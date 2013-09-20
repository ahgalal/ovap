/**
 * 
 */
package ovap.video;

import java.util.Map;

/**
 * @author Creative
 *
 */
public interface IFilterManager {
	boolean initialize(Map<String, Object> configurations, FrameData frameData);
	boolean startStream();
	boolean stopStream();
	boolean pauseStream();
	boolean resumeStream();
}
