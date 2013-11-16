package ovap.video;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;

import ovap.video.launch.StreamTarget;
import sys.utils.Utils;

public class VideoManager implements IExecutableExtensionFactory{

	public static final String	EP_OVAP_VIDEO_SOURCE_MANAGER	= "ovap.video.source.manager";
	public static final String	EP_OVAP_VIDEO_MODULE_MANAGER	= "ovap.video.module.manager";
	public static final String	EP_OVAP_VIDEO_FILTER_MANAGER	= "ovap.video.filter.manager";
	private Map<String,Session> sessions;
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
		sessions=new HashMap<String, Session>();
	}

	public boolean initializeSession(StreamTarget streamTarget){
		String launchConfigName = streamTarget.getLaunch().getLaunchConfiguration().getName();
		Session session =getSession(launchConfigName);
		if(session==null){
			session= new Session(streamTarget);
			sessions.put(session.getId(), session);
		}else{
			session.deInitialize();
			//Utils.sleep(100);
			session= new Session(streamTarget);
			sessions.put(session.getId(), session);
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
		Session session = getSession(sessionId);
		session.stopStream();
		//session.deInitialize();
	}

	public StreamState getState(String sessionId) {
		Session session = getSession(sessionId);
		if(session==null) // session is not created yet
			return StreamState.INITAIL;
		StreamState state = session.getState();
		return state;
	}


	private Session getSession(String sessionId) {
		return sessions.get(sessionId);
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
	
	public IFilterManager getFilterManager(String sessionId){
		Session session = getSession(sessionId);
		return session.getFilterManager();
	}
}
