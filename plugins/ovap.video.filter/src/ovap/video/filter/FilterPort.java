/**
 * 
 */
package ovap.video.filter;

/**
 * @author Creative
 */
public class FilterPort {
	public static enum PortDirection {
		IN, OUT;
	}

	private final PortDirection	direction;
	private final String		name;

	public FilterPort(final String name, final PortDirection direction) {
		super();
		this.name = name;
		this.direction = direction;
	}

	public PortDirection getDirection() {
		return direction;
	}

	public String getName() {
		return name;
	}

}
