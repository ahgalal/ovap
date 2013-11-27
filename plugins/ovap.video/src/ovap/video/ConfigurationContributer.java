package ovap.video;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

public abstract class ConfigurationContributer {

	protected ArrayList<ConfigurationChangeListener>	changeListeners;
	private Map<String, String>	configuration;
	private Composite	container;
	private String	configurableName;

	public ConfigurationContributer() {
		super();
	}

	public void addChangeListener(final ConfigurationChangeListener listener) {
		if (!changeListeners.contains(listener))
			changeListeners.add(listener);
	}

	public abstract void createControls(final Composite parent);

	public Map<String, String> getConfigurations() {
		return configuration;
	}

	public Composite getContainer() {
		return container;
	}

	public void show() {
		container.setVisible(true);
	}

	public void hide() {
		container.setVisible(false);
	}

	protected abstract void initializeGUI();

	//public abstract boolean isAcceptableElement(final EObject eObject);

	public void setConfigurations(final Map<String, String> configMap, String configurableName) {
		this.configuration = configMap;
		setConfigurableName(configurableName);
		initializeGUI();
	}

	protected void setContainer(final Composite container) {
		this.container = container;
	}

	protected void signalConfigurationChange() {
		for (final ConfigurationChangeListener listener : changeListeners)
			listener.signalConfigurationChange(this);
	}

	public String getConfigurableName() {
		return configurableName;
	}

	public void setConfigurableName(String configurableName) {
		this.configurableName = configurableName;
	}

}