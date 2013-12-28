/**
 * 
 */
package ovap.video.filter.delta;

import java.util.HashMap;

import ovap.video.filter.VideoFilter;
import ovap.video.utils.ImageManipulator;

/**
 * @author Creative
 */
public class DeltaFilter extends VideoFilter {
	private int[]	greyData, prevGreyData;

	private int[]	outputData, prevOutputData;

	private int		summation;
	private int		x1, x2, y1, y2;

	private int addAllPixelsValues(final int[] arr) {
		int sum = 0;
		final int width = 640;
		for (int x = x1; x < x2; x++)
			for (int y = y1; y < y2; y++) {

				sum += arr[x + (y * width)] & 0x000000FF;
				if ((x == x1) || (x == (x2 - 1)))
					arr[x + (y * width)] = 0x000000FF;
			}
		return sum;
	}

	private void getInterestingAreaBounds(final int[] arr, final int threshold) {
		initializeSearchBounds();
		final int height = 480;
		final int width = 640;
		for (int y = 10; y < height; y += 3) {
			for (int x1 = 10; x1 < width; x1 += 3)
				if ((arr[x1 + (y * width)] & 0x000000FF) > threshold) {
					if (x1 < this.x1)
						this.x1 = x1;
					for (int x2 = width - 11; x2 > x1; x2 -= 3)
						if ((arr[x2 + (y * width)] & 0x000000FF) > threshold)
							if (x2 > this.x2)
								this.x2 = x2;
					break;
				}
		}
	}

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, String> updatedConfigurations) {
		int width = getFrameSize().x;
		int height = getFrameSize().y;
		prevGreyData = new int[width*height];
		prevOutputData = new int[width*height];
		greyData = new int[prevGreyData.length];
		// final boolean ret = super.configure(configs);
		initializeSearchBounds();
	}

	private void initializeSearchBounds() {
		x1 = 640;
		y1 = 0;
		x2 = 0;
		y2 = 480;
	}
	
	@Override
	public boolean isReadyForAnalysis() {
		return true; // FIXME: implement
	}

//	@Override
//	public VideoFilter newInstance(final String name, final String contextId) {
//		final DeltaFilter deltaFilter = new DeltaFilter(name, contextId);
//		return deltaFilter;
//	}

	@Override
	public void process() {
		greyData = ImageManipulator.rgbIntArray2GrayIntArray(linkIn.getData(),
				greyData);
		final int parseInt = Integer.parseInt((String) getConfiguration().get(
				"a"));
		outputData = ImageManipulator.subtractGreyImage(greyData, prevGreyData,
				parseInt);

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
		// filterData.setWhiteSummation(summation);
		// System.out.println(summation);
	}

	private void pseudoColors(final int[] outputData2) {
		/**
		 * 0-20: green 21-50: blue 51-255: red
		 */
		final int greenMin = 0;
		final int greenMax = 20;
		final int blueMin = 21;
		final int blueMax = 50;
		final int redMin = 51;
		final int redMax = 255;
		final int width = 640;// FIXME
		// we work on the interesting area only, to save processing power
		for (int x = x1; x < x2; x++)
			for (int y = y1; y < y2; y++) {
				final int i = x + (y * width);
				final int grayVal = outputData2[i] >> 16;
				if ((grayVal > greenMin) && (grayVal <= greenMax)) {
					final int g = (int) (100 + ((grayVal / (float) greenMax) * 155));
					outputData2[i] = 0 | (g << 8) | (0 << 16);
				} else if ((grayVal > blueMin) && (grayVal < blueMax)) {
					final int b = (int) (100 + (((grayVal - blueMin) / (float) (blueMax - blueMin)) * 155));
					outputData2[i] = b | (0 << 8) | (0 << 16);
				} else if ((grayVal > redMin) && (grayVal < redMax)) {
					final int r = (int) (100 + (((grayVal - redMin) / (float) (redMax - redMin)) * 155));
					outputData2[i] = 0 | (0 << 8) | (r << 16);
				} else
					outputData2[i] = 0;
			}
	}
}
