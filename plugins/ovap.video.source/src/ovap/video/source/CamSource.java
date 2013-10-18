/**
 * 
 */
package ovap.video.source;

import java.awt.Point;

/**
 * @author Creative
 *
 */
public abstract class CamSource extends VideoSource {
	@Override
	public SourceType getType() {
		return SourceType.CAM;
	}
	public abstract int getNumberOfCams();
	public abstract Point[] getSupportedFrameSizes();
}
