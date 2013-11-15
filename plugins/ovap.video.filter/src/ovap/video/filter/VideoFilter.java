package ovap.video.filter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

import ovap.video.Parameter;
import ovap.video.ParametersContainer;
import utils.PDEUtils;

public abstract class VideoFilter {
	public static final String				FRAME_SIZE				= "ovap.video.filter.dynamic_configs.framesize";
	private static final String				OUTPUT_PARAM_ELEMENT	= "out_parameter";
	private static final String				PARAM_ID				= "id";
	private static final String				PARAM_NAME				= "name";
	private final HashMap<String, Object>	configurations			= new HashMap<String, Object>();
	protected String						contextId;
	protected boolean						enabled;
	private final Point						frameSize;
	protected Link							linkIn, linkOut;
	protected String						name;
	private ParametersContainer				paramsContainer;

	public VideoFilter(final String name, final String contextId) {
		setName(name);
		setContextId(contextId);
		frameSize = new Point();
	}

	public void configure(final HashMap<String, Object> configurations,
			final Map<String, Object> dynamicConfigurations) {
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

		// update frame size
		if (dynamicConfigurations != null) {
			final Point frameSizeConfig = (Point) dynamicConfigurations
					.get(VideoFilter.FRAME_SIZE);
			frameSize.x = frameSizeConfig.x;
			frameSize.y = frameSizeConfig.y;
		}
		// notify class extenders about the config changes
		handleConfigurationUpdates(updatedConfigurations);

		// apply configs
		this.configurations.putAll(updatedConfigurations);
	}

	public void enable(final boolean enable) {
		enabled = enable;
	}

	public HashMap<String, Object> getConfiguration() {
		return configurations;
	}

	protected Point getFrameSize() {
		return frameSize;
	}

	public String getID() {
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(Activator.OVAP_FILTER_VIDEOFILTER_EP);

		for (final IConfigurationElement element : extensions) {
			if (element.getAttribute("class").equals(
					getClass().getCanonicalName())) {
				return element.getAttribute("id");
			}
		}
		return null;
	}

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

	/**
	 * Handles configurations change, it is called before applying the new
	 * configurations.
	 * 
	 * @param updatedConfigurations
	 *            Updated filter configurations
	 */
	protected abstract void handleConfigurationUpdates(
			final HashMap<String, Object> updatedConfigurations);

	public abstract VideoFilter newInstance(String name, String contextId);

	public abstract void process();

	public void setContextId(final String contextId) {
		this.contextId = contextId;
	}

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
