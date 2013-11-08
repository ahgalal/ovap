/**
 * 
 */
package ovap.video.launch.ui;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

/**
 * @author Creative
 */
public class LaunchStreamTabGroup extends AbstractLaunchConfigurationTabGroup {

	/**
	 * 
	 */
	public LaunchStreamTabGroup() {
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse
	 * .debug.ui.ILaunchConfigurationDialog, java.lang.String)
	 */
	@Override
	public void createTabs(final ILaunchConfigurationDialog dialog,
			final String mode) {
		if (getTabs() == null) {
			setTabs(new ILaunchConfigurationTab[] { new LaunchStreamMainTab(),
					new CommonTab() });
		}
	}
	
	
	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		// FIXME: we can override this method in order to remove the [save filters] button
		// in the Filters tab, by making the filters tab implement an interface containing the API
		// "applyPressed()" which will contain code to save the EMF filters model instead of the
		// [save filters]
		super.performApply(configuration);
	}
}
