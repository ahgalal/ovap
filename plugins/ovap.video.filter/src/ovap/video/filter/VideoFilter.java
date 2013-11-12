package ovap.video.filter;

import java.util.ArrayList;
import java.util.HashMap;

import ovap.video.Parameter;
import ovap.video.ParametersContainer;

public abstract class VideoFilter {
	private static final String				OUTPUT_PARAM_ELEMENT	= "out_parameter";
	private static final String				PARAM_ID				= "id";
	private static final String				PARAM_NAME				= "name";
	private final HashMap<String, Object>	configurations			= new HashMap<String, Object>();
	protected boolean						enabled;
	protected Link							linkIn, linkOut;
	protected String						name;
	private ParametersContainer				paramsContainer;

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

	protected Parameter getParameter(final String name) {
		return getParametersContainer().getParameter(name);
	}

	protected ArrayList<Parameter> getParameters() {
		return getParametersContainer().getParameters();
	}

	private ParametersContainer getParametersContainer() {
		if (paramsContainer == null)
			paramsContainer = new ParametersContainer(getID(),
					Activator.OVAP_FILTER_VIDEOFILTER_EP, OUTPUT_PARAM_ELEMENT,
					PARAM_ID, PARAM_NAME);
		return paramsContainer;
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
