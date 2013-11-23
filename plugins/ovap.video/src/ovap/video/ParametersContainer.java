package ovap.video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

import utils.PDEUtils;

public class ParametersContainer {
	private final String			epId;
	private final String			inputParamElementStr;
	private final String			outputParamElementStr;
	private ArrayList<Parameter>	outputParameters;
	private Map<Parameter,Parameter>	inputParameters;
	private final String			paramIdStr;
	private final String			paramNameStr;
	private final String			pluginId;

	public ParametersContainer(final String pluginId, final String epId,
			final String inputParamElementStr,final String outputParamElementStr, final String paramIdStr,
			final String paramNameStr) {
		super();
		this.pluginId = pluginId;
		this.epId = epId;
		this.inputParamElementStr = inputParamElementStr;
		this.outputParamElementStr = outputParamElementStr;
		this.paramIdStr = paramIdStr;
		this.paramNameStr = paramNameStr;
	}
	
	public void mapActualParameter(Parameter actualParameter){
		
	}

	public Parameter getOutputParameter(final String name) {
		for (final Parameter parameter : getOutputParameters())
			if (parameter.getName().equals(name))
				return parameter;
		return null;
	}
	
	public Parameter getInputParameter(final String name) {
		for (final Parameter parameter : inputParameters.keySet())
			if (parameter.getName().equals(name))
				return inputParameters.get(parameter);
		return null;
	}

	public ArrayList<Parameter> getOutputParameters() {
		if (outputParameters != null)
			return outputParameters;
		outputParameters = new ArrayList<Parameter>();
		final IConfigurationElement[] extensions = PDEUtils.getExtensions(epId);

		for (final IConfigurationElement element : extensions) {
			if (element.getContributor().getName().equals(pluginId)) {
				for (final IConfigurationElement child : element.getChildren()) {
					if (child.getName().equals(outputParamElementStr))
						outputParameters.add(new Parameter(child
								.getAttribute(paramIdStr), child
								.getAttribute(paramNameStr)));
				}
			}
		}
		return outputParameters;
	}
	
	public boolean registerInputParameter(Parameter actualParameter){
		List<Parameter> definedInputParameters = getDefinedInputParameters();
		for(Parameter definedParameter:definedInputParameters){
			if(definedParameter.getId().equals(actualParameter.getId())){
				// FIXME: we only depend on parameter id, this may cause conflicts
				// in case of two input params having the same id (with different names)
				inputParameters.put(definedParameter, actualParameter);
				break;
			}
		}
		return false;
	}
	
	public List<Parameter> getActualInputParameters() {
		return Arrays.asList(inputParameters.values().toArray(new Parameter[0]));
	}
	public List<Parameter> getDefinedInputParameters() {
		if (inputParameters != null)
			return Arrays.asList(inputParameters.keySet().toArray(new Parameter[0]));
		inputParameters = new HashMap<Parameter, Parameter>();
		final IConfigurationElement[] extensions = PDEUtils.getExtensions(epId);

		for (final IConfigurationElement element : extensions) {
			if (element.getContributor().getName().equals(pluginId)) {
				for (final IConfigurationElement child : element.getChildren()) {
					if (child.getName().equals(inputParamElementStr))
						inputParameters.put(new Parameter(child
								.getAttribute(paramIdStr), child
								.getAttribute(paramNameStr)),null);
				}
			}
		}
		return Arrays.asList(inputParameters.keySet().toArray(new Parameter[0]));
	}
	
	
}