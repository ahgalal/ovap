package ovap.video;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.debug.core.ILaunchConfiguration;

import ovap.video.launch.StreamTarget;

public class VideoManager implements IExecutableExtensionFactory{

	public static final String	EP_OVAP_VIDEO_SOURCE_MANAGER	= "ovap.video.source.manager";
	public static final String	EP_OVAP_VIDEO_MODULE_MANAGER	= "ovap.video.module.manager";
	public static final String	EP_OVAP_VIDEO_FILTER_MANAGER	= "ovap.video.filter.manager";
	private ArrayList<Session> sessions;
	private static VideoManager self;
	public static VideoManager getDefault(){
		if(self ==null)
			self=new VideoManager();
		return self;
	}

	public VideoManager() {
		if (self != null)
			return;
		else
			self = this;
		sessions=new ArrayList<Session>();
	}

	public boolean initializeSession(StreamTarget streamTarget){
		String launchConfigName = streamTarget.getLaunch().getLaunchConfiguration().getName();
		Session session =getSession(launchConfigName);
		if(session==null){
			session= new Session(streamTarget);
			sessions.add(session);
		}
		session.setStreamTarget(streamTarget);
		
		return true;
	}


	public boolean startStream(String sessionId,Map<String, Object> configurations) {
		Session session = getSession(sessionId);
		session.initialize(configurations);
		session.startStream();
		return true;
	}
	
	public boolean pauseStream(String sessionId) {
		Session session = getSession(sessionId);
		session.pauseStream();
		return true;
	}


	public void stopStream(String sessionId) {
		getSession(sessionId).stopStream();
	}

	public StreamState getState(String sessionId) {
		Session session = getSession(sessionId);
		if(session==null) // session is not created yet
			return StreamState.INITAIL;
		StreamState state = session.getState();
		return state;
	}


	private Session getSession(String sessionId) {
		for(Session session:sessions){
			if(session.getId().equals(sessionId))
				return session;
		}
		return null;
	}


	@Override
	public Object create() throws CoreException {
		return getDefault();
	}

	public boolean resumeStream(String sessionId) {
		Session session = getSession(sessionId);
		session.resumeStream();
		return true;
	}
}
