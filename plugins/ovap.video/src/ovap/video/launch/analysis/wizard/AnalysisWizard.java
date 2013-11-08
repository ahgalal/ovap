package ovap.video.launch.analysis.wizard;

import org.eclipse.jface.wizard.Wizard;

import ovap.video.launch.analysis.wizard.page.AnalysisSessionInfoPage;

public class AnalysisWizard extends Wizard {

	public AnalysisWizard() {
		setWindowTitle("New Wizard");
	}

	@Override
	public void addPages() {
		addPage(new AnalysisSessionInfoPage());
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
