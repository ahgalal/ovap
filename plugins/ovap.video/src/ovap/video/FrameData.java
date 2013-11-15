/**
 * 
 */
package ovap.video;

/**
 * @author Creative
 *
 */
public class FrameData {

	/**
	 * Array containing the image data as an array of integers.
	 */
	private int[]	frameData;
	private int width;
	private int height;

	public void setFrameData(int[] frameData) {
		this.frameData = frameData;
	}
	
	public FrameData(int width,int height) {
		this.width=width;
		this.height=height;
	}

	public int[] getFrameData() {
		return frameData;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
