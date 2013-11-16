/**
 * 
 */
package ovap.video.filter;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

import ovap.video.IFilterManager;
import ovap.video.filter.setup.model.FilterInstance;

/**
 * @author Creative
 */
public abstract class FilterConfigurationContributer {
	private final ArrayList<FilterConfigurationChangeListener>	changeListeners;
	private Map<String, String>									configuration;
	private Composite											container;
	private IFilterManager filterManager;
	private String filterName;
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
	
	public void show(){
		container.setVisible(true);
	}
	
	public void hide(){
		container.setVisible(false);
	}

	protected abstract void initializeGUI();

	public boolean isAcceptableElement(final EObject eObject) {
		if (eObject instanceof FilterInstance)
			return true;
		return false;
	}

	public void setConfigurations(final Map<String, String> filterConfigMap,String filterName) {
		this.configuration = filterConfigMap;
		setFilterName(filterName);
		initializeGUI();
	}

	protected void setContainer(final Composite container) {
		this.container = container;
	}

	protected void signalConfigurationChange() {
		for (final FilterConfigurationChangeListener listener : changeListeners)
			listener.signalConfigurationChange(this);
	}

	public IFilterManager getFilterManager() {
		return filterManager;
	}

	public void setFilterManager(IFilterManager iFilterManager) {
		this.filterManager = iFilterManager;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

}
