/**
 * 
 */
package ovap.video.filter.filtersetup.validation;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

/**
 * @author Creative
 */
public class FilterValidator extends EObjectValidator {
	private final IBatchValidator	batchValidator;

	public FilterValidator() {

		batchValidator = ModelValidationService.getInstance().newValidator(
				EvaluationMode.BATCH);
		batchValidator.setIncludeLiveConstraints(true);
		batchValidator.setReportSuccesses(true);

	}

	/**
	 * Updates the diagnositics instance with results from running the Model
	 * validation service.
	 * 
	 * @param status
	 * @param diagnostics
	 */
	private void appendDiagnostics(final IStatus status,
			final DiagnosticChain diagnostics) {

		// it appears that model validation service returns a hierarchical
		// status for all objects validated
		if (status.isMultiStatus()) {
			final IStatus[] children = status.getChildren();

			for (int i = 0; i < children.length; i++) {
				appendDiagnostics(children[i], diagnostics);
			}
		} else if (status instanceof IConstraintStatus) {
			diagnostics.add(new BasicDiagnostic(status.getSeverity(), status
					.getPlugin(), status.getCode(), status.getMessage(),
					((IConstraintStatus) status).getResultLocus().toArray()));
		}
	}

	private boolean hasBeenProcessed(EObject eObject,
			final Map<Object, Object> context) {
		boolean result = false;

		// if eObject's container was processed, then the eObject had been
		// processed (Model validation service skims the object's tree)
		if (context != null) {
			while (eObject != null) {
				if (context.containsKey(eObject)) {
					result = true;
					eObject = null;
				} else {
					eObject = eObject.eContainer();
				}
			}
		}

		return result;
	}

	private void setProcessed(final EObject eObject,
			final Map<Object, Object> context, final IStatus status) {
		if (context != null) {
			context.put(eObject, status);
		}
	}

	@Override
	public boolean validate(final EClass eClass, final EObject eObject,
			final DiagnosticChain diagnostics, final Map<Object, Object> context) {
		super.validate(eClass, eObject, diagnostics, context);

		IStatus status = Status.OK_STATUS;

		// call model validation service
		if (diagnostics != null) {
			if (!hasBeenProcessed(eObject, context)) {
				status = batchValidator.validate(eObject,
						new NullProgressMonitor());

				setProcessed(eObject, context, status);

				appendDiagnostics(status, diagnostics);
			}
		}

		return status.isOK();
	}
}
