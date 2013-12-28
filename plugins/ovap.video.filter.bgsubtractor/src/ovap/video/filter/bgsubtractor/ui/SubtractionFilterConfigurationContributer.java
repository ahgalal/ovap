/**
 * 
 */
package ovap.video.filter.bgsubtractor.ui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.bgsubtractor.core.SubtractionFilter;
import ovap.video.filter.ui.FilterInputCanvas;

/**
 * @author Creative
 */
public class SubtractionFilterConfigurationContributer extends
		FilterConfigurationContributer {
	private Button				btnCaptureBackground;
	private FilterInputCanvas	canvas;

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
			// FIXME: hardcoded name
			canvas = new FilterInputCanvas(composite, SWT.BORDER, "source");
			canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
					2, 3));
		}
		{
			btnCaptureBackground = new Button(composite, SWT.NONE);
			btnCaptureBackground.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent event) {
					try {
						final String filePath = "C:\\bg"
								+ System.currentTimeMillis() + ".bmp";
						ImageIO.write(canvas.getBufferedImage(), "BMP",
								new File(filePath));
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
		canvas.setFilterManager(getFilterManager());
	}
}
