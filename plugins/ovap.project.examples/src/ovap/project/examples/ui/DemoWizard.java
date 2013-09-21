/**
 * 
 */
package ovap.project.examples.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import ovap.project.OVAPProjectUtils;
import ovap.project.examples.Activator;
import utils.FileUtils;

/**
 * @author Creative
 *
 */
public class DemoWizard extends Wizard implements INewWizard {
	private DemoWizardMainPage mainPage;
	/**
	 * 
	 */
	public DemoWizard() {
		mainPage = new DemoWizardMainPage();
		addPage(mainPage);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		String projectDescription="Demo OVAP Project";
		String projectAuthor="OVAP";
		String projectName=mainPage.getProjectName();
		IProject project = OVAPProjectUtils.createOVAPProject(projectDescription, projectAuthor, projectName);
		
		Path filterGraphFilePath = new Path("res/demo"); 
		URL url = FileLocator.find(Platform.getBundle(Activator.PLUGIN_ID), filterGraphFilePath,null);
		URL resolvedURL = null;
		try {
			resolvedURL = FileLocator.resolve(url);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		FileUtils.copyFiles(new File(resolvedURL.getFile()), project);
		
		return true;
	}

}
