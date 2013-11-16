/**
 * 
 */
package ovap.video.source.cam.agcam;

import java.awt.Point;

import ovap.video.FrameData;
import ovap.video.source.CamSource;
import ovap.video.source.SourceConfiguration;
import ovap.video.source.SourceStatus;

/**
 * @author Creative
 */
public class AGCamSource extends CamSource {

	@Override
	public Point getFrameSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "AG Cam";
	}

	@Override
	public int getNumberOfCams() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public SourceStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getStreamLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getStreamPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point[] getSupportedFrameSizes() {
		// TODO Auto-generated method stub
		return new Point[] { new Point(320, 240), new Point(640, 480) };
	}

	@Override
	public boolean initialize(final SourceConfiguration configs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pauseStream() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resumeStream() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean startStream() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopStream() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deInitialize() {
		// TODO Auto-generated method stub
		
	}

}
