package ovap.video;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "ovap.video"; //$NON-NLS-1$
	public static final String ANALYSIS_WIZARD_PAGE_EP= "ovap.video.analysis.wizard.page";
	public static final String	FILE_EXT_ANALYSIS_RESULT	= "oar";
	public static final String	FILE_EXT_ANALYSIS_SETTINGS	= "oas";
	public static final String		SETTING_LAUNCH_CONFG_NAME		= "launch_config_name";
	public static final String		SETTING_PROJECT_NAME = "project_name";
	public static final String		SETTING_SESSION_DATE		= "session_date";
	public static final String		SETTING_SESSION_DESCRIPTION	= "session_description";
	public static final String		SETTING_SESSION_NAME		= "session_name";
	public static final String		SETTING_SESSION_RESULTS_FILE		= "session_results_file";
	
	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
