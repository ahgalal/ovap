/**
 * 
 */
package ovap.video;

import java.util.Map;

/**
 * @author Creative
 *
 */
public interface ISourceManager {
	FrameData getFrameData();
	boolean initialize(Map<String, Object> configurations);
	boolean startStream();
	boolean stopStream();
	boolean pauseStream();
	boolean resumeStream();
	void addStreamEndListener(IStreamEndListener streamEndListener);
	void removeStreamEndListener(IStreamEndListener streamEndListener);
}
