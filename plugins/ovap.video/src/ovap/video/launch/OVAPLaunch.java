/**
 * 
 */
package ovap.video.launch;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ISourceLocator;

/**
 * @author Creative
 *
 */
public class OVAPLaunch extends Launch {

	public OVAPLaunch(ILaunchConfiguration launchConfiguration, String mode,
			ISourceLocator locator) {
		super(launchConfiguration, mode, locator);
	}
	
/*	@Override
	public void launchConfigurationChanged(ILaunchConfiguration configuration) {
		IDebugTarget[] debugTargets = getDebugTargets();
		for(IDebugTarget debugTarget:debugTargets){
			if(debugTarget instanceof StreamTarget)
				((StreamTarget)debugTarget).launchConfigurationChanged(configuration);
		}
	}*/
}
