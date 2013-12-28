/**
 * 
 */
package ovap.video.filter;

import org.eclipse.core.runtime.IConfigurationElement;

import ovap.video.filter.filtersetup.FilterInstance;
import utils.PDEUtils;

/**
 * @author Creative
 */
public class FilterConfigurationManager {
	private static FilterConfigurationManager	defaultInstance;

	public static FilterConfigurationManager getDefault() {
		if (defaultInstance == null)
			defaultInstance = new FilterConfigurationManager();
		return defaultInstance;
	}

	private FilterConfigurationManager() {
	}
	
	public boolean isContributerPresent(FilterInstance filterInstance){
		IConfigurationElement videoFilterExtension = getVideoFilterExtension(filterInstance.getType().getName());
		for(IConfigurationElement child:videoFilterExtension.getChildren()){
			// look for Configuration contributer
			if(child.getName().equals("configuration_gui"))
				return true;
		}
		return false;
	}
	
	private IConfigurationElement getVideoFilterExtension(String filterId){
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(Activator.OVAP_FILTER_VIDEOFILTER_EP);
		for (final IConfigurationElement element : extensions) {
			String tmpId = element.getAttribute("id");
			if(tmpId.equals(filterId)){
				return element;
			}
		}
		return null;
	}

	public FilterConfigurationContributer getContributerForFilter(
			final FilterInstance filterInstance) {
		IConfigurationElement videoFilterExtension = getVideoFilterExtension(filterInstance.getType().getName());
		if(videoFilterExtension==null){
			System.err.println("Couldn't find filter of type: " + filterInstance.getType().getName());
			return null;
		}
		for(IConfigurationElement child:videoFilterExtension.getChildren()){
			// look for Configuration contributer
			if(child.getName().equals("configuration_gui"))
				return PDEUtils.instantiateExtension(FilterConfigurationContributer.class, child);
		}
		return null;
	}
}
