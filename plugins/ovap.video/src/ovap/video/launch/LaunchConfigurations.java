package ovap.video.launch;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

public class LaunchConfigurations {
	protected String configName;
	protected IProject project;
	
	public LaunchConfigurations(Map<String, Object> configurations) {
		setConfigName((String) configurations.get(LaunchConfigs.LAUNCH_CONFIG_NAME.toString()));
		String projectName = (String) configurations.get(LaunchConfigs.PROJECT_NAME.toString());
		setProject(ResourcesPlugin.getWorkspace().getRoot().getProject(projectName));
	}
	
	public String getConfigName() {
		return configName;
	}

	private void setConfigName(String configName) {
		this.configName = configName;
	}

	public IProject getProject() {
		return project;
	}

	private void setProject(IProject project) {
		this.project = project;
	}
}
