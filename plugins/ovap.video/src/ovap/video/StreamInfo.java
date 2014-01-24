/**
 * 
 */
package ovap.video;

import java.awt.Point;

/**
 * @author Creative
 */
public class StreamInfo {
	private final Point	frameSize;
	private final long	length;

	public StreamInfo(final long length, final Point frameSize) {
		super();
		this.length = length;
		this.frameSize = frameSize;
	}

	public Point getFrameSize() {
		return frameSize;
	}

	public long getLength() {
		return length;
	}

}
