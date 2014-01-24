/**
 * 
 */
package ovap.video;

/**
 * @author Creative
 */
public class Parameter {
	private boolean			external	= true;
	private final String	providerId;
	private final String	name;
	private Object			value;

	public Parameter(final String providerId, final String name, final boolean external) {
		super();
		this.providerId = providerId;
		this.name = name;
		this.external = external;
	}

	@Override
	public boolean equals(final Object param) {
		if (param instanceof Parameter) {
			if (providerId.equals(((Parameter) param).getId())
					&& name.equals(((Parameter) param).getName()))
				return true;
		}
		return false;
	}

	public String getId() {
		return providerId;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public boolean isExternal() {
		return external;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return providerId + ": " + name + ", " + value;
	}
}
