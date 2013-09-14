/**
 * 
 */
package ovap.module.testmodule;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

import ovap.module.Module;

/**
 * @author Creative
 *
 */
public class TestModule extends Module {
private static int a=0;
	/**
	 * 
	 */
	public TestModule() {

	}

	@Override
	public String toString() {
		
		
		// TODO Auto-generated constructor stub
		IEclipsePreferences prefs =
		    //Platform.getPreferencesService().getRootNode().node(Plugin.PLUGIN_PREFEERENCES_SCOPE).node(MY_PLUGIN_ID);
		    new InstanceScope().getNode(Activator.PLUGIN_ID); // does all the above behind the scenes

		  prefs.put("X", ""+a++);
		  IPath path = Activator.getDefault().getStateLocation();
		    try {
				prefs.flush();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return this.getClass().toString();
	}
}
