package ovap.video;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;

import utils.PDEUtils;

public class ParametersContainer{
	private ArrayList<Parameter>			parameters;
	private String pluginId;
	private String epId;
	public ParametersContainer(String pluginId,String epId, String paramElementStr,
			String paramIdStr, String paramNameStr) {
		super();
		this.pluginId = pluginId;
		this.epId=epId;
		this.paramElementStr = paramElementStr;
		this.paramIdStr = paramIdStr;
		this.paramNameStr = paramNameStr;
	}
	private String paramElementStr;
	private String paramIdStr;
	private String paramNameStr;
	public ArrayList<Parameter> getParameters() {
		if (parameters != null)
			return parameters;
		parameters = new ArrayList<Parameter>();
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(epId);

		for (final IConfigurationElement element : extensions) {
			if (element.getContributor().getName().equals(pluginId)) {
				for (final IConfigurationElement child : element.getChildren()) {
					if (child.getName().equals(paramElementStr))
						parameters.add(new Parameter(child
								.getAttribute(paramIdStr), child
								.getAttribute(paramNameStr)));
				}
			}
		}
		return parameters;
	}
	public Parameter getParameter(String name){
		for(Parameter parameter:getParameters())
			if(parameter.getName().equals(name))
				return parameter;
		return null;
	}
}