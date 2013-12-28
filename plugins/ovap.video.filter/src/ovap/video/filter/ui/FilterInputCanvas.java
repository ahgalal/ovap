/**
 * 
 */
package ovap.video.filter.ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import ovap.video.IFilterManager;
import ovap.video.utils.ImageManipulator;

/**
 * @author Creative
 *
 */
public class FilterInputCanvas extends FrameCanvas {

	private String	filterName;
	private IFilterManager	filterManager;

	public FilterInputCanvas(Composite parent, int style,String filterName) {
		super(parent, style);
		this.setFilterName(filterName);
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(final PaintEvent e) {
				refreshCanvas();
			}
		});
	}
	
	private void refreshCanvas() {
		final Point canvasSize = getSize();
		if (getFilterManager() == null) { // session is not started
			final String msg = "Stream is not started.";
			getGc().drawText(msg, (canvasSize.x / 2) - (getGc().textExtent(msg).x / 2),
					canvasSize.y / 2);
		} else { // session is started
			final ArrayList<BufferedImage> filterInputImages = getFilterManager()
					.getFilterInputs(getFilterName()); // FIXME: hardcoded source filter name
			setBufferedImage(filterInputImages.get(0));
			final ImageData imageData = ImageManipulator
					.bufferedImageToImageData(getBufferedImage());
			final Image image = new Image(Display.getDefault(), imageData);

			getGc().drawImage(image, 0, 0, getBufferedImage().getWidth(),
					getBufferedImage().getHeight(), 0, 0, canvasSize.x, canvasSize.y);
		}
	}

	private IFilterManager getFilterManager() {
		return filterManager;
	}
	
	public void setFilterManager(IFilterManager iFilterManager) {
		this.filterManager = iFilterManager;
	}

	public String getFilterName() {
		return filterName;
	}

	private void setFilterName(String filterName) {
		this.filterName = filterName;
	}

}
