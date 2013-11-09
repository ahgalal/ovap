package ovap.video.filter;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.IConfigurationElement;

import utils.PDEUtils;

public abstract class VideoFilter {
	private static final String				OUTPUT_PARAM_ELEMENT	= "output_param";
	private static final String				PARAM_ID				= "id";
	private static final String				PARAM_NAME				= "name";
	private final HashMap<String, Object>	configurations			= new HashMap<String, Object>();
	protected boolean						enabled;
	protected Link							linkIn, linkOut;
	protected String						name;
	private ArrayList<Parameter>			parameters;

	public void configure(final HashMap<String, Object> configurations) {
		final HashMap<String, Object> updatedConfigurations = new HashMap<String, Object>();
		for (final String oldKey : configurations.keySet()) {
			final Object oldValue = this.configurations.get(oldKey);
			final Object newValue = configurations.get(oldKey);
			/*
			 * it is not expected to have configs deleted/added, they are just
			 * modified
			 */
			if ((oldValue == null) || !oldValue.equals(newValue))
				updatedConfigurations.put(oldKey, newValue);
		}

		handleConfigurationUpdates(updatedConfigurations);

		this.configurations.putAll(updatedConfigurations);
	}
	
	public void enable(final boolean enable) {
		enabled = enable;
	}

	public HashMap<String, Object> getConfiguration() {
		return configurations;
	}

	public abstract FilterData getFilterData();

	public abstract String getID();

	public abstract String[] getInPortIDs();

	public Link getLinkIn() {
		return linkIn;
	}

	public Link getLinkOut() {
		return linkOut;
	}

	public String getName() {
		return name;
	}

	public abstract String[] getOutPortIDs();

	protected ArrayList<Parameter> getParameters() {
		if (parameters != null)
			return parameters;
		parameters = new ArrayList<Parameter>();
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(Activator.OVAP_FILTER_VIDEOFILTER_EP);

		for (final IConfigurationElement element : extensions) {
			if (element.getContributor().getName().equals(getID())) {
				for (final IConfigurationElement child : element.getChildren()) {
					if (child.getName().equals(OUTPUT_PARAM_ELEMENT))
						parameters.add(new Parameter(child
								.getAttribute(PARAM_ID), child
								.getAttribute(PARAM_NAME)));
				}
			}
		}
		return parameters;
	}
	
	protected Parameter getParameter(String name){
		for(Parameter parameter:getParameters())
			if(parameter.getName().equals(name))
				return parameter;
		return null;
	}

	protected abstract void handleConfigurationUpdates(
			final HashMap<String, Object> updatedConfigurations);

	public abstract VideoFilter newInstance(String name, String contextId);

	public abstract void process();

	public abstract void registerDependentData(FilterData data);

	public void setLinkIn(final Link linkIn) {
		this.linkIn = linkIn;
	}

	public void setLinkOut(final Link linkOut) {
		this.linkOut = linkOut;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
