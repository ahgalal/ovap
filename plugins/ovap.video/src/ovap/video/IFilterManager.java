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
	boolean initialize(Map<String, Object> launchConfigurations, FrameData frameData);
	boolean startStream();
	boolean stopStream();
	boolean pauseStream();
	boolean resumeStream();
}
