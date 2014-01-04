/**
 * 
 */
package ovap.video.filter.drawshape;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;

import ovap.video.Parameter;
import ovap.video.filter.VideoFilter;
import ovap.video.filter.ui.shape.Shape;

/**
 * @author Creative
 */
public class DrawShapeFilter extends VideoFilter {

	private ArrayList<Shape>	shapes;
	private BufferedImage		bufferedImage;
	private int[]				imageData;

	/**
	 * 
	 */
	public DrawShapeFilter() {
		shapes = new ArrayList<Shape>();
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.filter.VideoFilter#isReadyForAnalysis()
	 */
	@Override
	public boolean isReadyForAnalysis() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * ovap.video.filter.VideoFilter#handleConfigurationUpdates(java.util.HashMap
	 * )
	 */
	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, String> updatedConfigurations) {
		if ((bufferedImage.getWidth() != getFrameSize().x)
				|| (bufferedImage.getHeight() != getFrameSize().y)) {
			bufferedImage = new BufferedImage(getFrameSize().x,
					getFrameSize().y, BufferedImage.TYPE_INT_RGB);
			imageData = ((DataBufferInt) bufferedImage.getRaster()
					.getDataBuffer()).getData();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.filter.VideoFilter#process()
	 */
	@Override
	public void process() {
		final Parameter shapesParam = getInputParameter("shapes");
		shapes = (ArrayList<Shape>) shapesParam.getValue();
		
		final int[] data = getLinkIn().getData();
		
		// copy input image
		System.arraycopy(data, 0, imageData, 0, data.length);
		
		// draw shapes
		for(final Shape shape:shapes){
			shape.draw(bufferedImage.getGraphics());
		}
		
		getLinkOut().setData(imageData);
	}
}
