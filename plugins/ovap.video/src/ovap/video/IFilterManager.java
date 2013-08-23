/**
 * 
 */
package ovap.video;

/**
 * @author Creative
 *
 */
public interface IFilterManager {
	boolean initialize(FrameData frameData);
	boolean startStream();
	boolean stopStream();
}
