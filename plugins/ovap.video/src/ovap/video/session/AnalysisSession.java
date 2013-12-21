/**
 * 
 */
package ovap.video.session;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;

import ovap.video.Activator;
import ovap.video.IModuleManager;
import ovap.video.Parameter;
import ovap.video.SessionState;
import ovap.video.VideoManager;
import ovap.video.launch.analysis.persist.AnalysisSessionResult;
import utils.FileUtils;
import utils.PDEUtils;

/**
 * @author Creative
 */
public class AnalysisSession extends AbstractSession {
	private final IModuleManager	moduleManager;

	private HashMap<String, Object>	settings;

	public AnalysisSession(final String analysisSessionId) {
		super(analysisSessionId);
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
	public ArrayList<Parameter> getParameters() {
		return moduleManager.getOutputParameters();
	}

	@Override
	public void initialize(final Map<String, String> analysisSettings) {
		final HashMap<String, Object> settings = new HashMap<String, Object>();
		settings.putAll(analysisSettings);
		this.settings = settings;
		moduleManager.initialize(settings);
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

	private void saveResults() {
		final String sessionTitle = (String) settings
				.get(Activator.SETTING_SESSION_NAME);
		final String sessionDate = (String) settings
				.get(Activator.SETTING_SESSION_DATE);
		final String sessionDescription = (String) settings
				.get(Activator.SETTING_SESSION_DESCRIPTION);
		final String sessionResultsFile = (String) settings
				.get(Activator.SETTING_SESSION_RESULTS_FILE);

		final IProject project = ResourcesPlugin
				.getWorkspace()
				.getRoot()
				.getProject(
						(String) settings.get(Activator.SETTING_PROJECT_NAME));
		if ((sessionResultsFile != null) && !sessionResultsFile.isEmpty()) {
			final IFile resultsFile = project.getFile(sessionResultsFile);
			FileUtils.createFile(resultsFile);

			final AnalysisSessionResult analysisSessionResult = new AnalysisSessionResult();
			analysisSessionResult.setTitle(sessionTitle);
			analysisSessionResult.setDate(sessionDate);
			analysisSessionResult.setDescription(sessionDescription);
			analysisSessionResult.setParameters(moduleManager
					.getOutputParametersToModuleInstanceMap());

			FileUtils.saveObject(analysisSessionResult, new File(resultsFile
					.getLocation().toOSString()));
		}
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

		saveResults();
	}
}
