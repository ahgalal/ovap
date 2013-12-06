/**
 * 
 */
package ovap.video.launch.model;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;

import ovap.video.SessionState;
import ovap.video.VideoManager;

/**
 * @author Creative
 */
public class StreamTarget extends OVAPTarget {
	public StreamTarget(final OVAPLaunch streamLaunch, final String name) {
		super(streamLaunch, name);
	}

	@Override
	public boolean canResume() {
		final SessionState state = VideoManager.getDefault().getStreamState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == SessionState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public boolean canSuspend() {
		final SessionState state = VideoManager.getDefault().getStreamState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == SessionState.RUNNING)
			return true;
		else
			return false;
	}

	@Override
	public boolean canTerminate() {
		final SessionState state = VideoManager.getDefault().getStreamState(
				getLaunch().getLaunchConfiguration().getName());
		if ((state == SessionState.RUNNING) || (state == SessionState.PAUSED))
			return true;
		else
			return false;
	}

	@Override
	public boolean isSuspended() {
		final SessionState state = VideoManager.getDefault().getStreamState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == SessionState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public void resume() throws DebugException {
		VideoManager.getDefault().resumeStream(
				getLaunch().getLaunchConfiguration().getName());
		fireEvent(DebugEvent.RESUME);
	}

	@Override
	public void suspend() throws DebugException {
		VideoManager.getDefault().pauseStream(
				getLaunch().getLaunchConfiguration().getName());

		fireEvent(DebugEvent.SUSPEND);
	}

	@Override
	public void terminate() throws DebugException {
		VideoManager.getDefault().stopStream(
				getLaunch().getLaunchConfiguration().getName());
		setTerminated(true);
		fireEvent(DebugEvent.TERMINATE);
	}

	/*
	 * public void launchConfigurationChanged(ILaunchConfiguration
	 * configuration) { Map<String, Object> attributes = null; try { attributes
	 * = configuration.getAttributes(); } catch (CoreException e) {
	 * e.printStackTrace(); }
	 * VideoManager.getDefault().launchConfigurationChanged
	 * (attributes,configuration.getName()); }
	 */
}
