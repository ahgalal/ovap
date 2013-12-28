/**
 * 
 */
package ovap.video.filter.blobdetector;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ovap.video.filter.VideoFilter;
import ovap.video.utils.Blob;
import ovap.video.utils.BlobFinder;
import ovap.video.utils.ImageManipulator;
import ovap.video.utils.RGB;
import ovap.video.utils.marker.CrossMarker;

/**
 * @author Creative
 */
public class BlobDetector extends VideoFilter {

	private BlobFinder	blobFinder;
	private CrossMarker	crossMarker;
	private int[]		outData;
	private static final String PARAMETER_BLOBS = "blobsLocations";
	public static final String BG_COLOR_CONFIG="background_color";

	public BlobDetector() {
		blobFinder = new BlobFinder();
		blobsLocations = new ArrayList<Point>();
	}
	@Override
	public boolean isReadyForAnalysis() {
		return true; // FIXME: implement
	}
	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, String> updatedConfigurations) {
		final Point size = getFrameSize();
		blobFinder.initialize(size.x, size.y);
		outData = new int[size.x * size.y];
		crossMarker = new CrossMarker(20, 20, 4, Color.RED, getFrameSize().x,
				getFrameSize().x);
	}

	private ArrayList<Point> blobsLocations; 
	@Override
	public void process() {
		final int[] inImage = getLinkIn().getData();

		// copy in data to output buffer
		System.arraycopy(inImage, 0, outData, 0, inImage.length);

		String backgroundColor = (String) getConfiguration().get(BG_COLOR_CONFIG);
		if(backgroundColor!=null){ // we don't process the image if bg color is not set
			String[] rgbStrArray = backgroundColor.split(",");
			int red = Integer.parseInt(rgbStrArray[0]);
			int green = Integer.parseInt(rgbStrArray[1]);
			int blue = Integer.parseInt(rgbStrArray[2]);
			// remove background FIXME: make background color adjustable through gui configurations
			ImageManipulator.filterImageRGB(inImage, outData, new RGB(red, green, blue),new RGB(50, 50, 25));

			// negate image
			ImageManipulator.negativeImage(outData, outData);

			// find blobs
			final Collection<Blob> blobs = blobFinder.getBlobs(outData, 0,
					getFrameSize().x, 0, getFrameSize().y);

			// draw a marker on each blob
			for (final Blob blob : blobs)
				crossMarker.draw(outData, blob.getCentroid().x,
						blob.getCentroid().y);

			// update parameters
			blobsLocations.clear();
			for (final Blob blob : blobs)
				blobsLocations.add(blob.getCentroid());
			getParameter(PARAMETER_BLOBS).setValue(blobsLocations);

			//System.out.println("blobs:" + blobs.size());
		}
		getLinkOut().setData(outData);
	}
}
