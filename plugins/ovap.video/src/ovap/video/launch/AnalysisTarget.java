package ovap.video.launch;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;

import ovap.video.SessionState;
import ovap.video.VideoManager;

public class AnalysisTarget extends OVAPTarget {

	public AnalysisTarget(final OVAPLaunch launch, final String name) {
		super(launch, name);
	}

	@Override
	public boolean canResume() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getSessionId());
		if (state == SessionState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public boolean canSuspend() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getSessionId());
		if (state == SessionState.RUNNING)
			return true;
		else
			return false;
	}

	@Override
	public boolean canTerminate() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getSessionId());
		if ((state == SessionState.RUNNING) || (state == SessionState.PAUSED))
			return true;
		else
			return false;
	}

	public String getSessionId() {
		try {
			return getLaunch().getLaunchConfiguration().getName()+"."+getName();
		} catch (DebugException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isSuspended() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getSessionId());
		if (state == SessionState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public void resume() throws DebugException {
		VideoManager.getDefault().resumeAnalysis(getSessionId());
		fireEvent(DebugEvent.RESUME);
	}

	@Override
	public void suspend() throws DebugException {
		VideoManager.getDefault().pauseAnalysis(getSessionId());

		fireEvent(DebugEvent.SUSPEND);
	}

	@Override
	public void terminate() throws DebugException {
		VideoManager.getDefault().stopAnalysis(getSessionId());
		setTerminated(true);
		fireEvent(DebugEvent.TERMINATE);
	}

}