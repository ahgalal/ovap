/**
 * 
 */
package ovap.video.source;

import java.awt.Point;

import ovap.video.FrameData;


/**
 * @author Creative
 *
 */
public abstract class VideoSource {
	protected FrameData	fia;
	protected SourceStatus			status;
	protected boolean paused=false;
	public abstract SourceType getType();
	public abstract String getName();
	public abstract SourceStatus getStatus();
	public abstract boolean initialize(FrameData frame, SourceConfiguration configs);
	public abstract boolean startStream();
	public abstract boolean stopStream();
	public abstract boolean pauseStream();
	public abstract boolean resumeStream();
	public abstract long getStreamPosition();
	public abstract long getStreamLength();
	public abstract Point getFrameSize();
}
