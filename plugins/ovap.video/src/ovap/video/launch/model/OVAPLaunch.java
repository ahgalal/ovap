/**
 * 
 */
package ovap.video.launch.model;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ISourceLocator;

import ovap.video.Activator;
import ovap.video.VideoManager;

/**
 * @author Creative
 */
public class OVAPLaunch extends Launch {

	public OVAPLaunch(final ILaunchConfiguration launchConfiguration,
			final String mode, final ISourceLocator locator) {
		super(launchConfiguration, mode, locator);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> getLaunchConfigAttributes() {
		Map<String, Object> configurations = null;
		try {
			configurations = getLaunchConfiguration().getAttributes();
		} catch (final CoreException e) {
			e.printStackTrace();
		}
		configurations.put(Activator.SETTING_LAUNCH_CONFG_NAME,
				getLaunchConfiguration().getName());
		return configurations;
	}

	@Override
	public void launchAdded(final ILaunch launch) {
		super.launchAdded(launch);

		// clear all targets
		IDebugTarget[] debugTargets = getDebugTargets();
		for(IDebugTarget target:debugTargets)
			removeDebugTarget(target);
		
		startStreamTarget();
	}
	
	private int analysisSessionCount=0;

	public boolean startAnalysisTarget(final Map<String, String> analysisSettings) {
		final AnalysisTarget target = new AnalysisTarget(this, "Analysis"+analysisSessionCount++);
		addDebugTarget(target);

		final Map<String, Object> configurations = getLaunchConfigAttributes();
		for (final String key : configurations.keySet()) {
			analysisSettings.put(key, (String) configurations.get(key));
		}
		VideoManager.getDefault().initializeAnalysisSession(target,
				analysisSettings);
		if(!VideoManager.getDefault().startAnalysis(target.getSessionId())){
			// roll back the initialization, as there is an error
			VideoManager.getDefault().removeAnalysisSession(target);
			return false;
		}
		
		return true;
	}
	
	@Override
	public void removeDebugTarget(IDebugTarget target) {
		super.removeDebugTarget(target);
		VideoManager.getDefault().removeAnalysisSession(target);
	}

	private void startStreamTarget() {
		final StreamTarget streamTarget = new StreamTarget(this, "Stream");
		addDebugTarget(streamTarget);
		final String launchConfigName = getLaunchConfiguration().getName();

		final Map<String, Object> configurations = getLaunchConfigAttributes();

		VideoManager.getDefault().initializeStreamSession(streamTarget,
				configurations);
		VideoManager.getDefault().startStream(launchConfigName);
	}

	/*
	 * @Override public void launchConfigurationChanged(ILaunchConfiguration
	 * configuration) { IDebugTarget[] debugTargets = getDebugTargets();
	 * for(IDebugTarget debugTarget:debugTargets){ if(debugTarget instanceof
	 * StreamTarget)
	 * ((StreamTarget)debugTarget).launchConfigurationChanged(configuration); }
	 * }
	 */
}
