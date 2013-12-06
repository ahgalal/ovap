package ovap.video.session;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.debug.core.model.IDebugTarget;

import ovap.video.Parameter;
import ovap.video.SessionState;

public abstract class AbstractSession {
	private SessionState	streamState	= SessionState.STOPPED;
	private IDebugTarget	target;
	private String id;
	
	public AbstractSession(String id) {
		this.id=id;
	}

	public abstract void deInitialize();

	public String getId(){
		return id;
	}

	public SessionState getState() {
		return streamState;
	}

	public IDebugTarget getTarget() {
		return target;
	}
	
	public abstract ArrayList<Parameter> getParameters();

	public abstract void initialize(final Map<String, String> attributes);

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