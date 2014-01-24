package ovap.video.module;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.IConfigurationElement;

import ovap.video.Parameter;
import ovap.video.ParametersContainer;
import ovap.video.StreamInfo;
import utils.PDEUtils;

public abstract class Module {
	private final HashMap<String, Object>	configurations	= new HashMap<String, Object>();
	private String							name;
	private ParametersContainer				parametersContainer;
	//private StreamInfo streamInfo;

//	protected StreamInfo getStreamInfo() {
//		return streamInfo;
//	}
	public static String getID(IConfigurationElement element) {
		return element.getAttribute("id");
	}
	
	public String getID() {
		IConfigurationElement element = getFilterExtension();
		return getID(element);
	}
	
	private IConfigurationElement getFilterExtension(){
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(Activator.OVAP_VIDEO_MODULE_EP);

		for (final IConfigurationElement element : extensions) {
			if (element.getAttribute("class").equals(
					getClass().getCanonicalName())) {
				return element;
			}
		}
		return null;
	}
	
	public void configure(final HashMap<String, Object> configurations) {
		final HashMap<String, Object> updatedConfigurations = new HashMap<String, Object>();
		for (final String key : configurations.keySet()) {
			final Object oldValue = this.configurations.get(key);
			final Object newValue = configurations.get(key);
			/*
			 * it is not expected to have configs deleted/added, they are just
			 * modified
			 */
			if ((oldValue == null) || !oldValue.equals(newValue))
				updatedConfigurations.put(key, newValue);
		}

		// notify class extenders about the config changes
		handleConfigurationUpdates(updatedConfigurations);

		// apply configs
		this.configurations.putAll(updatedConfigurations);
	}

	public HashMap<String, Object> getConfiguration() {
		return configurations;
	}

	protected Parameter getInputParameter(final String name) {
		return getParametersContainer().getInputParameter(name);
	}

	public ArrayList<Parameter> getInputParameters() {
		return (ArrayList<Parameter>) getParametersContainer()
				.getDefinedInputParameters();
	}

	public String getName() {
		return name;
	}

	protected Parameter getOutputParameter(final String name) {
		return getParametersContainer().getOutputParameter(name);
	}

	public ArrayList<Parameter> getOutputParameters() {
		return getParametersContainer().getOutputParameters();
	}

	protected ParametersContainer getParametersContainer() {
		if (parametersContainer == null){
				parametersContainer = new ParametersContainer(getID(), defineInParameters(), defineOutParameters());
//			parametersContainer = new ParametersContainer(getID(),
//					Activator.OVAP_VIDEO_MODULE_EP,
//					Activator.INPUT_PARAM_ELEMENT,
//					Activator.OUTPUT_PARAM_ELEMENT, Activator.PARAM_ID,
//					Activator.PARAM_NAME);
		}
		return parametersContainer;
	}

	protected abstract String[] defineInParameters();
	protected abstract String[] defineOutParameters();
	protected abstract void handleConfigurationUpdates(
			HashMap<String, Object> updatedConfigurations);

	public abstract void process();

	public void registerInputParameter(final Parameter parameter) {
		getParametersContainer().registerInputParameter(parameter);
	}

	public void setName(final String name) {
		this.name = name;
	}

//	public void setStreamInfo(StreamInfo streamInfo) {
//		this.streamInfo = streamInfo;
//	}
}
