/**
 * 
 */
package ovap.video.source;


/**
 * @author Creative
 */
public abstract class VideoFileSource extends VideoSource {
	@Override
	public SourceType getType() {
		return SourceType.FILE;
	}
}
