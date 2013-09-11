/**
 * 
 */
package ovap.video.filter;

/**
 * @author Creative
 *
 */
public enum FilterSettings {
	
	ACTIVE_GRAPH("ovap.video.filter.activegraph");
	
	private String value;
	private FilterSettings(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
