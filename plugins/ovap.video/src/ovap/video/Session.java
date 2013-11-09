/**
 * 
 */
package ovap.video;

import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.debug.core.DebugException;

import ovap.video.launch.StreamTarget;
import utils.PDEUtils;

/**
 * @author Creative
 */
public class Session implements IStreamEndListener{
	private final IFilterManager	filterManager;
	private final IModuleManager	moduleManager;
	private final ISourceManager	sourceManager;
	private StreamState				streamState	= StreamState.STOPPED;
	private StreamTarget			streamTarget;

	public Session(final StreamTarget streamTarget) {
		this.streamTarget = streamTarget;

		// Filter Manager
		final IConfigurationElement[] filterManagerExtensions = PDEUtils
				.getExtensions(VideoManager.EP_OVAP_VIDEO_FILTER_MANAGER);
		filterManager = PDEUtils.instantiateExtension(IFilterManager.class,
				filterManagerExtensions[0]);

		// Module Manager
		final IConfigurationElement[] moduleManagerExtensions = PDEUtils
				.getExtensions(VideoManager.EP_OVAP_VIDEO_MODULE_MANAGER);
		moduleManager = PDEUtils.instantiateExtension(IModuleManager.class,
				moduleManagerExtensions[0]);

		// Source Manager
		final IConfigurationElement[] sourceManagerExtensions = PDEUtils
				.getExtensions(VideoManager.EP_OVAP_VIDEO_SOURCE_MANAGER);
		sourceManager = PDEUtils.instantiateExtension(ISourceManager.class,
				sourceManagerExtensions[0]);
		setState(StreamState.STOPPED);

	}

	public String getId() {
		return streamTarget.getLaunch().getLaunchConfiguration().getName();
	}

	public StreamState getState() {
		return streamState;
	}

	public StreamTarget getStreamTarget() {
		return streamTarget;
	}

	public void initialize(final Map<String, Object> attributes) {
		sourceManager.initialize(attributes);
		sourceManager.addStreamEndListener(this);
		final FrameData frameData = sourceManager.getFrameData();
		filterManager.initialize(attributes, frameData);
	}
	
	public void deInitialize(){
		filterManager.deInitialize();
	}

	public boolean pauseStream() {
		moduleManager.pause();
		filterManager.pauseStream();
		sourceManager.pauseStream();

		setState(StreamState.PAUSED);

		return true;
	}

	public boolean resumeStream() {
		sourceManager.resumeStream();
		filterManager.resumeStream();
		moduleManager.resume(); // FIXME: only resume if the tracking status was
								// ON when pausing

		setState(StreamState.STREAMING);
		return true;
	}

	private void setState(final StreamState state) {
		streamState = state;

		// notify State provider
		/*
		 * ISourceProviderService service = (ISourceProviderService)
		 * PlatformUI.getWorkbench
		 * ().getWorkbenchWindows()[0].getService(ISourceProviderService.class);
		 * StreamingStateProvider streamStateProvider = (StreamingStateProvider)
		 * service.getSourceProvider(StreamingStateProvider.
		 * OVAP_VIDEO_STREAMING_ENABLE_RESUME);
		 * streamStateProvider.notifyStateUpdate();
		 */
	}

	public void setStreamTarget(final StreamTarget streamTarget) {
		this.streamTarget = streamTarget;
	}

	public boolean startStream() {
		sourceManager.startStream();
		filterManager.startStream();
		setState(StreamState.STREAMING);
		return true;
	}

	public void stopStream() {
		filterManager.stopStream();
		sourceManager.stopStream();
		setState(StreamState.STOPPED);
	}

	@Override
	public void streamEnded() {
		try {
			streamTarget.terminate();
		} catch (DebugException e) {
			e.printStackTrace();
		}
	}
}
