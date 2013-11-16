/**
 * 
 */
package ovap.video;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Creative
 *
 */
public interface IFilterManager {
	boolean initialize(Map<String, Object> launchConfigurations, FrameData frameData);
	boolean deInitialize();
	boolean startStream();
	boolean stopStream();
	boolean pauseStream();
	boolean resumeStream();
	ArrayList<BufferedImage> getFilterInputs(String filterName);
}
