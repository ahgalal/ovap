package ovap.video.launch.analysis.wizard;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;

import ovap.video.Activator;
import ovap.video.launch.DialogSettings2;
import ovap.video.launch.analysis.wizard.page.AnalysisSessionInfoPage;
import ovap.video.launch.analysis.wizard.page.AnalysisWizardPage;
import ovap.video.launch.analysis.wizard.page.SaveFilesPage;
import ovap.video.launch.model.OVAPLaunch;
import utils.FileUtils;
import utils.PDEUtils;

public class AnalysisWizard extends Wizard {
	private AnalysisSessionInfoPage	basicInfoPage;

	private final DialogSettings2	dialogSettings;

	private final OVAPLaunch		launch;

	private SaveFilesPage		saveSettingsPage;

	public AnalysisWizard(final OVAPLaunch launch) {
		this.launch = launch;
		setWindowTitle("New Wizard");
		dialogSettings = new DialogSettings2("ovap.video.analysis.wizard");
	}

	@Override
	public void addPages() {
		basicInfoPage = new AnalysisSessionInfoPage();
		addPage(basicInfoPage);

		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(Activator.ANALYSIS_WIZARD_PAGE_EP);
		for (final IConfigurationElement element : extensions) {
			final WizardPage page = PDEUtils.instantiateExtension(
					WizardPage.class, element);
			addPage(page);
		}

		saveSettingsPage = new SaveFilesPage();
		addPage(saveSettingsPage);

		notifyPageSettings();
	}

	@Override
	public IWizardPage getNextPage(final IWizardPage page) {
		if (page instanceof AnalysisWizardPage) {
			final AnalysisWizardPage analysisWizardPage = (AnalysisWizardPage) page;
			analysisWizardPage.updateSettingsFromGUI();
		}
		return super.getNextPage(page);
	}

	public IProject getProject() {
		String projectName = null;
		try {
			projectName = launch.getLaunchConfiguration().getAttribute(
					Activator.SETTING_PROJECT_NAME, "");
		} catch (final CoreException e) {
			e.printStackTrace();
		}
		final IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectName);
		return project;
	}

	public void loadSettings(final File file) {
		try {
			dialogSettings.load(file.getAbsolutePath());
		} catch (final IOException e) {
			e.printStackTrace();
		}

		notifyPageSettings();
	}

	private void notifyPageSettings() {
		for (final IWizardPage page : getPages()) {
			if (page instanceof AnalysisWizardPage) {
				((AnalysisWizardPage) page).setSettings(dialogSettings);
			}
		}
	}

	@Override
	public boolean performFinish() {
		// store session's results file to settings
		String resultsSaveFilePath = saveSettingsPage.getResultsSaveFilePath();
		dialogSettings.put(Activator.SETTING_SESSION_RESULTS_FILE, resultsSaveFilePath);
		
		final String saveFilePath = saveSettingsPage.getSettingsSaveFilePath();
		if (!saveFilePath.isEmpty()) {
			final IProject project = getProject();
			final IFile iFile = project.getFile(saveFilePath);
			if (!iFile.exists())
				FileUtils.createFile(iFile);

			final String fileAbsPath = iFile.getLocation().toOSString();
			final File file = new File(fileAbsPath);
			saveSettings(file);
		}

		launch.startAnalysisTarget(dialogSettings.getAttributes());
		return true;
	}

	public void saveSettings(final File file) {
		try {
			dialogSettings.save(file.getAbsolutePath());
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
