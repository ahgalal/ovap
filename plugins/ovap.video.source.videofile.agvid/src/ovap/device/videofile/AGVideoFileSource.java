package ovap.device.videofile;

import jagvidlib.JAGVidLib;
import jagvidlib.JAGVidLib.StreamState;

import java.awt.Point;

import ovap.device.SourceConfiguration;
import ovap.device.SourceStatus;
import ovap.device.SourceType;
import ovap.device.VideoSource;
import ovap.video.FrameData;
import sys.utils.Utils;

public class AGVideoFileSource extends VideoSource {


	private class RunnableAGVidLib implements Runnable {
		@Override
		public void run() {
			final Point dims = vidLib.getVideoDimensions();
			fia.setFrameData(new int[dims.x * dims.y]);
			
			vidLib.play();
			Utils.sleep(100);
			while (!stopStream) {
				synchronized (this) {
					while (paused) {
						try {
							this.wait();
						} catch (InterruptedException e) {
						}
					}	
				}

				// long l1 = System.currentTimeMillis();
				fia.setFrameData(vidLib.getCurrentFrameInt());
				// long l2 = System.currentTimeMillis();
/*				if (fia.getFrameData() != null)
					status = SourceStatus.STREAMING;
				else{
					if(paused)
						status = SourceStatus.PAUSED;
					else
						status=SourceStatus.ERROR;
				}*/
				
				try {
					Thread.sleep(30);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
				// System.out.println(l2-l1 + "\n");
			}
		}
	}
	
	private SourceStatus convertStreamStatus(StreamState state){
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

	private boolean			stopStream;

	private Thread			thUpdateImage;

	private final JAGVidLib	vidLib;

	@Override
	public long getStreamPosition() {
		return vidLib.getPosition();
	}
	
	@Override
	public long getStreamLength() {
		return vidLib.getLength();
	}
	/**
	 * 
	 */
	public AGVideoFileSource() {
		vidLib = new JAGVidLib();
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
		SourceStatus streamStatus = convertStreamStatus(vidLib.getState());
		return streamStatus;
/*		if(paused)
			status=SourceStatus.PAUSED;
		return status;*/
	}

	@Override
	public boolean initialize(final FrameData frameData,
			final SourceConfiguration configs) {
		//this.configs = configs;
		fia = frameData;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see utils.video.input.VidInputter#startStream()
	 */
	@Override
	public boolean startStream() {
		vidLib.initialize("E:\\Documents\\Desktop\\BMT\\Videos\\FST_1_wmv2.avi"/*configs.getVideoFilePath()*/);
		Utils.sleep(100);
		thUpdateImage = new Thread(new RunnableAGVidLib(),"AGVidLib");
		thUpdateImage.start();
		stopStream = false;
		return true;
	}
	@Override
	public SourceType getType() {
		return SourceType.FILE;
	}
	/*
	 * (non-Javadoc)
	 * @see utils.video.input.VidInputter#stopModule()
	 */
	@Override
	public boolean stopStream() {
		stopStream = true;
		vidLib.stop();
		return true;
	}
	
	@Override
	public boolean pauseStream() {
		paused=true;
		vidLib.pause();
		return true;
	}

	@Override
	public boolean resumeStream() {
		paused=false;
		vidLib.play();
		thUpdateImage.interrupt();
		return true;		
	}

	@Override
	public Point getFrameSize() {
		return vidLib.getVideoDimensions();
	}
}
