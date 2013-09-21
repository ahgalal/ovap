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
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author Creative
 *
 */
public class OVAPProjectUtils {
	public static IProject createOVAPProject(String projectDescription,
			String projectAuthor, String projectName) {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
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
				
				projectNode.put("ovap.project.info.description", projectDescription);
				
				projectNode.put("ovap.project.info.author", projectAuthor);
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
		return project;
	}
}
