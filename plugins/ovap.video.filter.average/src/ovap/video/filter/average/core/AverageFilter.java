/**
 * 
 */
package ovap.video.filter.average.core;

import java.awt.Point;
import java.util.HashMap;

import ovap.video.filter.FilterConfigurationUtils;
import ovap.video.filter.FilterPort;
import ovap.video.filter.VideoFilter;
import ovap.video.filter.FilterPort.PortDirection;
import ovap.video.utils.ImageManipulator;

/**
 * @author Creative
 */
public class AverageFilter extends VideoFilter {

	private short[]				currentFrameGrayMap;

	private int[]				dataOut;
	private Point[]				effectivePixels;
	private int					height;
	private int					maskSideLength			= Activator.CONFIG_MASK_SIDE_LENGTH_DEFAULT_VALUE;
	private int					threshold				= Activator.CONFIG_GRAY_THRESHOLD_DEFAULT_VALUE;
	private int					width;

	private int getMaskAverage(final int x, final int y, final int maskArea) {
		int maskSum = 0;
		for (int xMask = x - (maskSideLength / 2); xMask < (x + (maskSideLength / 2)); xMask++)
			for (int yMask = y - (maskSideLength / 2); yMask < (y + (maskSideLength / 2)); yMask++)
				maskSum = currentFrameGrayMap[xMask + (width * yMask)]
						+ maskSum;
		return maskSum / maskArea;
	}

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, String> updatedConfigurations) {
		width = getFrameSize().x;
		height = getFrameSize().y;
		dataOut = new int[width * height];

		effectivePixels = new Point[width * height];
		for (int i = 0; i < effectivePixels.length; i++)
			effectivePixels[i] = new Point();

		maskSideLength = FilterConfigurationUtils.getConfigurationValue(updatedConfigurations,
				Activator.CONFIG_MASK_SIDE_LENGTH, Activator.CONFIG_MASK_SIDE_LENGTH_DEFAULT_VALUE);
		threshold = FilterConfigurationUtils.getConfigurationValue(updatedConfigurations,Activator.CONFIG_GRAY_THRESHOLD,
				Activator.CONFIG_GRAY_THRESHOLD_DEFAULT_VALUE);

	}

	@Override
	public boolean isReadyForAnalysis() {
		return true;
	}

	@Override
	public void process() {
		// final long t1 = System.currentTimeMillis();
		final int[] dataIn = linkIn.getData();
		int effectivePixelsNumber = 0;
		final int maskArea = maskSideLength * maskSideLength;
		for (int i = 0; i < dataOut.length; i++)
			dataOut[i] = 0;

		// form current frame's gray map (current frame is the subtraction
		// between cam frame and background, thresholded by the Subtraction
		// filter)
		currentFrameGrayMap = ImageManipulator.formGrayMapFromGrayImage(dataIn);

		// only use average filter for effective pixels to enhance
		// performance
		for (int i = 0; i < currentFrameGrayMap.length; i++) {
			final int x = i % width;
			final int y = i / width;

			if ((currentFrameGrayMap[i] < threshold)
					|| ((x + (maskSideLength / 2)) > width)
					|| ((y + (maskSideLength / 2)) > height)
					|| ((x - (maskSideLength / 2)) < 0)
					|| ((y - (maskSideLength / 2)) < 0))
				continue;
			effectivePixels[effectivePixelsNumber].x = x;
			effectivePixels[effectivePixelsNumber].y = y;
			effectivePixelsNumber++;
		}

		for (int i = 0; i < effectivePixelsNumber; i++) {
			final Point p = effectivePixels[i];
			// calculate mask's average value
			final int maskAverage = getMaskAverage(p.x, p.y, maskArea);

			// set pixel's value to the mask's avg value
			dataOut[p.x + (p.y * width)] = ImageManipulator
					.formGrayValueFromGrayIntensity((short) maskAverage);
		}

		linkOut.setData(dataOut);
		// final long t2 = System.currentTimeMillis();
		// System.out.println(t2 - t1);
	}

	@Override
	protected void definePorts() {
		inPorts = new FilterPort[]{new FilterPort("in", PortDirection.IN)};
		outPorts = new FilterPort[]{new FilterPort("out", PortDirection.OUT)};
	}

	@Override
	protected String[] defineInParameters() {
		return new String[0];
	}

	@Override
	protected String[] defineOutParameters() {
		return new String[0];
	}
	

}
