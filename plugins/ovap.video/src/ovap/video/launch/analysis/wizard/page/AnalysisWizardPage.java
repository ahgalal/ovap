/**
 * 
 */
package ovap.video.launch.analysis.wizard.page;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.wizard.WizardPage;

/**
 * @author Creative
 */
public abstract class AnalysisWizardPage extends WizardPage {
	private DialogSettings	settings;

	protected AnalysisWizardPage(final String pageName) {
		super(pageName);
	}

	public void setSettings(final DialogSettings settings) {
		this.settings = settings;
	}
	
	protected DialogSettings getSettings(){
		return settings;
	}
}
