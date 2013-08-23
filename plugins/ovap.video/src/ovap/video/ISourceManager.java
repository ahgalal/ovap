/**
 * 
 */
package ovap.video;

/**
 * @author Creative
 *
 */
public interface ISourceManager {
	FrameData getFrameData();
	boolean initialize();
	boolean startStream();
	boolean stopStream();
}
