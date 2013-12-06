/**
 * 
 */
package ovap.video.launch.analysis.persist;

import java.util.Map;

import ovap.video.Parameter;

/**
 * @author Creative
 */
public class AnalysisSessionResult {
	private String					date;
	private String					description;
	private Map<Parameter,String>	parameters;
	private String					title;

	public String getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public Map<Parameter,String> getParameters() {
		return parameters;
	}

	public String getTitle() {
		return title;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setParameters(final Map<Parameter,String> parameters) {
		this.parameters = parameters;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

}
