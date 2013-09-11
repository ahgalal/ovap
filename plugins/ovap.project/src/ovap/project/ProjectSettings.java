/**
 * 
 */
package ovap.project;


/**
 * @author Creative
 *
 */
public enum ProjectSettings{
	
	PROJECT_DESCRIPTION ("ovap.project.info.description"),
	PROJECT_AUTHOR("ovap.project.info.author");
	
	private String value;
	private ProjectSettings(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}