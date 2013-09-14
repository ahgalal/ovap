package ovap.video;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;

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

	public boolean initializeSession(String sessionId){
		if(getSession(sessionId)==null){
			sessions.add(new Session(sessionId));
		}
		return true;
	}


	public boolean startStream(String sessionId,FiltersConfiguration configuration) {
		Session session = getSession(sessionId);
		session.initialize(configuration);
		session.startStream();
		return true;
	}


	public void stopStream(String sessionId) {
		getSession(sessionId).stopStream();
	}

	public StreamState getState(String sessionId) {
		return getSession(sessionId).getState();
	}


	private Session getSession(String sessionId) {
		for(Session session:sessions){
			if(session.getName().equals(sessionId))
				return session;
		}
		return null;
	}


	@Override
	public Object create() throws CoreException {
		return getDefault();
	}
}
