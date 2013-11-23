package ovap.module;

import java.util.ArrayList;

import ovap.video.Parameter;
import ovap.video.ParametersContainer;

public abstract class Module {
	private String				name;
	private ParametersContainer	parametersContainer;

	public abstract String getID();

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
		if (parametersContainer == null)
			parametersContainer = new ParametersContainer(getID(),
					Activator.OVAP_VIDEO_MODULE_EP,
					Activator.INPUT_PARAM_ELEMENT,
					Activator.OUTPUT_PARAM_ELEMENT, Activator.PARAM_ID,
					Activator.PARAM_NAME);
		return parametersContainer;
	}

	public void registerInputParameter(final Parameter parameter) {
		getParametersContainer().registerInputParameter(parameter);
	}
	
	public abstract void process(); 

	public void setName(final String name) {
		this.name = name;
	}

}
