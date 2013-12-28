/**
 * 
 */
package ovap.video.filter.bgsubtractor.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import ovap.video.FrameData;
import ovap.video.filter.VideoFilter;
import ovap.video.utils.ImageManipulator;

/**
 * @author Creative
 */
public class SubtractionFilter extends VideoFilter {

	private static final String	CONFIG_THRESHOLD	= "threshold";

	public static final String	BG_FILE_PATH	= "bg_file_path";

	private FrameData	bgImageGray;

	private int[]		localData;

	private boolean	bgSet=false;

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, String> updatedConfigurations) {
		localData = new int[getFrameSize().x * getFrameSize().y];
		
		String bgFilePath = (String) updatedConfigurations.get(BG_FILE_PATH);
		if(bgFilePath!=null && !bgFilePath.isEmpty()){
			try {
				BufferedImage image = ImageIO.read(new File(bgFilePath));
				int[] data  = new int[getFrameSize().x*getFrameSize().y];
				image.getRGB(0, 0, getFrameSize().x, getFrameSize().y, data, 0, getFrameSize().x);
				//int[] data = ((DataBufferInt)(image.getRaster().getDataBuffer())).getData();
				bgImageGray = new FrameData(getFrameSize().x, getFrameSize().y);
				setBG(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void process() {
		if(!bgSet){
			bypass();
			return;
		}
		int tmp;
		String threshold = (String) getConfiguration().get(CONFIG_THRESHOLD);
		if(threshold ==null)
			threshold="20";
		final int threshMask = Integer.parseInt(threshold);
		if (linkIn.getData().length == bgImageGray.getFrameData().length) {
			localData = ImageManipulator.rgbIntArray2GrayIntArray(
					linkIn.getData(), localData);
			tmp = 0;
			for (int i = 0; i < localData.length; i++) {
				tmp = (0x000000FF & localData[i])
						- (0x000000FF & bgImageGray.getFrameData()[i]);

				if (tmp < 0)
					tmp *= -1;

				if (tmp < threshMask)
					localData[i] = 0;
				else
					localData[i] = 0x00FFFFFF;
			}
			linkOut.setData(localData);
		}
	}
	
	private void setBG(int[] data){
		bgImageGray.setFrameData(ImageManipulator
				.rgbIntArray2GrayIntArray(data, new int[getFrameSize().x*getFrameSize().y]));
		bgSet=true;
	}

	@Override
	public boolean isReadyForAnalysis() {
		return bgSet;
	}

}
