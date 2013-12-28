package ovap.video.utils;

import java.awt.Point;

public class CentroidFinder {
	private static final int	CENTROID_HISTORY_SIZE	= 3;
	private static final int	SMALLEST_WHITE_AREA		= 5;
	private final Point[]		centroidHistory			= new Point[CENTROID_HISTORY_SIZE];
	private int					framesRemainingReliableCentroid;
	private int					height;
	private int[]				horiSum;
	private int					searchSideLength		= 600;
	private int					tmpMax;
	private int[]				vertSum;
	private int					width;

	public void initialize(final int width, final int height) {
		this.width = width;
		this.height = height;
		horiSum = new int[height];
		vertSum = new int[width];

		for (int i = 0; i < centroidHistory.length; i++)
			centroidHistory[i] = new Point(-1, -1);
		searchSideLength = width /* / 4 */; // TODO
		framesRemainingReliableCentroid = 10;

	}

	public boolean isStableCentroid() {
		return framesRemainingReliableCentroid == 0;
	}

	private void lowPassFilterCentroidPosition(final Point centerPoint) {

		// history remains disabled till ex:10 frames elapse, this is to
		// ensure
		// the reliability of the centroid position (after ex:10 frames)
		if (framesRemainingReliableCentroid == 0) {
			if (centroidHistory[0].x == -1) { // history is not initialized
												// yet
				for (int i = 0; i < (centroidHistory.length - 1); i++) {
					centroidHistory[i].x = centerPoint.x;
					centroidHistory[i].y = centerPoint.y;
				}
			} else {
				int sumX = 0, sumY = 0;
				for (final Point p : centroidHistory) {
					sumX += p.x;
					sumY += p.y;
				}
				final int factor = 5;
				centerPoint.x = ((centerPoint.x * factor) + sumX)
						/ (centroidHistory.length + factor);
				centerPoint.y = ((centerPoint.y * factor) + sumY)
						/ (centroidHistory.length + factor);

				// update history
				for (int i = 0; i < (centroidHistory.length - 1); i++) {
					centroidHistory[i].x = centroidHistory[i + 1].x;
					centroidHistory[i].y = centroidHistory[i + 1].y;
				}
				centroidHistory[centroidHistory.length - 1].x = centerPoint.x;
				centroidHistory[centroidHistory.length - 1].y = centerPoint.y;
			}
		} else
			framesRemainingReliableCentroid--;
	}

	public void updateCentroid(final int[] tmpProcessedData,
			final Point centroid) {
		updateCentroid(tmpProcessedData, centroid, -1);
	}

	/**
	 * Updates the center point (ie: finds the location of the moving
	 * object).
	 * 
	 * @param binaryImage
	 *            input image
	 */
	public void updateCentroid(final int[] binaryImage,
			final Point centerPoint, final int label) {

		tmpMax = SMALLEST_WHITE_AREA;

		int y1, y2;

		if (centerPoint.y == 0) {
			y1 = 0;
			y2 = height;
		} else {
			y1 = (centerPoint.y - searchSideLength) < 0 ? 0 : centerPoint.y
					- searchSideLength;
			y2 = (centerPoint.y + searchSideLength) > height ? height
					: centerPoint.y + searchSideLength;
		}

		for (int y = y1; y < y2; y++) { // Horizontal Sum
			horiSum[y] = 0;
			for (int x = 0; x < width; x++)
				if (label != -1) {
					if (binaryImage[(y * width) + x] == label)
						horiSum[y] += 1;
				} else
					horiSum[y] += binaryImage[(y * width) + x] & 0xFF;
			if (horiSum[y] > tmpMax) {
				centerPoint.y = y;
				tmpMax = horiSum[y];
			}
		}

		tmpMax = SMALLEST_WHITE_AREA;

		int x1, x2;
		if (centerPoint.x == 0) {
			x1 = 0;
			x2 = width;
		} else {
			x1 = (centerPoint.x - searchSideLength) < 0 ? 0 : centerPoint.x
					- searchSideLength;
			x2 = (centerPoint.x + searchSideLength) > width ? width
					: centerPoint.x + searchSideLength;
		}

		for (int x = x1; x < x2; x++) { // Vertical Sum
			vertSum[x] = 0;
			for (int y = 0; y < height; y++)
				if (label != -1) {
					if (binaryImage[(y * width) + x] == label)
						vertSum[x] += 1;
				} else
					vertSum[x] += binaryImage[(y * width) + x] & 0xFF;

			if (vertSum[x] > tmpMax) {
				centerPoint.x = x;
				tmpMax = vertSum[x];
			}
		}

		// low pass filter on position
		lowPassFilterCentroidPosition(centerPoint);
	}
}