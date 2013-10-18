/**
 * 
 */
package ovap.video.filter.source;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import ovap.video.filter.FilterConfigurationContributer;

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
				text.addModifyListener(new ModifyListener() {
					
					@Override
					public void modifyText(ModifyEvent e) {
						getConfigurations().put("a", text.getText());
						signalConfigurationChange();
					}
				});
			}
		}

	}
	@Override
	protected void initializeGUI() {
		// FIXME: initialize group gui controls with configuration data
		text.setText(getConfigurations().get("a"));
	}
}
