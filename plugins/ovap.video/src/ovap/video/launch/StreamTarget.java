/**
 * 
 */
package ovap.video.launch;

import java.util.Map;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.internal.core.LaunchManager;

import ovap.video.Activator;
import ovap.video.StreamState;
import ovap.video.VideoManager;

/**
 * @author Creative
 */
public class StreamTarget implements IDebugTarget {
	private final OVAPLaunch	launch;
	private final String		name;
	private boolean				terminated	= false;

	public StreamTarget(final OVAPLaunch streamLaunch, final String name) {
		this.launch = streamLaunch;
		this.name = name;
	}

	@Override
	public void breakpointAdded(final IBreakpoint breakpoint) {
	}

	@Override
	public void breakpointChanged(final IBreakpoint breakpoint,
			final IMarkerDelta delta) {
	}

	@Override
	public void breakpointRemoved(final IBreakpoint breakpoint,
			final IMarkerDelta delta) {
	}

	@Override
	public boolean canDisconnect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canResume() {
		final StreamState state = VideoManager.getDefault().getState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == StreamState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public boolean canSuspend() {
		final StreamState state = VideoManager.getDefault().getState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == StreamState.STREAMING)
			return true;
		else
			return false;
	}

	@Override
	public boolean canTerminate() {
		final StreamState state = VideoManager.getDefault().getState(
				getLaunch().getLaunchConfiguration().getName());
		if ((state == StreamState.STREAMING) || (state == StreamState.PAUSED))
			return true;
		else
			return false;
	}

	@Override
	public void disconnect() throws DebugException {
		// TODO Auto-generated method stub

	}

	private void fireEvent(final int eventId) {
		final DebugEvent debugEvent = new DebugEvent(this, eventId);
		DebugPlugin.getDefault().fireDebugEventSet(
				new DebugEvent[] { debugEvent });
	}

	@Override
	public Object getAdapter(final Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDebugTarget getDebugTarget() {
		return this;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@Override
	public IMemoryBlock getMemoryBlock(final long startAddress,
			final long length) throws DebugException {
		return null;
	}

	@Override
	public String getModelIdentifier() {
		return Activator.PLUGIN_ID;
	}

	@Override
	public String getName() throws DebugException {
		return name;
	}

	@Override
	public IProcess getProcess() {
		return null;
	}

	@Override
	public IThread[] getThreads() throws DebugException {
		// TODO Auto-generated method stub
		return new IThread[0];
	}

	@Override
	public boolean hasThreads() throws DebugException {
		return false;
	}

	@Override
	public boolean isDisconnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuspended() {
		final StreamState state = VideoManager.getDefault().getState(
				getLaunch().getLaunchConfiguration().getName());
		if (state == StreamState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public boolean isTerminated() {
		return terminated;
	}

	@Override
	public void resume() throws DebugException {
		VideoManager.getDefault().resumeStream(
				getLaunch().getLaunchConfiguration().getName());
		fireEvent(DebugEvent.RESUME);
	}

	@Override
	public boolean supportsBreakpoint(final IBreakpoint breakpoint) {
		return false;
	}

	@Override
	public boolean supportsStorageRetrieval() {
		return false;
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
		terminated = true;
		fireEvent(DebugEvent.TERMINATE);
	}

/*	public void launchConfigurationChanged(ILaunchConfiguration configuration) {
		Map<String, Object> attributes = null;
		try {
			attributes = configuration.getAttributes();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		VideoManager.getDefault().launchConfigurationChanged(attributes,configuration.getName());
	}*/
}
