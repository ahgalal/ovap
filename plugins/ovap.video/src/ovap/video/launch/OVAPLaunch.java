/**
 * 
 */
package ovap.video.launch;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.ISourceLocator;
import org.eclipse.jface.dialogs.DialogSettings;

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
		configurations.put(LaunchConfigs.LAUNCH_CONFIG_NAME.toString(),
				getLaunchConfiguration().getName());
		return configurations;
	}

	@Override
	public void launchAdded(final ILaunch launch) {
		super.launchAdded(launch);

		startStreamTarget();
	}

	public void startAnalysisTarget(final DialogSettings analysisSettings) {
		final AnalysisTarget target = new AnalysisTarget(this, "Analysis");
		addDebugTarget(target);

		final Map<String, Object> configurations = getLaunchConfigAttributes();
		for (final String key : configurations.keySet()) {
			analysisSettings.put(key, (String) configurations.get(key));
		}
		final String launchConfigName = getLaunchConfiguration().getName();
		VideoManager.getDefault().initializeAnalysisSession(target,
				analysisSettings, launchConfigName);
		VideoManager.getDefault().startAnalysis(launchConfigName);
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
