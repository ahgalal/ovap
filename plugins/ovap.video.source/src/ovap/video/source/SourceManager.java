/**
 * 
 */
package ovap.video.source;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

import ovap.video.FrameData;
import ovap.video.ISourceManager;
import ovap.video.IStreamEndListener;
import utils.PDEUtils;

/**
 * @author Creative
 */
public class SourceManager implements ISourceManager, IStreamEndListener {

	public static final String	OVAP_DEVICE_INPUT_EP	= "ovap.device.input";

	public static ArrayList<VideoSource> getSources() {
		final ArrayList<VideoSource> sources = new ArrayList<VideoSource>();
		final IConfigurationElement[] config = PDEUtils
				.getExtensions(OVAP_DEVICE_INPUT_EP);
		for (final IConfigurationElement e : config) {
			final VideoSource videoSource = PDEUtils.instantiateExtension(
					VideoSource.class, e);
			sources.add(videoSource);
		}
		return sources;
	}

	private VideoSource						activeSource	= null;
	private SourceConfiguration				configuration;
	private FrameData						frameData;
	private ArrayList<IStreamEndListener>	streamEndListeners;
	private final ArrayList<VideoSource>	videoSources;

	public SourceManager() {
		videoSources = getSources();
	}

	@Override
	public void addStreamEndListener(final IStreamEndListener streamEndListener) {
		streamEndListeners.add(streamEndListener);
	}

	@Override
	public FrameData getFrameData() {
		return frameData;
	}

	@Override
	public boolean initialize(final Map<String, Object> configurations) {
		streamEndListeners = new ArrayList<IStreamEndListener>();

		final String sourceName = (String) configurations
				.get(SourceLaunchConfigs.SOURCE_NAME.toString());

		for (final VideoSource tmpSource : videoSources)
			if (tmpSource.getName().equals(sourceName)) {
				activeSource = tmpSource;
				break;
			}

		if (activeSource.getType() == SourceType.FILE)
			configuration = new SourceFileConfiguration(configurations);
		else
			configuration = new SourceCamConfiguration(configurations);

		activeSource.initialize(configuration);
		activeSource.addStreamEndListener(this);
		frameData = activeSource.getFrameData();
		return true;
	}

	@Override
	public boolean pauseStream() {
		activeSource.pauseStream();
		return true;
	}

	@Override
	public void removeStreamEndListener(
			final IStreamEndListener streamEndListener) {
		streamEndListeners.remove(streamEndListener);
	}

	@Override
	public boolean resumeStream() {
		activeSource.resumeStream();
		return true;
	}

	@Override
	public boolean startStream() {
		activeSource.startStream();
		return true;
	}

	@Override
	public boolean stopStream() {
		activeSource.stopStream();
		return false;
	}

	@Override
	public void streamEnded() {
		//System.out.println("SourceManager.streamEnded()1" + this);
		for (final IStreamEndListener streamEndListener : streamEndListeners)
			streamEndListener.streamEnded();
		//System.out.println("SourceManager.streamEnded()2" + this);
	}
}
