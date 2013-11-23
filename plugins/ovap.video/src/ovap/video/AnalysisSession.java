/**
 * 
 */
package ovap.video;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.dialogs.DialogSettings;

import utils.PDEUtils;

/**
 * @author Creative
 */
public class AnalysisSession extends AbstractSession {
	private final IModuleManager	moduleManager;

	public AnalysisSession() {
		// Module Manager
		final IConfigurationElement[] moduleManagerExtensions = PDEUtils
				.getExtensions(VideoManager.EP_OVAP_VIDEO_MODULE_MANAGER);
		moduleManager = PDEUtils.instantiateExtension(IModuleManager.class,
				moduleManagerExtensions[0]);
	}

	@Override
	public void deInitialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getId() {
		return getTarget().getLaunch().getLaunchConfiguration().getName();
	}

	@Override
	public ArrayList<Parameter> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(final DialogSettings settings) {
		moduleManager.initialize(settings);
	}

	@Override
	public void initialize(final Map<String, Object> attributes) {
		throw new UnsupportedOperationException("method is not implemented");
	}

	@Override
	public boolean pause() {
		moduleManager.pause();

		setState(SessionState.PAUSED);
		return true;
	}

	public void registerParameters(final ArrayList<Parameter> parameters) {
		moduleManager.registerParameters(parameters);
	}

	@Override
	public boolean resume() {
		moduleManager.resume(); // FIXME: only resume if the tracking status was
		// ON when pausing
		setState(SessionState.RUNNING);
		return false;
	}

	@Override
	public boolean start() {
		moduleManager.start();
		setState(SessionState.RUNNING);
		return true;
	}

	@Override
	public void stop() {
		moduleManager.stop();
		setState(SessionState.STOPPED);
	}
}
