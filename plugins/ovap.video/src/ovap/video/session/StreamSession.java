/**
 * 
 */
package ovap.video.session;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.debug.core.DebugException;

import ovap.video.FrameData;
import ovap.video.IFilterManager;
import ovap.video.ISourceManager;
import ovap.video.IStreamEndListener;
import ovap.video.Parameter;
import ovap.video.SessionState;
import ovap.video.VideoManager;

import utils.PDEUtils;

/**
 * @author Creative
 */
public class StreamSession extends AbstractSession implements
		IStreamEndListener {
	private final IFilterManager	filterManager;
	private final ISourceManager	sourceManager;

	public StreamSession(String id) {
		super(id);
		// Filter Manager
		final IConfigurationElement[] filterManagerExtensions = PDEUtils
				.getExtensions(VideoManager.EP_OVAP_VIDEO_FILTER_MANAGER);
		filterManager = PDEUtils.instantiateExtension(IFilterManager.class,
				filterManagerExtensions[0]);

		// Source Manager
		final IConfigurationElement[] sourceManagerExtensions = PDEUtils
				.getExtensions(VideoManager.EP_OVAP_VIDEO_SOURCE_MANAGER);
		sourceManager = PDEUtils.instantiateExtension(ISourceManager.class,
				sourceManagerExtensions[0]);
		setState(SessionState.STOPPED);

	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ISession#deInitialize()
	 */
	@Override
	public void deInitialize() {
		if ((getState() == SessionState.RUNNING)
				|| (getState() == SessionState.PAUSED))
			stop();
	}

	public ArrayList<BufferedImage> getFilterInputs(final String filterName) {
		return filterManager.getFilterInputs(filterName);
	}

	public IFilterManager getFilterManager() {
		return filterManager;
	}

	@Override
	public ArrayList<Parameter> getParameters() {
		return filterManager.getParameters();
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ISession#initialize(java.util.Map)
	 */
	@Override
	public void initialize(final Map<String, String> configurations) {
		HashMap<String, Object> settings = new HashMap<String, Object>();
		settings.putAll(configurations);
		sourceManager.initialize(settings);
		sourceManager.addStreamEndListener(this);
		final FrameData frameData = sourceManager.getFrameData();
		filterManager.initialize(settings, frameData);
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ISession#pauseStream()
	 */
	@Override
	public boolean pause() {
		filterManager.pauseStream();
		sourceManager.pauseStream();

		setState(SessionState.PAUSED);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ISession#resumeStream()
	 */
	@Override
	public boolean resume() {
		sourceManager.resumeStream();
		filterManager.resumeStream();

		setState(SessionState.RUNNING);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ISession#startStream()
	 */
	@Override
	public boolean start() {
		sourceManager.startStream();
		filterManager.startStream();
		setState(SessionState.RUNNING);
		return true;
	}
	
	public boolean isStreamReadyForAnalysis(){
		return filterManager.areFiltersReady();
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ISession#stopStream()
	 */
	@Override
	public void stop() {
		filterManager.stopStream();
		sourceManager.stopStream();
		setState(SessionState.STOPPED);
	}

	@Override
	public void streamEnded() {
		try {
			getTarget().terminate();
		} catch (final DebugException e) {
			e.printStackTrace();
		}
	}
}
