/**
 * 
 */
package ovap.video.launch.ui;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

/**
 * @author Creative
 *
 */
public class LaunchStreamTabGroup extends AbstractLaunchConfigurationTabGroup {

	/**
	 * 
	 */
	public LaunchStreamTabGroup() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog, java.lang.String)
	 */
	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		if(getTabs()==null){
			setTabs(new ILaunchConfigurationTab[]{
					new LaunchStreamMainTab(),
					new CommonTab()
			});
		}
	}

}
