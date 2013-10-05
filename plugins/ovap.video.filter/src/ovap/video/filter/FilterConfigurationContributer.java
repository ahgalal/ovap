/**
 * 
 */
package ovap.video.filter;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

import ovap.video.filter.setup.model.FilterInstance;

/**
 * @author Creative
 */
public abstract class FilterConfigurationContributer {

	private HashMap<String, String>	configuration;

	public abstract void createControls(final Composite parent);

	public HashMap<String, String> getConfigurations() {
		return configuration;
	}

	protected abstract void initializeGUI();

	public boolean isAcceptableElement(final EObject eObject) {
		if (eObject instanceof FilterInstance)
			return true;
		return false;
	}

	public void setConfigurations(final HashMap<String, String> configuration) {
		this.configuration = configuration;
		initializeGUI();
	}

}
