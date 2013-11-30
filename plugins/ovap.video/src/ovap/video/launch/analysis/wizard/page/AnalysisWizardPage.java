/**
 * 
 */
package ovap.video.launch.analysis.wizard.page;

import org.eclipse.jface.wizard.WizardPage;

import ovap.video.launch.DialogSettings2;

/**
 * @author Creative
 */
public abstract class AnalysisWizardPage extends WizardPage {
	private DialogSettings2	settings;

	protected AnalysisWizardPage(final String pageName) {
		super(pageName);
	}

	public void setSettings(final DialogSettings2 settings) {
		this.settings = settings;
	}
	
	public DialogSettings2 getSettings(){
		return settings;
	}
	@Override
	public boolean isPageComplete() {
		String validateInputResultStr = validateInput();
		if(validateInputResultStr!=null){
			setErrorMessage(validateInputResultStr);
			return false;
		}else
			setErrorMessage(null);
		return super.isPageComplete();
	}
	public abstract String validateInput();
	
	public abstract void updateSettingsFromGUI();
}
