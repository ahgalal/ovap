/**
 * 
 */
package ovap.video.launch.ui;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;

/**
 * @author Creative
 *
 */
public abstract class OVAPLaunchConfigurationTab extends AbstractLaunchConfigurationTab {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#canSave()
	 */
	@Override
	public boolean canSave() {
		return validateData();
	}
	protected String errorMessage;
	protected abstract boolean validateData();
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getErrorMessage()
	 */
	@Override
	public String getErrorMessage() {
		validateData();
		return errorMessage;
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug
	 * .core.ILaunchConfiguration)
	 */
	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		return validateData();
	}
}
