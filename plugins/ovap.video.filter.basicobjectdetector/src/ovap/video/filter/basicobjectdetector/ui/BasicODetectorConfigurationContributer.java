/**
 * 
 */
package ovap.video.filter.basicobjectdetector.ui;

import org.eclipse.swt.widgets.Composite;

import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.FilterConfigurationUtils;
import ovap.video.filter.basicobjectdetector.Activator;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

/**
 * @author Creative
 *
 */
public class BasicODetectorConfigurationContributer extends
		FilterConfigurationContributer {
	private Label lblTrackHistoryLength;
	private Text txtTrackLength;

	/**
	 * 
	 */
	public BasicODetectorConfigurationContributer() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see ovap.video.ConfigurationContributer#initializeGUI()
	 */
	@Override
	protected void initializeGUI() {
		txtTrackLength.setText(""+FilterConfigurationUtils.getConfigurationValue(getConfigurations(), Activator.CONFIG_TRACK_LENGTH, Activator.CONFIG_TRACK_LENGTH_DEFAULT));
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(Composite parent) {
		Composite composite = new Composite(parent, 0);
		setContainer(composite);
		composite.setLayout(new GridLayout(2, false));
		{
			lblTrackHistoryLength = new Label(composite, SWT.NONE);
			lblTrackHistoryLength.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblTrackHistoryLength.setText("Track history length (samples):");
		}
		{
			txtTrackLength = new Text(composite, SWT.BORDER);
			txtTrackLength.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			txtTrackLength.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					getConfigurations().put(Activator.CONFIG_TRACK_LENGTH, txtTrackLength.getText());
					signalConfigurationChange();
				}
			});
		}
	}
}
