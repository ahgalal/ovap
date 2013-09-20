/**
 * 
 */
package ovap.video.source;

import java.util.Map;


/**
 * @author Creative
 *
 */
public class SourceFileConfiguration extends SourceConfiguration {
	public SourceFileConfiguration(Map<String, Object> configurations) {
		super(configurations);
		String fileName = (String) configurations.get(SourceLaunchConfigs.FILE_PATH.toString());
		setFileName(fileName);
	}

	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
