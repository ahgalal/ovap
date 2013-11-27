/**
 * 
 */
package ovap.video.filter;

import java.util.ArrayList;


import ovap.video.ConfigurationChangeListener;
import ovap.video.ConfigurationContributer;
import ovap.video.IFilterManager;

/**
 * @author Creative
 */
public abstract class FilterConfigurationContributer extends ConfigurationContributer {
	private IFilterManager filterManager;
	public FilterConfigurationContributer() {
		changeListeners = new ArrayList<ConfigurationChangeListener>();
	}

	public IFilterManager getFilterManager() {
		return filterManager;
	}

	public void setFilterManager(IFilterManager iFilterManager) {
		this.filterManager = iFilterManager;
	}

}
