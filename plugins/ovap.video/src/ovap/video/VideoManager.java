package ovap.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;

import ovap.video.launch.AnalysisTarget;
import ovap.video.launch.StreamTarget;

public class VideoManager implements IExecutableExtensionFactory {

	public static final String	EP_OVAP_VIDEO_FILTER_MANAGER	= "ovap.video.filter.manager";
	public static final String	EP_OVAP_VIDEO_MODULE_MANAGER	= "ovap.video.module.manager";
	public static final String	EP_OVAP_VIDEO_SOURCE_MANAGER	= "ovap.video.source.manager";
	private static VideoManager	self;

	public static VideoManager getDefault() {
		if (self == null)
			self = new VideoManager();
		return self;
	}

	private Map<AnalysisSession, String>	analysisSessions;
	private ArrayList<StreamSession>		streamSessions;

	public VideoManager() {
		if (self != null)
			return;
		else
			self = this;
		streamSessions = new ArrayList<StreamSession>();
		analysisSessions = new HashMap<AnalysisSession, String>();
	}

	@Override
	public Object create() throws CoreException {
		return getDefault();
	}

	private AnalysisSession getAnalysisSession(final String analysisSessionId) {
		for (final AnalysisSession session : analysisSessions.keySet())
			if (session.getId().equals(analysisSessionId))
				return session;
		return null;
	}

	private ArrayList<AnalysisSession> getAnalysisSessions(
			final String streamSessionId) {
		final ArrayList<AnalysisSession> ret = new ArrayList<AnalysisSession>();
		for (final AnalysisSession analysisSession : analysisSessions.keySet()) {
			if (analysisSessions.get(analysisSession).equals(streamSessionId))
				ret.add(analysisSession);
		}
		return ret;
	}

	public SessionState getAnalysisState(final String sessionId) {
		final AbstractSession session = getAnalysisSession(sessionId);
		if (session == null) // session is not created yet
			return SessionState.INITAIL;
		final SessionState state = session.getState();
		return state;
	}

	public IFilterManager getFilterManager(final String sessionId) {
		final StreamSession session = getStreamSession(sessionId);
		return session.getFilterManager();
	}

	private StreamSession getStreamSession(final String sessionId) {
		for (final StreamSession session : streamSessions)
			if (session.getId().equals(sessionId))
				return session;
		return null;
	}

	public SessionState getStreamState(final String sessionId) {
		final AbstractSession session = getStreamSession(sessionId);
		if (session == null) // session is not created yet
			return SessionState.INITAIL;
		final SessionState state = session.getState();
		return state;
	}

	public boolean initializeAnalysisSession(
			final AnalysisTarget analysisTarget,
			final Map<String, String> analysisSettings) {
		String analysisSessionId = analysisTarget.getSessionId();
		String streamSessionId = analysisTarget.getLaunch().getLaunchConfiguration().getName();
		AnalysisSession session = getAnalysisSession(analysisSessionId);
		if (session != null) {
			session.deInitialize();
		}
		session = new AnalysisSession(analysisSessionId);
		analysisSessions.put(session, streamSessionId);
		session.setTarget(analysisTarget);

		session.initialize(analysisSettings);
		
		final StreamSession streamSession = getStreamSession(streamSessionId);
		final ArrayList<Parameter> filtersParameters = streamSession
				.getParameters();
		session.registerParameters(filtersParameters);
		return true;
	}

	public boolean initializeStreamSession(final StreamTarget streamTarget,
			final Map<String, Object> configurations) {
		final String launchConfigName = streamTarget.getLaunch()
				.getLaunchConfiguration().getName();
		StreamSession session = getStreamSession(launchConfigName);
		if (session != null) {
			session.deInitialize();
			streamSessions.remove(session);
		}
		session = new StreamSession(launchConfigName);
		streamSessions.add(session);
		session.setTarget(streamTarget);

		HashMap<String, String> settings = new HashMap<String, String>();
		for(String key: configurations.keySet())
		settings.put(key,configurations.get(key).toString());
		session.initialize(settings);
		
/*		ArrayList<AnalysisSession> analysisSessionsToRemove = new ArrayList<AnalysisSession>();
		// remove old analysis sessions associated with this stream session
		for(AnalysisSession analysisSession:analysisSessions.keySet()){
			if(analysisSessions.get(analysisSession).equals(session.getId()))
				analysisSessionsToRemove.add(analysisSession);
		}
		for(AnalysisSession analysisSession:analysisSessionsToRemove)
			analysisSessions.remove(analysisSession);*/

		return true;
	}

	public boolean pauseAnalysis(final String sessionId) {
		final AbstractSession session = getAnalysisSession(sessionId);
		session.pause();
		return true;
	}

	public boolean pauseStream(final String sessionId) {
		final AbstractSession session = getStreamSession(sessionId);
		session.pause();

		final ArrayList<AnalysisSession> associatedAnalysisSessions = getAnalysisSessions(sessionId);
		for (final AnalysisSession analysisSession : associatedAnalysisSessions)
			try {
				analysisSession.getTarget().suspend();
			} catch (final DebugException e) {
				e.printStackTrace();
			}
		return true;
	}

	public boolean resumeAnalysis(final String sessionId) {
		final AbstractSession session = getAnalysisSession(sessionId);
		session.resume();
		return true;
	}

	public boolean resumeStream(final String sessionId) {
		final AbstractSession session = getStreamSession(sessionId);
		session.resume();
		return true;
	}

	public boolean startAnalysis(final String sessionId) {
		final AbstractSession session = getAnalysisSession(sessionId);
		session.start();
		return true;
	}

	public boolean startStream(final String sessionId) {
		final AbstractSession session = getStreamSession(sessionId);
		session.start();
		return true;
	}

	public void stopAnalysis(final String sessionId) {
		final AbstractSession session = getAnalysisSession(sessionId);
		session.stop();
	}

	public void stopStream(final String sessionId) {
		final AbstractSession session = getStreamSession(sessionId);
		session.stop();

		final ArrayList<AnalysisSession> associatedSessions = getAnalysisSessions(sessionId);
		for (final AnalysisSession analysisSession : associatedSessions)
			try {
				analysisSession.getTarget().terminate();
			} catch (final DebugException e) {
				e.printStackTrace();
			}
	}

	public void removeSession(IDebugTarget target) {
		AnalysisSession analysisSession=null;
		for(AnalysisSession tmpAnalysisSession: analysisSessions.keySet())
			if(tmpAnalysisSession.getTarget().equals(target)){
				analysisSession = tmpAnalysisSession;
				break;
			}
		analysisSessions.remove(analysisSession);
	}
}
