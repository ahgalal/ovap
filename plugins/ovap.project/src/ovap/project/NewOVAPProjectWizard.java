/**
 * 
 */
package ovap.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.osgi.service.prefs.BackingStoreException;

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
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(pageA.getProjectName());
		try {
			project.create(new NullProgressMonitor());
			project.open(null);
			IProjectDescription description = project.getDescription();
			String[] natureIds = description.getNatureIds();
			String[] updatedNatureIds = new String[natureIds.length+1];
			System.arraycopy(natureIds, 0, updatedNatureIds, 0, natureIds.length);
			updatedNatureIds[natureIds.length]=OVAPNature.ID;
			description.setNatureIds(updatedNatureIds);
			
			project.setDescription(description, null);
			
			IScopeContext projectScope = new ProjectScope(project);
			IEclipsePreferences projectNode = projectScope.getNode("ovap.project.info");
			if (projectNode != null) {
				projectNode.put("ovap.project.info.description", pageA.getProjectDescription());
				projectNode.put("ovap.project.info.author", pageA.getProjectAuthor());
				try {
					projectNode.flush();
				} catch (BackingStoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
