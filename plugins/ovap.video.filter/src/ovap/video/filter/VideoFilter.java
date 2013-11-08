package ovap.video.filter;

import java.util.HashMap;

public abstract class VideoFilter {
	private final HashMap<String, Object>	configurations	= new HashMap<String, Object>();
	protected Link							linkIn, linkOut;
	protected String						name;
	protected boolean enabled;

	public void configure(final HashMap<String, Object> configurations) {
		final HashMap<String, Object> updatedConfigurations = new HashMap<String, Object>();
		for (final String oldKey : configurations.keySet()) {
			final Object oldValue = this.configurations.get(oldKey);
			final Object newValue = configurations.get(oldKey);
			/*
			 * it is not expected to have configs deleted/added, they are just
			 * modified
			 */
			if (oldValue ==null || !oldValue.equals(newValue))
				updatedConfigurations.put(oldKey, newValue);
		}

		handleConfigurationUpdates(updatedConfigurations);

		this.configurations.putAll(updatedConfigurations);
	}

	public void enable(boolean enable){
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
