/**
 * 
 */
package ovap.module;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;

import ovap.video.IModuleManager;
import utils.PDEUtils;

/**
 * @author Creative
 * 
 */
public class ModuleManager implements IModuleManager/*,
		IExecutableExtensionFactory*/ {
	
	private ArrayList<Module> modules;

/*	private static ModuleManager self;
	public static ModuleManager getDefault() {
		if (self == null)
			self = new ModuleManager();
		return self;
	}*/

	public ModuleManager() {
/*		if (self != null)
			return;
		else
			self = this;*/
		
		modules=new ArrayList<Module>();

		final IConfigurationElement[] config = PDEUtils
				.getExtensions("ovap.module");
		for (final IConfigurationElement e : config) {
			final Module module = PDEUtils
					.instantiateExtension(Module.class, e);
			modules.add(module);
		}

		// TODO Auto-generated constructor stub
	}

/*	@Override
	public Object create() throws CoreException {
		return getDefault();
	}*/
}
