/**
 * 
 */
package ovap.video.filter.filtersetup.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Creative
 */
public class PortConnectionConstraint extends AbstractModelConstraint {

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse
	 * .emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(final IValidationContext ctx) {
		// AhGalal: commented out as we depend on EObject constrains (that portIn to Connection
		// is 1 to 1)
		
		// final EObject target = ctx.getTarget();
		// final PortInInstance portInInstance = (PortInInstance) target;
		// if (portInInstance.getFilterConnection() == null)
		// return ctx.createFailureStatus(target);

		return ctx.createSuccessStatus();
	}

}
