/**
 * 
 */
package ovap.video.filter.blobdetector;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.utils.ImageManipulator;

/**
 * @author Creative
 */
public class BlobDetectorConfigurationContributer extends
		FilterConfigurationContributer {
	private Button	btnPickBackgroundColor;
	private Canvas	canvas;
	private GC		gc;
	private Composite cmpstColorContainer;
	private Composite cmpstColor;

	/**
	 * 
	 */
	public BlobDetectorConfigurationContributer() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * ovap.video.filter.FilterConfigurationContributer#createControls(org.eclipse
	 * .swt.widgets.Composite)
	 */
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
			btnPickBackgroundColor = new Button(composite, SWT.NONE);
			btnPickBackgroundColor.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					canvas.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							Image image = new Image(e.display,e.x, e.y);
							gc.copyArea(image, 0, 0);
							int intPixel = image.getImageData().getPixel(e.x-1, e.y-1);
							RGB pixel = image.getImageData().palette.getRGB(intPixel);
							int blue = pixel.blue;
							int green = pixel.green; 
							int red = pixel.red; 
							Color color = new Color(Display.getDefault(), red, green, blue);
							cmpstColor.setBackground(color);
							canvas.removeMouseListener(this);
							image.dispose();
							
							getConfigurations().put(BlobDetector.BG_COLOR_CONFIG, red+","+green+","+blue);
							signalConfigurationChange();
						}
					});
				}
			});
			btnPickBackgroundColor.setText("Pick background color");
		}
		{
			cmpstColorContainer = new Composite(composite, SWT.NONE);
			cmpstColorContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			{
				cmpstColor = new Composite(cmpstColorContainer, SWT.NONE);
				cmpstColor.setBounds(23, 10, 64, 32);
			}
		}
		new Label(composite, SWT.NONE);
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.filter.FilterConfigurationContributer#initializeGUI()
	 */
	@Override
	protected void initializeGUI() {
		gc = new GC(canvas);
		String bgColorStr = getConfigurations().get(BlobDetector.BG_COLOR_CONFIG);
		if(bgColorStr!=null){
			String[] bgColorStrArray = bgColorStr.split(",");
			int red = Integer.parseInt(bgColorStrArray[0]);
			int green = Integer.parseInt(bgColorStrArray[1]);
			int blue = Integer.parseInt(bgColorStrArray[2]);
			cmpstColor.setBackground(new Color(Display.getDefault(), red, green, blue));
		}
	}

	private void refreshCanvas() {
		final Point canvasSize = canvas.getSize();
		if (getFilterManager() == null) { // session is not started
			String msg = "Stream is not started.";
			gc.drawText(msg, (canvasSize.x / 2) - gc.textExtent(msg).x/2,
					canvasSize.y / 2);
		} else { // session is started
			final ArrayList<BufferedImage> filterInputImages = getFilterManager()
					.getFilterInputs(getConfigurableName());
			BufferedImage bufferedImage = filterInputImages.get(0);
			final ImageData imageData = ImageManipulator
					.bufferedImageToImageData(bufferedImage);
			final Image image = new Image(Display.getDefault(), imageData);
			gc.drawImage(image, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), 0, 0,
					canvasSize.x, canvasSize.y);
		}
	}

	@Override
	public void show() {
		super.show();
		refreshCanvas();
	}
}
