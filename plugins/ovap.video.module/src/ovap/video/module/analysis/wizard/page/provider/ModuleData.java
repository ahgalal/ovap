package ovap.video.module.analysis.wizard.page.provider;

public class ModuleData {
	public String	id;
	public String	name;

	public ModuleData(final String name, final String id) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public boolean equals(final Object obj) {
		final ModuleData otherObj = (ModuleData) obj;
		if (!name.equals(otherObj.name) || !id.equals(otherObj.id))
			return false;

		return true;
	}
}