package ovap.video;

import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;

public class StreamingStateProvider extends AbstractSourceProvider {

	private static final String OVAP_VIDEO_STREAMING_STATE = "ovap.video.streaming.state";
	private static final String ENABLED = "enabled";
	private static final String DISABLED = "disabled";
	public static final String OVAP_VIDEO_STREAMING_ENABLE_STOP = "ovap.video.streaming.enable.stop";
	public static final String OVAP_VIDEO_STREAMING_ENABLE_RESUME = "ovap.video.streaming.enable.resume";
	
	public StreamingStateProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map getCurrentState() {/*
		Map<String, String> stateMap=new HashMap<String, String>();
		String value=null;
		StreamState state = VideoManager.getDefault().getState();
		if(state==StreamState.STREAMING)
			value=DISABLED;
		else // paused or stopped
			value=ENABLED;
		stateMap.put(OVAP_VIDEO_STREAMING_ENABLE_RESUME, value);
		value=null;
		if(state==StreamState.STOPPED)
			value=DISABLED;
		else // paused or stopped
			value=ENABLED;
		stateMap.put(OVAP_VIDEO_STREAMING_ENABLE_STOP, value);
		
		// current state name
		stateMap.put(OVAP_VIDEO_STREAMING_STATE, state.name());
		return stateMap;
	*/
	return null;	
	}
	
	public void notifyStateUpdate(){
		fireSourceChanged(0, getCurrentState());
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[]{
				OVAP_VIDEO_STREAMING_ENABLE_RESUME,
				OVAP_VIDEO_STREAMING_ENABLE_STOP,
				OVAP_VIDEO_STREAMING_STATE};
	}

}
