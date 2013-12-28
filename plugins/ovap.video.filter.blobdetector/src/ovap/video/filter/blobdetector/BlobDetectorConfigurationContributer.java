/**
 * 
 */
package ovap.video.filter.blobdetector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.ui.FilterInputCanvas;

/**
 * @author Creative
 */
public class BlobDetectorConfigurationContributer extends
		FilterConfigurationContributer {
	private Button			btnPickBackgroundColor;
	private FilterInputCanvas			canvas;
	private Composite		cmpstColor;
	private Composite		cmpstColorContainer;
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
			// FIXME: hardcoded name
			canvas = new FilterInputCanvas(composite, SWT.BORDER,"source");
			canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
					2, 3));
		}
		{
			btnPickBackgroundColor = new Button(composite, SWT.NONE);
			btnPickBackgroundColor.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					canvas.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(final MouseEvent e) {
							final Image image = new Image(e.display, e.x, e.y);
							canvas.getGc().copyArea(image, 0, 0);
							final int intPixel = image.getImageData().getPixel(
									e.x - 1, e.y - 1);
							final RGB pixel = image.getImageData().palette
									.getRGB(intPixel);
							final int blue = pixel.blue;
							final int green = pixel.green;
							final int red = pixel.red;
							final Color color = new Color(Display.getDefault(),
									red, green, blue);
							cmpstColor.setBackground(color);
							canvas.removeMouseListener(this);
							image.dispose();

							getConfigurations().put(
									BlobDetector.BG_COLOR_CONFIG,
									red + "," + green + "," + blue);
							signalConfigurationChange();
						}
					});
				}
			});
			btnPickBackgroundColor.setText("Pick background color");
		}
		{
			cmpstColorContainer = new Composite(composite, SWT.NONE);
			cmpstColorContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
					false, false, 1, 1));
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
		final String bgColorStr = getConfigurations().get(
				BlobDetector.BG_COLOR_CONFIG);
		if (bgColorStr != null) {
			final String[] bgColorStrArray = bgColorStr.split(",");
			final int red = Integer.parseInt(bgColorStrArray[0]);
			final int green = Integer.parseInt(bgColorStrArray[1]);
			final int blue = Integer.parseInt(bgColorStrArray[2]);
			cmpstColor.setBackground(new Color(Display.getDefault(), red,
					green, blue));
		}
		canvas.setFilterManager(getFilterManager());
	}
}
