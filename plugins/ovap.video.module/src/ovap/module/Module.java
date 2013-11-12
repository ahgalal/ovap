package ovap.module;

import java.util.ArrayList;

import ovap.video.Parameter;
import ovap.video.ParametersContainer;

public abstract class Module {
	private static final String		INPUT_PARAM_ELEMENT	= "in_parameter";
	private static final String		PARAM_ID			= "id";
	private static final String		PARAM_NAME			= "name";
	private ParametersContainer inParametersContainer;
	private ParametersContainer outParametersContainer;
	public abstract String getID();
	
	protected Parameter getInputParameter(final String name) {
		return getInParametersContainer().getParameter(name);
	}

	protected ArrayList<Parameter> getInputParameters() {
		return getInParametersContainer().getParameters();
	}
	
	protected Parameter getOutputParameter(final String name) {
		return getOutParametersContainer().getParameter(name);
	}

	protected ArrayList<Parameter> getOutputParameters() {
		return getOutParametersContainer().getParameters();
	}

	public ParametersContainer getInParametersContainer() {
		if(inParametersContainer==null)
			inParametersContainer=new ParametersContainer(getID(), Activator.OVAP_VIDEO_MODULE_EP, INPUT_PARAM_ELEMENT, PARAM_ID, PARAM_NAME);
		return inParametersContainer;
	}
	
	public ParametersContainer getOutParametersContainer() {
		if(outParametersContainer==null)
			outParametersContainer=new ParametersContainer(getID(), Activator.OVAP_VIDEO_MODULE_EP, INPUT_PARAM_ELEMENT, PARAM_ID, PARAM_NAME);
		return outParametersContainer;
	}

}
