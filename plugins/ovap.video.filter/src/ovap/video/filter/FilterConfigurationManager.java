/**
 * 
 */
package ovap.video.filter;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;

import ovap.video.filter.setup.model.FilterInstance;

import utils.PDEUtils;

/**
 * @author Creative
 */
public class FilterConfigurationManager {
	private static FilterConfigurationManager	defaultInstance;
	private static String						EXP_ID	= "ovap.filter.configuration";

	public static FilterConfigurationManager getDefault() {
		if (defaultInstance == null)
			defaultInstance = new FilterConfigurationManager();
		return defaultInstance;
	}

	private FilterConfigurationManager() {
	}
	
	public boolean isContributerPresent(EObject eObject){
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(EXP_ID);
		for (final IConfigurationElement element : extensions) {
			String supportedFilterType = element.getAttribute("filtertype");
			String filterInstanceTypeId = ((FilterInstance)eObject).getType().getName();
			if(supportedFilterType.equals(filterInstanceTypeId))
				return true;
		}
		return false;
	}

	public FilterConfigurationContributer getContributerForObject(
			final EObject eObject) {
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(EXP_ID);
		for (final IConfigurationElement element : extensions) {
			final FilterConfigurationContributer instance = PDEUtils
					.instantiateExtension(FilterConfigurationContributer.class,
							element);
			final boolean accepted = instance.isAcceptableElement(eObject);
			if (accepted) {
				return instance;
			}
		}

		return null;
	}
}
