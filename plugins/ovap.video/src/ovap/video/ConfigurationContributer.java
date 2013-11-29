package ovap.video;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

public abstract class ConfigurationContributer {

	protected ArrayList<ConfigurationChangeListener>	changeListeners;
	private String										configurableName;

	private Map<String, String>							configuration;

	private Composite									container;

	public ConfigurationContributer() {
		changeListeners = new ArrayList<ConfigurationChangeListener>();
	}

	public void addChangeListener(final ConfigurationChangeListener listener) {
		if (!changeListeners.contains(listener))
			changeListeners.add(listener);
	}

	public abstract void createControls(final Composite parent);

	public String getConfigurableName() {
		return configurableName;
	}

	public Map<String, String> getConfigurations() {
		return configuration;
	}

	public Composite getContainer() {
		return container;
	}

	public void hide() {
		container.setVisible(false);
	}

	/**
	 * Initialized GUI controls with input configurations.<br>
	 * Called by the framework when configurations are set.
	 */
	protected abstract void initializeGUI();

	// public abstract boolean isAcceptableElement(final EObject eObject);

	public void setConfigurableName(final String configurableName) {
		this.configurableName = configurableName;
	}

	public void setConfigurations(final Map<String, String> configMap,
			final String configurableName) {
		this.configuration = configMap;
		setConfigurableName(configurableName);
		initializeGUI();
	}

	protected void setContainer(final Composite container) {
		this.container = container;
	}

	public void show() {
		container.setVisible(true);
	}

	protected void signalConfigurationChange() {
		for (final ConfigurationChangeListener listener : changeListeners)
			listener.signalConfigurationChange(this);
	}

}