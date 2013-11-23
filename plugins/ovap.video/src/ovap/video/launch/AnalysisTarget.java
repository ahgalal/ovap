package ovap.video.launch;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;

import ovap.video.SessionState;
import ovap.video.VideoManager;

public class AnalysisTarget extends OVAPTarget {

	public AnalysisTarget(OVAPLaunch launch, String name) {
		super(launch, name);
	}

	@Override
	public boolean canResume() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == SessionState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public boolean canSuspend() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == SessionState.RUNNING)
			return true;
		else
			return false;
	}

	@Override
	public boolean canTerminate() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getLaunch().getLaunchConfiguration().getName());
		if ((state == SessionState.RUNNING) || (state == SessionState.PAUSED))
			return true;
		else
			return false;
	}

	@Override
	public boolean isSuspended() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == SessionState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public void resume() throws DebugException {
		VideoManager.getDefault().resumeAnalysis(
				getLaunch().getLaunchConfiguration().getName());
		fireEvent(DebugEvent.RESUME);
	}

	@Override
	public void suspend() throws DebugException {
		VideoManager.getDefault().pauseAnalysis(
				getLaunch().getLaunchConfiguration().getName());

		fireEvent(DebugEvent.SUSPEND);
	}

	@Override
	public void terminate() throws DebugException {
		VideoManager.getDefault().stopAnalysis(
				getLaunch().getLaunchConfiguration().getName());
		setTerminated(true);
		fireEvent(DebugEvent.TERMINATE);
	}

}