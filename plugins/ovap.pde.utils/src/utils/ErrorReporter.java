/**
 * 
 */
package utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import ovap.pde.utils.Activator;

/**
 * @author Creative
 *
 */
public class ErrorReporter {
	
	public static void reportInternalError(String message,Throwable throwable){
		Activator.getDefault().getLog().log(new Status(IStatus.ERROR, "", message,throwable));
	}
	
	public static void reportInternalError(Throwable throwable){
		Activator.getDefault().getLog().log(new Status(IStatus.ERROR, "", throwable.getMessage(),throwable));
	}

}
