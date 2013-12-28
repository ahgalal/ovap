/**
 * 
 */
package ovap.video.filter.basicobjectdetector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

import ovap.video.filter.FilterConfigurationUtils;
import ovap.video.filter.VideoFilter;
import ovap.video.utils.CentroidFinder;
import ovap.video.utils.marker.CrossMarker;
import ovap.video.utils.marker.Marker;
import ovap.video.utils.marker.RectangularMarker;

/**
 * @author Creative
 */
public class BasicObjectDetectorFilter extends VideoFilter {
	private static final String			PARAM_OBJECT_CENTER	= "objectCenter";
	protected final Point				centerPoint;
	protected final CentroidFinder		centroidFinder;
	private int							height;
	protected Marker					marker, marker2;
	protected int[]						outData;
	private Graphics					outputGraphics;
	private BufferedImage				outputImage;
	protected ArrayBlockingQueue<Point>	pathQueue;
	private int							pathQueueLength;

	private int							width;

	public BasicObjectDetectorFilter() {
		centroidFinder = new CentroidFinder();
		centerPoint = new Point();
		getParameter(PARAM_OBJECT_CENTER).setValue(centerPoint);
	}

	/**
	 * Draws a cross at the center of the moving object.
	 */
	protected void drawMarkerOnImg() {
		try {
			marker.draw(outData, centerPoint.x, centerPoint.y);
			marker2.draw(outData, centerPoint.x - (width / 8), centerPoint.y
					- (width / 8));
		} catch (final Exception e) {
			System.err.print("Error in marker");
			e.printStackTrace();
		}
	}

	private void drawPathOnImg() {
		if (centroidFinder.isStableCentroid()) {
			// update path queue
			if (pathQueue.size() < pathQueueLength) {
				pathQueue.add(new Point(centerPoint.x, centerPoint.y));
			} else {
				final Point head = pathQueue.poll();
				head.x = centerPoint.x;
				head.y = centerPoint.y;
				pathQueue.add(head); // added to tail
			}

			final Point prevPt = new Point(-1, -1);
			// draw path on image
			for (final Iterator<Point> it = pathQueue.iterator(); it.hasNext();) {
				final Point pt = it.next();

				if (prevPt.x != -1) {

					outputGraphics.setColor(Color.RED);
					try {
						outputGraphics.drawLine(pt.x - 1, pt.y - 1,
								prevPt.x - 1, prevPt.y - 1);
						outputGraphics.drawLine(pt.x, pt.y, prevPt.x, prevPt.y);
						outputGraphics.drawLine(pt.x + 1, pt.y + 1,
								prevPt.x + 1, prevPt.y + 1);
					} catch (final Exception e) {
						System.err
								.println("Error in path drawing, index out of range");
						e.printStackTrace();
					}

				} else {
					outputGraphics.drawLine(pt.x, pt.y, pt.x, pt.y);
				}

				prevPt.x = pt.x;
				prevPt.y = pt.y;
			}
		}
	}

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, String> updatedConfigurations) {
		width = getFrameSize().x;
		height = getFrameSize().y;

		outputImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		outData = ((DataBufferInt) outputImage.getRaster().getDataBuffer())
				.getData();
		outputGraphics = outputImage.getGraphics();

		centroidFinder.initialize(width, height);

		marker = new CrossMarker(50, 50, 5, Color.RED, width, height);

		marker2 = new RectangularMarker(width, height, width / 4, width / 4,
				Color.RED);
		if ((pathQueue == null)
				|| updatedConfigurations
						.containsKey(Activator.CONFIG_TRACK_LENGTH)) {
			pathQueueLength = FilterConfigurationUtils.getConfigurationValue(
					updatedConfigurations, Activator.CONFIG_TRACK_LENGTH,
					Activator.CONFIG_TRACK_LENGTH_DEFAULT);
			pathQueue = new ArrayBlockingQueue<Point>(pathQueueLength);
		}
	}

	@Override
	public boolean isReadyForAnalysis() {
		return true;
	}

	@Override
	public void process() {
		// final long t1 = System.currentTimeMillis();
		final int[] data = getLinkIn().getData();
		centroidFinder.updateCentroid(data, centerPoint);
		System.arraycopy(data, 0, outData, 0, data.length);
		getLinkOut().setData(outData);
		// final long t1 = System.currentTimeMillis();
		drawMarkerOnImg();
		// final long t2 = System.currentTimeMillis();
		drawPathOnImg();
		// System.out.println(t2-t1);
	}
}
