package ovap.video;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.jface.dialogs.DialogSettings;

public abstract class AbstractSession {
	private SessionState	streamState	= SessionState.STOPPED;
	private IDebugTarget	target;

	public abstract void deInitialize();

	public abstract String getId();

	public SessionState getState() {
		return streamState;
	}

	public IDebugTarget getTarget() {
		return target;
	}
	
	public abstract ArrayList<Parameter> getParameters();

	public abstract void initialize(final Map<String, Object> attributes);
	public abstract void initialize(final DialogSettings settings);

	public abstract boolean pause();

	public abstract boolean resume();

	protected void setState(final SessionState state) {
		streamState = state;
	}

	public void setTarget(final IDebugTarget streamTarget) {
		this.target = streamTarget;
	}

	public abstract boolean start();

	public abstract void stop();

}