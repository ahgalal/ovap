/**
 * 
 */
package ovap.video.filter.filtersetup.validation;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.ui.IStartup;

import ovap.video.filter.filtersetup.FiltersetupPackage;

/**
 * @author Creative
 *
 */
public class RegisterModelValidator implements IStartup {

	@Override
	public void earlyStartup() {
		// register model validator
		EValidator.Registry.INSTANCE.put(FiltersetupPackage.eINSTANCE, new FilterValidator());
	}

}
