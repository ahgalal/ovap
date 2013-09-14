/**
 * 
 */
package ovap.video.launch;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;

import ovap.video.StreamState;
import ovap.video.VideoManager;

/**
 * @author Creative
 */
public class StreamTarget implements IDebugTarget {
	private final OVAPLaunch	launch;

	public StreamTarget(final OVAPLaunch streamLaunch) {
		this.launch = streamLaunch;
	}

	@Override
	public void breakpointAdded(final IBreakpoint breakpoint) {
		// TODO Auto-generated method stub

	}

	@Override
	public void breakpointChanged(final IBreakpoint breakpoint,
			final IMarkerDelta delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void breakpointRemoved(final IBreakpoint breakpoint,
			final IMarkerDelta delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canDisconnect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canResume() {
		final StreamState state = VideoManager.getDefault().getState();
		if (state == StreamState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public boolean canSuspend() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canTerminate() {
		final StreamState state = VideoManager.getDefault().getState();
		if ((state == StreamState.STREAMING) || (state == StreamState.PAUSED))
			return true;
		else
			return false;
	}

	@Override
	public void disconnect() throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getAdapter(final Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDebugTarget getDebugTarget() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ILaunch getLaunch() {
		// TODO Auto-generated method stub
		return launch;
	}

	@Override
	public IMemoryBlock getMemoryBlock(final long startAddress,
			final long length) throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModelIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() throws DebugException {
		// TODO Auto-generated method stub
		return "Stream1";
	}

	@Override
	public IProcess getProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IThread[] getThreads() throws DebugException {
		// TODO Auto-generated method stub
		return new IThread[0];
	}

	@Override
	public boolean hasThreads() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDisconnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuspended() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resume() throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsBreakpoint(final IBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supportsStorageRetrieval() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void suspend() throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminate() throws DebugException {
		VideoManager.getDefault().stopStream();
	}
}
