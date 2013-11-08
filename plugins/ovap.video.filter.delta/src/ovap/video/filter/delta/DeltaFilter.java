/**
 * 
 */
package ovap.video.filter.delta;

import java.util.HashMap;

import ovap.video.filter.FilterData;
import ovap.video.filter.VideoFilter;


/**
 * @author Creative
 *
 */
public class DeltaFilter extends VideoFilter {
	private int[]	greyData, prevGreyData;
	private int[]	outputData, prevOutputData;
	private int		summation;

	private int		x1, x2, y1, y2;
	@Override
	public FilterData getFilterData() {
		// TODO Auto-generated method stub
		return null;
	}
	private int addAllPixelsValues(final int[] arr) {
		int sum = 0;
		int width = 640;
		for (int x = x1; x < x2; x++)
			for (int y = y1; y < y2; y++) {
				
				sum += arr[x + y * width] & 0x000000FF;
				if ((x == x1) || (x == x2 - 1))
					arr[x + y * width] = 0x000000FF;
			}
		return sum;
	}
	@Override
	public String getID() {
		return Activator.PLUGIN_ID;
	}

	@Override
	public String[] getInPortIDs() {
		return new String[]{"in"};
	}

	@Override
	public String[] getOutPortIDs() {
		return new String[]{"out"};
	}
	private void initializeSearchBounds() {
		x1 = 640;
		y1 = 0;
		x2 = 0;
		y2 = 480;
	}
	@Override
	protected void handleConfigurationUpdates(
			HashMap<String, Object> updatedConfigurations) {
		prevGreyData = new int[640// FIXME
		       				* 480];
		       		prevOutputData = new int[640
		       				* 480];
		       		greyData = new int[prevGreyData.length];
		       		//final boolean ret = super.configure(configs);
		       		initializeSearchBounds();		
	}
	private void getInterestingAreaBounds(final int[] arr, final int threshold) {
		initializeSearchBounds();
		int height = 480;
		int width = 640;
		for (int y = 10; y < height; y += 3) {
			for (int x1 = 10; x1 < width; x1 += 3)
				if ((arr[x1 + y * width] & 0x000000FF) > threshold) {
					if (x1 < this.x1)
						this.x1 = x1;
					for (int x2 = width - 11; x2 > x1; x2 -= 3)
						if ((arr[x2 + y * width] & 0x000000FF) > threshold)
							if (x2 > this.x2)
								this.x2 = x2;
					break;
				}
		}
	}
	@Override
	public VideoFilter newInstance(String name, String contextId) {
		final DeltaFilter deltaFilter = new DeltaFilter();
		deltaFilter.setName(name);
		return deltaFilter;
	}
	private void pseudoColors(int[] outputData2) {
		/**
		 * 0-20: 	green
		 * 21-50:	blue
		 * 51-255:	red
		 */
		int greenMin=0;
		int greenMax=20;
		int blueMin=21;
		int blueMax=50;
		int redMin=51;
		int redMax=255;
		int width = 640;// FIXME
		// we work on the interesting area only, to save processing power
		for (int x = x1; x < x2; x++)
			for (int y = y1; y < y2; y++) {
			int i = x+y*width;
			int grayVal=outputData2[i]>>16;
			if(grayVal>greenMin &&grayVal<=greenMax){
				int g=(int) (100+(grayVal/(float)greenMax)*155);
				outputData2[i]=0 | (g << 8) | (0 << 16);
			}else if(grayVal>blueMin &&grayVal<blueMax){
				int b=(int) (100+((grayVal-blueMin)/(float)(blueMax-blueMin))*155);
				outputData2[i]=b | (0 << 8) | (0 << 16);
			}else if(grayVal>redMin &&grayVal<redMax){
				int r=(int) (100+((grayVal-redMin)/(float)(redMax-redMin))*155);
				outputData2[i]=0 | (0 << 8) | (r << 16);
			}else
				outputData2[i]=0;
		}
	}
	@Override
	public void process() {
		greyData = ImageManipulator.rgbIntArray2GrayIntArray(linkIn.getData(),greyData);
		int parseInt = Integer.parseInt((String) getConfiguration().get("a"));
		outputData = ImageManipulator.subtractGreyImage(greyData, prevGreyData,parseInt);
		
		
		// tc.start();
		getInterestingAreaBounds(outputData, 20);
		// System.out.println("x1,x2 = " + x1 + " , " + x2);
		// System.out.println(tc.end());

		final int tmp = addAllPixelsValues(outputData);
		
		// pseudo coloring the image
		pseudoColors(outputData);
		if (tmp == 0)
			linkOut.setData(prevOutputData);
		else {
			summation = tmp;
			
			linkOut.setData(outputData);

			prevOutputData = outputData;
		}

		System.arraycopy(greyData, 0, prevGreyData, 0, greyData.length);
		//filterData.setWhiteSummation(summation);
		//System.out.println(summation);
	}

	@Override
	public void registerDependentData(FilterData data) {
		// TODO Auto-generated method stub
		
	}

}
