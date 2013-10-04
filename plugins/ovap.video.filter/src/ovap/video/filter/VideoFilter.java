package ovap.video.filter;

import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;

public abstract class VideoFilter {
	private HashMap<String, Object>	configuration = new HashMap<String, Object>();
	protected Link					linkIn, linkOut;
	protected String				name;

	public void configure(final EMap<String, String> configuration) {
		HashMap<String, Object> configs=new HashMap<String, Object>();
		for (final Entry<String, String> entry : configuration) {
			configs.put(entry.getKey(), entry.getValue());
		}
		
		configure(configs);
	}

	public void configure(final HashMap<String, Object> configurations) {
		this.configuration.putAll(configurations); // TODO: detect updated
													// params and call
													// appropriate methods to
													// handle those updated
													// params
	}

	public abstract void enable(boolean enable);

	public HashMap<String, Object> getConfiguration() {
		return configuration;
	}

	public abstract FilterData getFilterData();

	public abstract String getID();

	public abstract int getInPortCount();

	public Link getLinkIn() {
		return linkIn;
	}

	public Link getLinkOut() {
		return linkOut;
	}

	public String getName() {
		return name;
	}

	public abstract int getOutPortCount();

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
