package ovap.video.source.videofile;

import jagvidlib.JAGVidLib;
import jagvidlib.JAGVidLib.StreamState;

import java.awt.Point;

import ovap.video.FrameData;
import ovap.video.source.SourceConfiguration;
import ovap.video.source.SourceFileConfiguration;
import ovap.video.source.SourceStatus;
import ovap.video.source.VideoFileSource;
import sys.utils.Utils;

public class AGVideoFileSource extends VideoFileSource {

	private class RunnableAGVidLib implements Runnable {
		@Override
		public void run() {
			final Point dims = vidLib.getVideoDimensions();
			frameData.setFrameData(new int[dims.x * dims.y]);

			vidLib.play();
			Utils.sleep(100);
			while (!stopStream) {
				synchronized (this) {
					while (paused) {
						try {
							this.wait();
						} catch (final InterruptedException e) {
						}
					}
				}

				// long l1 = System.currentTimeMillis();
				frameData.setFrameData(vidLib.getCurrentFrameInt());

				// long l2 = System.currentTimeMillis();

				if (frameData.getFrameData() != null)
					stableStreamSynchronizer.signalStableStream();

				else { // check if stream ended
					if (vidLib.getLength() <= vidLib.getPosition())
						notifyStreamEndListeners();
				}

				/*
				 * if (fia.getFrameData() != null) status =
				 * SourceStatus.STREAMING; else { if (paused) status =
				 * SourceStatus.PAUSED; else status = SourceStatus.ERROR; }
				 */

				Utils.sleep(30);
				// System.out.println(l2-l1 + "\n");
			}
		}
	}

	private static class StableStreamSynchronizer {
		private Long			actualWaitTime			= 0L;
		private final Object	flag					= "";
		private boolean			stableStreamVerified	= false;

		public void signalStableStream() {
			if (!stableStreamVerified) {
				synchronized (flag) {
					flag.notify();
					System.out.println("Signaling stable stream ...");
				}
				stableStreamVerified = true;

				actualWaitTime = System.currentTimeMillis() - actualWaitTime;
				System.out.println("Stable stream is established after: "
						+ actualWaitTime + " ms");
			}
		}

		public void waitForStableStream(final long timeOut) {
			System.out.println("Waiting for stable stream ...");
			actualWaitTime = System.currentTimeMillis();
			if (!stableStreamVerified) {
				try {
					synchronized (flag) {
						flag.wait(timeOut);
					}
				} catch (final InterruptedException e) {
					// FIXME: throw a stream start timeout exception
				}
			}
		}
	}

	private SourceFileConfiguration		configuration;
	private StableStreamSynchronizer	stableStreamSynchronizer;
	private boolean						stopStream;
	private Thread						thUpdateImage;
	private final JAGVidLib				vidLib;

	/**
	 * 
	 */
	public AGVideoFileSource() {
		vidLib = new JAGVidLib();
	}

	private SourceStatus convertStreamStatus(final StreamState state) {
		switch (state) {
			case INITIAL_STATE:
				return SourceStatus.INITIALIZING;
			case PAUSED:
				return SourceStatus.PAUSED;
			case RUNNING:
				return SourceStatus.STREAMING;
			case STOPPED:
				return SourceStatus.END_OF_STREAM;
			default:
				break;
		}
		return null;
	}

	@Override
	public Point getFrameSize() {
		return vidLib.getVideoDimensions();
	}

	/*
	 * (non-Javadoc)
	 * @see utils.video.input.VidInputter#getName()
	 */
	@Override
	public String getName() {
		return "VideoFileReader";
	}

	/*
	 * (non-Javadoc)
	 * @see utils.video.input.VidInputter#getStatus()
	 */
	@Override
	public SourceStatus getStatus() {
		final SourceStatus streamStatus = convertStreamStatus(vidLib.getState());
		return streamStatus;
	}

	@Override
	public long getStreamLength() {
		return vidLib.getLength();
	}

	@Override
	public long getStreamPosition() {
		return vidLib.getPosition();
	}

	@Override
	public boolean initialize(final SourceConfiguration configs) {
		this.configuration = (SourceFileConfiguration) configs;
		vidLib.initialize(configuration.getFileName());
		Utils.sleep(100);
		Point videoDimensions = vidLib.getVideoDimensions();
		frameData = new FrameData(videoDimensions.x, videoDimensions.y);
		stableStreamSynchronizer = new StableStreamSynchronizer();
		stopStream = false;
		return true;
	}

	@Override
	public boolean pauseStream() {
		paused = true;
		vidLib.pause();
		return true;
	}

	@Override
	public boolean resumeStream() {
		paused = false;
		vidLib.play();
		thUpdateImage.interrupt();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see utils.video.input.VidInputter#startStream()
	 */
	@Override
	public boolean startStream() {
		thUpdateImage = new Thread(new RunnableAGVidLib(), "AGVidLib");
		thUpdateImage.start();
		stableStreamSynchronizer.waitForStableStream(1000);
		stopStream = false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see utils.video.input.VidInputter#stopModule()
	 */
	@Override
	public boolean stopStream() {
		if(!stopStream){
			stopStream = true;
			paused = false;
			try {
				thUpdateImage.interrupt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				vidLib.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Utils.sleep(30);
		}
		return true;
	}

	@Override
	public void deInitialize() {
		stopStream();
	}
}
