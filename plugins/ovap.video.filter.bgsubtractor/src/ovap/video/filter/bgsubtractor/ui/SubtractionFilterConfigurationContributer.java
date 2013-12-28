/**
 * 
 */
package ovap.video.filter.bgsubtractor.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.bgsubtractor.core.SubtractionFilter;
import ovap.video.utils.ImageManipulator;

/**
 * @author Creative
 */
public class SubtractionFilterConfigurationContributer extends
		FilterConfigurationContributer {
	private Button			btnCaptureBackground;
	private BufferedImage	bufferedImage;
	private Canvas			canvas;
	private GC				gc;

	/**
	 * 
	 */
	public SubtractionFilterConfigurationContributer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(final Composite parent) {
		final Composite composite = new Composite(parent, 0);
		setContainer(composite);
		composite.setLayout(new GridLayout(3, false));
		{
			canvas = new Canvas(composite, SWT.BORDER);
			canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
					2, 3));
			canvas.addPaintListener(new PaintListener() {

				@Override
				public void paintControl(final PaintEvent e) {
					refreshCanvas();
				}
			});
		}
		{
			btnCaptureBackground = new Button(composite, SWT.NONE);
			btnCaptureBackground.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent event) {
					try {
						final String filePath = "C:\\bg"
								+ System.currentTimeMillis() + ".bmp";
						ImageIO.write(bufferedImage, "BMP", new File(filePath));
						getConfigurations().put(SubtractionFilter.BG_FILE_PATH,
								filePath);
						signalConfigurationChange();
					} catch (final IOException e) {
						e.printStackTrace();
					}
				}
			});
			btnCaptureBackground.setText("Capture Background");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ConfigurationContributer#initializeGUI()
	 */
	@Override
	protected void initializeGUI() {
		gc = new GC(canvas);
	}

	private void refreshCanvas() {
		final Point canvasSize = canvas.getSize();
		if (getFilterManager() == null) { // session is not started
			final String msg = "Stream is not started.";
			gc.drawText(msg, (canvasSize.x / 2) - (gc.textExtent(msg).x / 2),
					canvasSize.y / 2);
		} else { // session is started
			final ArrayList<BufferedImage> filterInputImages = getFilterManager()
					.getFilterInputs("source"); // FIXME: hardcoded source filter name
			bufferedImage = filterInputImages.get(0);
			final ImageData imageData = ImageManipulator
					.bufferedImageToImageData(bufferedImage);
			final Image image = new Image(Display.getDefault(), imageData);

			gc.drawImage(image, 0, 0, bufferedImage.getWidth(),
					bufferedImage.getHeight(), 0, 0, canvasSize.x, canvasSize.y);
		}
	}
}
