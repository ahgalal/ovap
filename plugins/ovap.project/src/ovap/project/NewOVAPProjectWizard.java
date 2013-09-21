/**
 * 
 */
package ovap.project;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import ovap.project.ui.NewProjectPageA;

/**
 * @author Creative
 *
 */
public class NewOVAPProjectWizard extends Wizard implements INewWizard {
private NewProjectPageA pageA;
	/**
	 * 
	 */
	public NewOVAPProjectWizard() {
		pageA = new NewProjectPageA();
		addPage(pageA);
		// TODO Auto-generated constructor stub
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
		String projectDescription = pageA.getProjectDescription();
		String projectAuthor = pageA.getProjectAuthor();
		String projectName = pageA.getProjectName();
		
		OVAPProjectUtils.createOVAPProject(projectDescription, projectAuthor, projectName);
		return true;
	}
}
