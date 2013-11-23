/**
 * 
 */
package ovap.video;

/**
 * @author Creative
 */
public class Parameter {
	private final String	id;
	private final String	name;
	private Object			value;

	public Parameter(final String id, final String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(final Object param) {
		if (param instanceof Parameter) {
			if (id.equals(((Parameter) param).getId())
					&& name.equals(((Parameter) param).getName()))
				return true;
		}
		return false;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return id + ": " + name + ", " + value;
	}
}
