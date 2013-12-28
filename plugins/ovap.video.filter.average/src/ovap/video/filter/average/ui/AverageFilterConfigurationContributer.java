/**
 * 
 */
package ovap.video.filter.average.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.FilterConfigurationUtils;
import ovap.video.filter.average.core.Activator;

/**
 * @author Creative
 */
public class AverageFilterConfigurationContributer extends
		FilterConfigurationContributer {
	private Label	lblGrayThreshold;
	private Label	lblMaskSideLength;
	private Text	txtGrayThreshold;
	private Text	txtMaskSideLength;

	/**
	 * 
	 */
	public AverageFilterConfigurationContributer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(final Composite parent) {
		final Composite composite = new Composite(parent, 0);
		setContainer(composite);
		composite.setLayout(new GridLayout(2, false));
		{
			lblMaskSideLength = new Label(composite, SWT.NONE);
			lblMaskSideLength.setText("Mask side length (pixels):");
		}
		{
			txtMaskSideLength = new Text(composite, SWT.BORDER);
			txtMaskSideLength.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
			txtMaskSideLength.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(final ModifyEvent e) {
					getConfigurations().put(Activator.CONFIG_MASK_SIDE_LENGTH,
							txtMaskSideLength.getText());
					signalConfigurationChange();
				}
			});
		}
		{
			lblGrayThreshold = new Label(composite, SWT.NONE);
			lblGrayThreshold.setText("Gray threshold (0-255):");
		}
		{
			txtGrayThreshold = new Text(composite, SWT.BORDER);
			txtGrayThreshold.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
			txtGrayThreshold.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(final ModifyEvent e) {
					getConfigurations().put(Activator.CONFIG_GRAY_THRESHOLD,
							txtGrayThreshold.getText());
					signalConfigurationChange();
				}
			});
		}

	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.ConfigurationContributer#initializeGUI()
	 */
	@Override
	protected void initializeGUI() {
		txtMaskSideLength.setText(FilterConfigurationUtils
				.getConfigurationValue(getConfigurations(),
						Activator.CONFIG_MASK_SIDE_LENGTH,
						Activator.CONFIG_MASK_SIDE_LENGTH_DEFAULT_VALUE + ""));
		txtGrayThreshold.setText(FilterConfigurationUtils
				.getConfigurationValue(getConfigurations(),
						Activator.CONFIG_GRAY_THRESHOLD,
						Activator.CONFIG_GRAY_THRESHOLD_DEFAULT_VALUE + ""));
	}

}
