/**
 * 
 */
package ovap.video.filter.filtersetup.validation;

import org.eclipse.emf.validation.model.IClientSelector;

import ovap.video.filter.filtersetup.Identifiable;

/**
 * @author Creative
 *
 */
public class ClientSelector implements IClientSelector {

	@Override
	public boolean selects(Object object) {
		return object instanceof Identifiable;
	}

}
