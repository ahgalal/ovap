package ovap.video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametersContainer {
	// private final String epId;
	// private final String inputParamElementStr;
	private Map<Parameter, Parameter>	inputParameters;
	// private final String outputParamElementStr;
	private ArrayList<Parameter>		outputParameters;
	private final String				paramExternal	= "external";

	// private final String paramIdStr;
	// private final String paramNameStr;
	// private final String pluginId;

//	public ParametersContainer(final String pluginId, final String epId,
//			final String inputParamElementStr,
//			final String outputParamElementStr, final String paramIdStr,
//			final String paramNameStr) {
//		super();
//		this.pluginId = pluginId;
//		this.epId = epId;
//		this.inputParamElementStr = inputParamElementStr;
//		this.outputParamElementStr = outputParamElementStr;
//		this.paramIdStr = paramIdStr;
//		this.paramNameStr = paramNameStr;
//	}

	public ParametersContainer(final String providerId,
			final String[] inParamNames, final String[] outParamNames) {
		inputParameters = new HashMap<Parameter, Parameter>();
		for (final String inParamName : inParamNames) {
			inputParameters.put(new Parameter(providerId, inParamName, true),
					null);
		}
		outputParameters = new ArrayList<Parameter>();
		for (final String outParamName : outParamNames) {
			// FIXME: support internal params (i.e. not intended for statistics,
			// but for the communication from modules to filters)
			outputParameters
					.add(new Parameter(providerId, outParamName, true));
		}
	}

	public List<Parameter> getActualInputParameters() {
		return Arrays
				.asList(inputParameters.values().toArray(new Parameter[0]));
	}

	public List<Parameter> getDefinedInputParameters() {
		//if (inputParameters != null)
			return Arrays.asList(inputParameters.keySet().toArray(
					new Parameter[0]));
//		inputParameters = new HashMap<Parameter, Parameter>();
//		final IConfigurationElement[] extensions = PDEUtils.getExtensions(epId);
//
//		for (final IConfigurationElement element : extensions) {
//			if (element.getContributor().getName().equals(pluginId)) {
//				for (final IConfigurationElement child : element.getChildren()) {
//					if (child.getName().equals(inputParamElementStr))
//						inputParameters.put(
//								new Parameter(child.getAttribute(paramIdStr),
//										child.getAttribute(paramNameStr),
//										Boolean.parseBoolean(child
//												.getAttribute(paramExternal))),
//								null);
//				}
//			}
//		}
//		return Arrays
//				.asList(inputParameters.keySet().toArray(new Parameter[0]));
	}

	public Parameter getInputParameter(final String name) {
		for (final Parameter parameter : inputParameters.keySet())
			if (parameter.getName().equals(name))
				return inputParameters.get(parameter);
		return null;
	}

	public Parameter getOutputParameter(final String name) {
		for (final Parameter parameter : getOutputParameters())
			if (parameter.getName().equals(name))
				return parameter;
		return null;
	}

	public ArrayList<Parameter> getOutputParameters() {
//		if (outputParameters != null)
			return outputParameters;
//		outputParameters = new ArrayList<Parameter>();
//		final IConfigurationElement[] extensions = PDEUtils.getExtensions(epId);
//
//		for (final IConfigurationElement element : extensions) {
//			if (element.getContributor().getName().equals(pluginId)) {
//				for (final IConfigurationElement child : element.getChildren()) {
//					if (child.getName().equals(outputParamElementStr))
//						outputParameters.add(new Parameter(child
//								.getAttribute(paramIdStr), child
//								.getAttribute(paramNameStr),
//								Boolean.parseBoolean(child
//										.getAttribute(paramExternal))));
//				}
//			}
//		}
//		return outputParameters;
	}

	public void mapActualParameter(final Parameter actualParameter) {

	}

	public boolean registerInputParameter(final Parameter actualParameter) {
		final List<Parameter> definedInputParameters = getDefinedInputParameters();
		for (final Parameter definedParameter : definedInputParameters) {
			if (definedParameter.getName().equals(actualParameter.getName())) {
				// FIXME: we only depend on parameter name, this may cause
				// conflicts
				// in case of two input params having the same name (with
				// different provider ids)
				inputParameters.put(definedParameter, actualParameter);
				break;
			}
		}
		return false;
	}

}