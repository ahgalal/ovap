/**
 * 
 */
package ovap.video.filter.source;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

import ovap.video.filter.FilterConfigurationContributer;
import org.eclipse.swt.widgets.Text;

/**
 * @author Creative
 */
public class SourceFilterConfigurationContributer extends
		FilterConfigurationContributer {
	private Composite	container;
	private Group		grpSourceFilterConfigurations;
	private Text text;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(final Composite parent) {
		container = new Composite(parent, 0);
		container.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_BACKGROUND));
		container.setLayout(new GridLayout(1, false));
		{
			grpSourceFilterConfigurations = new Group(container, SWT.NONE);
			{
				final GridData gd_grpSourceFilterConfigurations = new GridData(
						SWT.LEFT, SWT.FILL, false, false, 1, 1);
				gd_grpSourceFilterConfigurations.heightHint = 147;
				grpSourceFilterConfigurations
						.setLayoutData(gd_grpSourceFilterConfigurations);
			}
			grpSourceFilterConfigurations.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_LIST_BACKGROUND));
			grpSourceFilterConfigurations
					.setText("Source filter configurations");
			{
				text = new Text(grpSourceFilterConfigurations, SWT.BORDER);
				text.setBounds(30, 27, 76, 19);
				text.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						getConfigurations().put("a", text.getText());
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		}

	}
	@Override
	protected void initializeGUI() {
		// TODO: initialize group gui controls with configuration data
		text.setText(getConfigurations().get("a"));
	}
}
