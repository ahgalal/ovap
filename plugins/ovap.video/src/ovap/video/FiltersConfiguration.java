/**
 * 
 */
package ovap.video;

import org.eclipse.core.resources.IProject;

/**
 * @author Creative
 *
 */
public class FiltersConfiguration {
	protected String filterGraphResourcePath;
	protected FrameData frameData;
	protected IProject project;
	protected String configName;
	
	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public FrameData getFrameData() {
		return frameData;
	}

	public void setFrameData(FrameData frameData) {
		this.frameData = frameData;
	}

	public String getFilterGraphResourcePath() {
		return filterGraphResourcePath;
	}

	public void setFilterGraphResourcePath(String filterGraphResourcePath) {
		this.filterGraphResourcePath = filterGraphResourcePath;
	}
}
