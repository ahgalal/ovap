package ovap.video.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class BlobFinder {

	private final CentroidFinder	centroidFinder;

	final int[][]					labelTable			= new int[200][200];	// FIXME:
																				// maximum
																				// of
																				// 40

	// labels only!, need to
	// be dynamic
	final int[]						labelTableIndices	= new int[200];

	@SuppressWarnings("unused")
	private int						width, height;

	public BlobFinder() {
		centroidFinder = new CentroidFinder();
	}

	private void addLabelToLabelTable(final int smallerLabel,
			final int largerLabel) {
		// check if largerLabel was added before as an adjacent label to
		// smallerLabel
		for (int alreadyAddedAdjacentLabelIndex = 1; alreadyAddedAdjacentLabelIndex <= labelTableIndices[smallerLabel]; alreadyAddedAdjacentLabelIndex++) {
			if (labelTable[smallerLabel][alreadyAddedAdjacentLabelIndex] == largerLabel)
				return;
		}
		final int index = labelTableIndices[smallerLabel] + 1;

		labelTable[smallerLabel][index] = largerLabel;
		labelTableIndices[smallerLabel] += 1;
		// System.out.println("added: " + largerLabel + " to:" +
		// smallerLabel + " at idx: "+index);
	}

	// FIXME: performance enhancement
	public Collection<Blob> getBlobs(final int[] image, final int xStart,
			final int xEnd, final int yStart, final int yEnd) {
		final HashMap<Integer, Blob> blobs = new HashMap<Integer, Blob>();
		final int[] labels = new int[image.length];
		int labelCounter = 1;

		resetLabelTable();

		for (int i = 0; i < image.length; i++) {
			final int pixel = image[i];
			final int xPixel = i % width;
			final int yPixel = i / width;

			if (((xPixel - 1) < xStart) || ((xPixel + 1) > (xEnd - 1))
					|| ((yPixel - 1) < yStart)
					|| ((yPixel + 1) > (yEnd - 1)))
				continue;

			if (pixel == 0x00FFFFFF) { // pixel is foreground
				// check neighboring pixels' label
				for (int yTmp = yPixel - 1; yTmp <= (yPixel + 1); yTmp++) {
					for (int xTmp = xPixel - 1; xTmp <= (xPixel + 1); xTmp++) {
						final int neighbourLabel = labels[xTmp
								+ (yTmp * width)];
						if (neighbourLabel > 0) {
							final int currentLabel = labels[i];
							if (currentLabel == 0)
								labels[i] = neighbourLabel;
							else {// this pixel has been labeled before
									// two blobs meeting
								if (currentLabel != neighbourLabel) {

									// System.out.println("Adj. Labels: " +
									// currentLabel + " & " +
									// neighbourLabel);
									int smallerLabel, largerLabel;
									// mark labels as adjacent
									if (currentLabel < neighbourLabel) {
										smallerLabel = currentLabel;
										largerLabel = neighbourLabel;
									} else {
										smallerLabel = neighbourLabel;
										largerLabel = currentLabel;
									}
									addLabelToLabelTable(smallerLabel,
											largerLabel);
									labels[i] = smallerLabel;
								}
							}
						}
					}
				}
				Blob blob = null;
				if (labels[i] == 0) { // if label is not set yet for the
										// current
										// foreground pixel, set it
					labels[i] = labelCounter;
					blob = blobs.get(labelCounter);
					if (blob == null) {
						blob = new Blob();
						blobs.put(labelCounter, blob);
					}
					labelCounter++;
				} else {
					blob = blobs.get(labels[i]);
				}
				blob.addPoint(xPixel, yPixel);
			}
		}

		// for (int keyLabelIndex = labelCounter - 1; keyLabelIndex >= 1;
		// keyLabelIndex--) {
		// String adjLabels = "";
		// for (int adjLabelIndex = 1; adjLabelIndex <=
		// labelTableIndices[keyLabelIndex]; adjLabelIndex++) {
		// final int adjLabel = labelTable[keyLabelIndex][adjLabelIndex];
		// adjLabels += adjLabel + ",";
		// }
		// System.out.println(keyLabelIndex + ": "+ adjLabels);
		// }

		final Set<Integer> labelsToRemove = new HashSet<Integer>();
		// merge blobs of adjacent labels
		for (int keyLabelIndex = labelCounter - 1; keyLabelIndex >= 1; keyLabelIndex--) {
			final int keyLabel = keyLabelIndex;

			// get key blob
			final Blob keyBlob = blobs.get(keyLabel);

			if ((keyBlob.getHeight() == 0) || (keyBlob.getWidth() == 0))
				labelsToRemove.add(keyLabel);

			// merge adjacent blobs into key blob
			for (int adjLabelIndex = 1; adjLabelIndex <= labelTableIndices[keyLabelIndex]; adjLabelIndex++) {
				final int adjLabel = labelTable[keyLabelIndex][adjLabelIndex];
				final Blob adjBlob = blobs.get(adjLabel);

				keyBlob.addPoint(adjBlob.getMaxX(), adjBlob.getMaxY());
				keyBlob.addPoint(adjBlob.getMinX(), adjBlob.getMinY());
				labelsToRemove.add(adjLabel);

				// System.out.println("adj blob to be removed: " +adjLabel
				// +" " +adjBlob );
			}
		}

		for (final int i : labelsToRemove)
			blobs.remove(i);
		return blobs.values();
	}

	public void initialize(final int width, final int height) {
		this.width = width;
		this.height = height;
		centroidFinder.initialize(width, height);
	}

	private void resetLabelTable() {
		for (int i = 0; i < labelTable.length; i++) {
			for (int j = 0; j < labelTable[i].length; j++)
				labelTable[i][j] = 0;
			labelTableIndices[i] = 0;
		}
	}

	public void updateBlobsCentroids(final int[] image,
			final int[] filteredImage, final RGB[] colors,
			final Blob[] blobs, final RGB[] thresholds) {
		final RGB rgb = new RGB(0, 0, 0);
		for (int i = 0; i < image.length; i++) {
			final int pixel = image[i];
			ImageManipulator.intToRGB(pixel, rgb);

			for (int iColor = 0; iColor < colors.length; iColor++) {
				if ((Math.abs(rgb.getR() - colors[iColor].getR()) < thresholds[iColor]
						.getR())
						&& (Math.abs(rgb.getG() - colors[iColor].getG()) < thresholds[iColor]
								.getG())
						&& (Math.abs(rgb.getB() - colors[iColor].getB()) < thresholds[iColor]
								.getB())) {
					filteredImage[i] = iColor + 1;
				}
			}
		}

		// TODO: Performance Enhancement: create a method:
		// centroidFinder.updateCentroid(filteredImage,
		// centroids_to_update[], labels[])
		for (int iColor = 0; iColor < colors.length; iColor++) {
			centroidFinder.updateCentroid(filteredImage,
					blobs[iColor].getCentroid(), iColor + 1);
		}
	}
}