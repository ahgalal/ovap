/**
 * 
 */
package ovap.video;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.debug.core.DebugException;
import org.eclipse.jface.dialogs.DialogSettings;

import utils.PDEUtils;

/**
 * @author Creative
 */
public class StreamSession extends AbstractSession implements
		IStreamEndListener {
	private final IFilterManager	filterManager;
	private final ISourceManager	sourceManager;

	public StreamSession() {
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

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ISession#getId()
	 */
	@Override
	public String getId() {
		return getTarget().getLaunch().getLaunchConfiguration().getName();
	}

	@Override
	public ArrayList<Parameter> getParameters() {
		return filterManager.getParameters();
	}

	@Override
	public void initialize(final DialogSettings settings) {
		throw new UnsupportedOperationException("method is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ISession#initialize(java.util.Map)
	 */
	@Override
	public void initialize(final Map<String, Object> attributes) {
		sourceManager.initialize(attributes);
		sourceManager.addStreamEndListener(this);
		final FrameData frameData = sourceManager.getFrameData();
		filterManager.initialize(attributes, frameData);
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
