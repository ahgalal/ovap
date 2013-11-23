/**
 * 
 */
package ovap.video.launch;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;

import ovap.video.Activator;

/**
 * @author Creative
 *
 */
public abstract class OVAPTarget implements IDebugTarget {
	private final OVAPLaunch	launch;
	private final String		name;
	private boolean				terminated	= false;

	public OVAPTarget(final OVAPLaunch launch, final String name) {
		this.launch = launch;
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
		return false;
	}

	@Override
	public void disconnect() throws DebugException {
	}

	protected void fireEvent(final int eventId) {
		final DebugEvent debugEvent = new DebugEvent(this, eventId);
		DebugPlugin.getDefault().fireDebugEventSet(
				new DebugEvent[] { debugEvent });
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(final Class adapter) {
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
		return new IThread[0];
	}

	@Override
	public boolean hasThreads() throws DebugException {
		return false;
	}

	@Override
	public boolean isDisconnected() {
		return false;
	}

	@Override
	public boolean isTerminated() {
		return terminated;
	}

	@Override
	public boolean supportsBreakpoint(final IBreakpoint breakpoint) {
		return false;
	}

	@Override
	public boolean supportsStorageRetrieval() {
		return false;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}
}
