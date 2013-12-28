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
	private static final String				PORT_ELEMENT	= "port";
	private static final String				PORT_NAME_ATTRIBUTE	= "name";
	private static final String				PORT_DIRECTION_ATTRIBUTE	= "direction";
	private static final String				PORT_DIRECTION_IN_ATTRIBUTE	= "In";
	private static final String				PORT_DIRECTION_OUT_ATTRIBUTE	= "Out";
	private static final String				PARAM_ID				= "id";
	private static final String				PARAM_NAME				= "name";
	private final HashMap<String, String>	configurations			= new HashMap<String, String>();
	protected String						contextId;
	protected boolean						enabled;
	private final Point						frameSize;
	protected Link							linkIn, linkOut;
	protected String						name;
	private ParametersContainer				paramsContainer;

	public VideoFilter() {
		frameSize = new Point();
	}
	
	private int[] bypassData;
	
	protected void bypass(){
		int[] data = getLinkIn().getData();
		System.arraycopy(data, 0, bypassData, 0, data.length);
		getLinkOut().setData(bypassData);
	}
	
	public abstract boolean isReadyForAnalysis();

	public void configure(final HashMap<String, String> configurations,
			final Map<String, Object> dynamicConfigurations) {
		final HashMap<String, String> updatedConfigurations = new HashMap<String, String>();
		for (final String key : configurations.keySet()) {
			final String oldValue = this.configurations.get(key);
			final String newValue = configurations.get(key);
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
			bypassData = new int[frameSize.x*frameSize.y];
		}
		// notify class extenders about the config changes
		handleConfigurationUpdates(updatedConfigurations);

		// apply configs
		this.configurations.putAll(updatedConfigurations);
	}

	public void enable(final boolean enable) {
		enabled = enable;
	}

	public HashMap<String, String> getConfiguration() {
		return configurations;
	}

	protected Point getFrameSize() {
		return frameSize;
	}

	public static String getID(IConfigurationElement element) {
		return element.getAttribute("id");
	}
	
	public String getID() {
		IConfigurationElement element = getFilterExtension();
		return getID(element);
	}
	
	private IConfigurationElement getFilterExtension(){
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(Activator.OVAP_FILTER_VIDEOFILTER_EP);

		for (final IConfigurationElement element : extensions) {
			if (element.getAttribute("class").equals(
					getClass().getCanonicalName())) {
				return element;
			}
		}
		return null;
	}

	public String[] getInPortIDs(){
		return getInPortIDs(getFilterExtension());
	}
	
	public static String[] getInPortIDs(IConfigurationElement element){
		return getPortIDs(PORT_DIRECTION_IN_ATTRIBUTE,element);
	}
	
	public static String[] getOutPortIDs(IConfigurationElement element){
		return getPortIDs(PORT_DIRECTION_OUT_ATTRIBUTE,element);
	}
	
	public static String[] getPortIDs(String direction,IConfigurationElement element ){
		ArrayList<String> portNames = new ArrayList<String>();

		IConfigurationElement[] portElements = element.getChildren(PORT_ELEMENT);
		for(IConfigurationElement portElement:portElements){
			if(portElement.getAttribute(PORT_DIRECTION_ATTRIBUTE).equals(direction))
				portNames.add(portElement.getAttribute(PORT_NAME_ATTRIBUTE));
		}

		return portNames.toArray(new String[0]);
	}

	public Link getLinkIn() {
		return linkIn;
	}

	public Link getLinkOut() {
		return linkOut;
	}

	public String getName() {
		return name;
	}

	public String[] getOutPortIDs(){
		return getOutPortIDs(getFilterExtension());
	}

	protected Parameter getParameter(final String name) {
		return getParametersContainer().getOutputParameter(name);
	}

	protected ArrayList<Parameter> getParameters() {
		return getParametersContainer().getOutputParameters();
	}

	private ParametersContainer getParametersContainer() {
		if (paramsContainer == null)
			paramsContainer = new ParametersContainer(getID(),
					Activator.OVAP_FILTER_VIDEOFILTER_EP,null, OUTPUT_PARAM_ELEMENT,
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
			final HashMap<String, String> updatedConfigurations);

	public void initialize(String name, String sessionName){
		setName(name);
		setContextId(sessionName);
	}

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
