/**
 * 
 */
package ovap.video.filter;

/**
 * @author Creative
 *
 */
public class Parameter {
	private String id;
	private String name;
	private Object value;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Parameter(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return id + ": "+name + ", " + value;
	}
}
