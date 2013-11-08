/**
 * 
 */
package ovap.video.filter;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

import ovap.video.filter.setup.model.FilterInstance;

/**
 * @author Creative
 */
public abstract class FilterConfigurationContributer {
	private final ArrayList<FilterConfigurationChangeListener>	changeListeners;
	private Map<String, String>									configuration;
	private Composite											container;

	public FilterConfigurationContributer() {
		changeListeners = new ArrayList<FilterConfigurationChangeListener>();
	}

	public void addChangeListener(
			final FilterConfigurationChangeListener listener) {
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

	protected abstract void initializeGUI();

	public boolean isAcceptableElement(final EObject eObject) {
		if (eObject instanceof FilterInstance)
			return true;
		return false;
	}

	public void setConfigurations(final Map<String, String> filterConfigMap) {
		this.configuration = filterConfigMap;
		initializeGUI();
	}

	protected void setContainer(final Composite container) {
		this.container = container;
	}

	protected void signalConfigurationChange() {
		for (final FilterConfigurationChangeListener listener : changeListeners)
			listener.signalConfigurationChange(this);
	}

}
