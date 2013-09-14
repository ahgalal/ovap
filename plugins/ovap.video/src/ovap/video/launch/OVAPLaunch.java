/**
 * 
 */
package ovap.video.launch;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.ISourceLocator;

/**
 * @author Creative
 *
 */
public class OVAPLaunch extends Launch {

	public OVAPLaunch(ILaunchConfiguration launchConfiguration, String mode,
			ISourceLocator locator) {
		super(launchConfiguration, mode, locator);
		
		StreamTarget streamTarget = new StreamTarget(this);
		
		addDebugTarget(streamTarget);
		
	}
	
	

}
