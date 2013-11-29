/**
 * 
 */
package ovap.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

import ovap.video.IModuleManager;
import ovap.video.Parameter;
import sys.utils.Utils;
import utils.PDEUtils;
import utils.StringUtils;

/**
 * @author Creative
 */
public class ModuleManager implements IModuleManager {
	private class ModulesRunnable implements Runnable {

		@Override
		public void run() {
			while (started) {
				Utils.sleep(30);
				synchronized (this) {
					while (paused) {
						try {
							this.wait();
						} catch (final InterruptedException e) {
							// nothing to do, as someone has just awakened this
							// thread
						}
					}
				}
				for (final Module module : activeModules)
					module.process();
			}
		}

	}

	private static ArrayList<Module>	installedModules;

	public static ArrayList<Module> getInstalledModules() {
		if (installedModules == null) {
			installedModules = new ArrayList<Module>();

			final IConfigurationElement[] config = PDEUtils
					.getExtensions(Activator.OVAP_VIDEO_MODULE_EP);
			for (final IConfigurationElement e : config) {
				final Module module = PDEUtils.instantiateExtension(
						Module.class, e);
				installedModules.add(module);
			}
		}

		return installedModules;
	}

	private ArrayList<Module>	activeModules;
	private Thread				modulesThread;

	private boolean				paused	= false;

	private boolean				started	= false;

	public ModuleManager() {
	}

	@Override
	public void initialize(HashMap<String, Object> settings) {
		activeModules = new ArrayList<Module>();
		final String moduleNamesStr = (String) settings
				.get(Activator.SETTINGS_SELECTED_MODULES_NAMES);
		String[] moduleNames = moduleNamesStr.split(Activator.DELIM_SETTINGS_REGEX);
		for (final String moduleName : moduleNames) { // TODO: check for NPE and throw exception
			final String moduleId = (String) settings.get(moduleName
					+ Activator.SETTINGS_ID_POST_FIX);
			final Module module = newModuleInstance(moduleId, moduleName);
			activeModules.add(module);
		}
		
		// configure modules
		for(Module module:activeModules){
			Map encodedConfigsMap = StringUtils.convertToInstanceConfigMap(module.getName(), settings,true);
			module.configure((HashMap<String, Object>) encodedConfigsMap);
		}
	}

	private Module newModuleInstance(final String moduleId, final String name) {
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(Activator.OVAP_VIDEO_MODULE_EP);
		for (final IConfigurationElement element : extensions) {
			final String id = element
					.getAttribute(Activator.OVAP_VIDEO_MODULE_EP_ATTR_ID);
			if (id.equals(moduleId)) {
				final Module module = PDEUtils.instantiateExtension(
						Module.class, element);
				module.setName(name);
				return module;
			}
		}
		return null;
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void registerParameters(final ArrayList<Parameter> parameters) {
		// TODO Auto-generated method stub
		for (final Parameter parameter : parameters) {
			System.out.println("Param: " + parameter.getId()
					+ " is registered in Module Manager");

			for (final Module module : activeModules) {
				module.registerInputParameter(parameter);
			}
		}
	}

	@Override
	public void resume() {
		if (paused) {
			paused = false;
			if (modulesThread != null) {
				modulesThread.interrupt();
				Utils.sleep(30);
			}
		}
	}

	@Override
	public void start() {
		modulesThread = new Thread(new ModulesRunnable(), "Modules process");
		modulesThread.start();
		started = true;
	}

	@Override
	public void stop() {
		if (started == true) {
			started = false;
			paused = false;
			modulesThread.interrupt();
			Utils.sleep(30);
		}
	}
}
