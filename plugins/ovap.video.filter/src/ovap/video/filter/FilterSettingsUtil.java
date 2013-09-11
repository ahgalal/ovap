/**
 * 
 */
package ovap.video.filter;

import org.eclipse.core.resources.IProject;

import utils.PDEUtils;

/**
 * @author Creative
 *
 */
public class FilterSettingsUtil {
	private static final String OVAP_FILTER_NODE = "ovap.video.filter";

	public static String getFilterSetting(IProject project,FilterSettings setting){
		switch (setting) {
		case ACTIVE_GRAPH:
			return PDEUtils.getProjectSetting(project, OVAP_FILTER_NODE, setting.toString(), "");
		}
		return null;
	}

	public static void setFilterSetting(IProject project,FilterSettings setting,String value){
		PDEUtils.setProjectSetting(project, OVAP_FILTER_NODE, setting.toString(), value);
	}
	
	public static void persistSettings(IProject project){
		PDEUtils.persistSettings(project, OVAP_FILTER_NODE);
	}
}
