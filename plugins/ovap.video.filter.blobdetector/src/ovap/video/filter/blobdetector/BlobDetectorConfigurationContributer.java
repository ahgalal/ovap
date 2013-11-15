/**
 * 
 */
package ovap.video.filter.blobdetector;

import org.eclipse.swt.widgets.Composite;

import ovap.video.filter.FilterConfigurationContributer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.GridData;

/**
 * @author Creative
 *
 */
public class BlobDetectorConfigurationContributer extends
		FilterConfigurationContributer {
	private Button btnPickBackgroundColor;
	private Canvas canvas;

	/**
	 * 
	 */
	public BlobDetectorConfigurationContributer() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see ovap.video.filter.FilterConfigurationContributer#createControls(org.eclipse.swt.widgets.Composite)
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(Composite parent) {
		Composite composite = new Composite(parent, 0);
		setContainer(composite);
		composite.setLayout(new GridLayout(3, false));
		{
			canvas = new Canvas(composite, SWT.BORDER);
			canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
		}
		{
			btnPickBackgroundColor = new Button(composite, SWT.NONE);
			btnPickBackgroundColor.setText("Pick background color");
		}
		new Label(composite, SWT.NONE);
	}

	/* (non-Javadoc)
	 * @see ovap.video.filter.FilterConfigurationContributer#initializeGUI()
	 */
	@Override
	protected void initializeGUI() {
		// TODO Auto-generated method stub

	}

}
