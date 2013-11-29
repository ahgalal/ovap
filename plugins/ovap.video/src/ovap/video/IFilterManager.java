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
	boolean initialize(Map<String, Object> configurations, FrameData frameData);
	boolean startStream();
	boolean stopStream();
	boolean pauseStream();
	boolean resumeStream();
	ArrayList<BufferedImage> getFilterInputs(String filterName);
	ArrayList<Parameter> getParameters();
}
