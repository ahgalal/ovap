/**
 * 
 */
package ovap.video.filter.delta;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.setup.model.FilterInstance;

/**
 * @author Creative
 *
 */
public class DeltaFilterConfigurationContributer extends
		FilterConfigurationContributer {
	
	private Group		grpSourceFilterConfigurations;
	private Text text;
	/**
	 * 
	 */
	public DeltaFilterConfigurationContributer() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see ovap.video.filter.FilterConfigurationContributer#createControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControls(Composite parent) {
		setContainer(new Composite(parent, 0));
		getContainer().setLayout(new GridLayout(1, false));
		{
			grpSourceFilterConfigurations = new Group(getContainer(), SWT.NONE);
			{
				final GridData gd_grpSourceFilterConfigurations = new GridData(
						SWT.LEFT, SWT.FILL, false, false, 1, 1);
				gd_grpSourceFilterConfigurations.heightHint = 147;
				grpSourceFilterConfigurations
						.setLayoutData(gd_grpSourceFilterConfigurations);
			}
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

	public boolean isAcceptableElement(EObject eObject) {
		return super.isAcceptableElement(eObject) && ((FilterInstance)eObject).getType().getName().equals(Activator.PLUGIN_ID);
	};
	/* (non-Javadoc)
	 * @see ovap.video.filter.FilterConfigurationContributer#initializeGUI()
	 */
	@Override
	protected void initializeGUI() {
		if(getConfigurations().get("a")!=null)
			text.setText(getConfigurations().get("a"));
	}

}
