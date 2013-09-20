/**
 * 
 */
package ovap.video.source;

import java.awt.Point;
import java.util.ArrayList;

import ovap.video.FrameData;
import ovap.video.IStreamEndListener;

/**
 * @author Creative
 */
public abstract class VideoSource {
	protected FrameData						fia;
	protected boolean						paused	= false;
	protected SourceStatus					status;
	protected ArrayList<IStreamEndListener>	streamEndListeners;

	public VideoSource() {
		streamEndListeners = new ArrayList<IStreamEndListener>();
	}

	public void addStreamEndListener(final IStreamEndListener streamEndListener) {
		streamEndListeners.add(streamEndListener);
	}

	public abstract Point getFrameSize();

	public abstract String getName();

	public abstract SourceStatus getStatus();

	public abstract long getStreamLength();

	public abstract long getStreamPosition();

	public abstract SourceType getType();

	public abstract boolean initialize(FrameData frame,
			SourceConfiguration configs);

	public void notifyStreamEndListeners() {
		for (final IStreamEndListener streamEndListener : streamEndListeners) {
			streamEndListener.streamEnded();
		}
		streamEndListeners.clear();
	}

	public abstract boolean pauseStream();

	public void removeStreamEndListener(
			final IStreamEndListener streamEndListener) {
		streamEndListeners.remove(streamEndListener);
	}

	public abstract boolean resumeStream();

	/**
	 * Starts stream.<br>
	 * <b>Note:</b> this method is synchronous, it returns when stream is
	 * actually started.
	 * 
	 * @return
	 */
	public abstract boolean startStream();

	public abstract boolean stopStream();
}
