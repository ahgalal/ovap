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
import ovap.video.utils.ImageManipulator;
import ovap.video.utils.ImageManipulator.Blob;
import ovap.video.utils.ImageManipulator.BlobFinder;
import ovap.video.utils.ImageManipulator.RGB;
import ovap.video.utils.marker.CrossMarker;

/**
 * @author Creative
 */
public class BlobDetector extends VideoFilter {

	private BlobFinder	blobFinder;
	private CrossMarker	crossMarker;
	private int[]		outData;
	private static final String PARAMETER_BLOBS = "blobsLocations";

	public BlobDetector() {
		super("", "");
	}

	public BlobDetector(final String name, final String contextId) {
		super(name, contextId);
		blobFinder = new BlobFinder();
		blobsLocations = new ArrayList<Point>();
	}

	@Override
	public String[] getInPortIDs() {
		return new String[] { "in" };
	}

	@Override
	public String[] getOutPortIDs() {
		return new String[] { "out" };
	}

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, Object> updatedConfigurations) {
		final Point size = getFrameSize();
		blobFinder.initialize(size.x, size.y);
		outData = new int[size.x * size.y];
		crossMarker = new CrossMarker(20, 20, 4, Color.RED, getFrameSize().x,
				getFrameSize().x);
	}

	@Override
	public VideoFilter newInstance(final String name, final String contextId) {
		return new BlobDetector(name, contextId);
	}
	private ArrayList<Point> blobsLocations; 
	@Override
	public void process() {
		final int[] inImage = getLinkIn().getData();

		// copy in data to output buffer
		System.arraycopy(inImage, 0, outData, 0, inImage.length);

		// remove background FIXME: make background color adjustable through gui configurations
		ImageManipulator.filterImageRGB(inImage, outData, new RGB(0, 0, 255),new RGB(50, 50, 25));
		
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
		
		System.out.println("blobs:" + blobs.size());
		
		getLinkOut().setData(outData);
	}
}