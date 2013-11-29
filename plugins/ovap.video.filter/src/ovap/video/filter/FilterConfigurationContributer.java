/**
 * 
 */
package ovap.video.filter;

import ovap.video.ConfigurationContributer;
import ovap.video.IFilterManager;

/**
 * @author Creative
 */
public abstract class FilterConfigurationContributer extends ConfigurationContributer {
	private IFilterManager filterManager;

	public IFilterManager getFilterManager() {
		return filterManager;
	}

	public void setFilterManager(IFilterManager iFilterManager) {
		this.filterManager = iFilterManager;
	}

}
