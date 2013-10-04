/**
 * 
 */
package ovap.video.filter;

import java.util.Map;

import ovap.video.launch.LaunchConfigurations;

/**
 * @author Creative
 *
 */
public class FiltersLaunchConfigurations extends LaunchConfigurations {
	protected String filterGraphResourcePath;
	
	public FiltersLaunchConfigurations(Map<String, Object> configurations) {
		super(configurations);
		setFilterGraphResourcePath((String) configurations.get(FilterLaunchConfigs.FILTER_GRAPH.toString()));
	}

	public String getFilterGraphResourcePath() {
		return filterGraphResourcePath;
	}

	private void setFilterGraphResourcePath(String filterGraphResourcePath) {
		this.filterGraphResourcePath = filterGraphResourcePath;
	}
}
