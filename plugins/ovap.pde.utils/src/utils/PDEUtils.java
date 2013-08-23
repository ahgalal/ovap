/**
 * 
 */
package utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author Creative
 * 
 */
public class PDEUtils {

	public static IConfigurationElement[] getExtensions(final String epID) {
		final IConfigurationElement[] configs = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(epID);
		return configs;
	}

	@SuppressWarnings("unchecked")
	public static <T> T instantiateExtension(final Class<T> type,
			final IConfigurationElement config) {
		String msg="";
		try {
			msg+="Instantiating extension: " + config.getName() + ": ";
			final Object o = config.createExecutableExtension("class");
			msg+=o;
			return (T) o;
		} catch (final Exception e) {
			System.err.println();
		}finally{
			System.out.println(msg);
		}
		return null;
	}
	
	public static void setProjectSetting(IProject project,String node,String setting,String value){
		IScopeContext projectScope = new ProjectScope(project);
		IEclipsePreferences projectNode = projectScope.getNode(node);
		if (projectNode != null) {
			projectNode.put(setting, value);
			try {
				projectNode.flush();
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}
	}
}
