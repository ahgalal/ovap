/**
 * 
 */
package ovap.video.filter.filtersetup.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import ovap.video.filter.filtersetup.Identifiable;

/**
 * @author Creative
 *
 */
public class NamingConstraint extends AbstractModelConstraint {

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		Identifiable identifiable = (Identifiable) ctx.getTarget();
		if(identifiable.getName().contains(" "))
			return ctx.createFailureStatus("Name contains spaces for: " + identifiable);
		if(identifiable.getName()==null || identifiable.getName().isEmpty())
			return ctx.createFailureStatus("Name is empty for: "+ identifiable);
		
		return ctx.createSuccessStatus();
	}

}
