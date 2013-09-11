/**
 * 
 */
package ovap.project;

import org.eclipse.core.resources.IProject;

import utils.PDEUtils;

/**
 * @author Creative
 *
 */
public class ProjectSettingsUtil {
	private static final String OVAP_PROJECT_INFO_NODE = "ovap.project.info";

	public static String getProjectInfo(IProject project,ProjectSettings setting){
		switch (setting) {
		case PROJECT_DESCRIPTION:
			return PDEUtils.getProjectSetting(project, OVAP_PROJECT_INFO_NODE, setting.toString(), "Description");
		case PROJECT_AUTHOR:
			return PDEUtils.getProjectSetting(project, OVAP_PROJECT_INFO_NODE, setting.toString(), "Author");
		}
		return null;
	}

	public static void setProjectInfo(IProject project,ProjectSettings setting,String value){
		PDEUtils.setProjectSetting(project, OVAP_PROJECT_INFO_NODE, setting.toString(), value);
	}
	
	public static void persistSettings(IProject project){
		PDEUtils.persistSettings(project, OVAP_PROJECT_INFO_NODE);
	}
}
