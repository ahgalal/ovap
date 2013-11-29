package ovap.module;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	public static final String	DELIM_SETTINGS	= "|";
	public static final String	DELIM_SETTINGS_REGEX	= "\\|";
	public static final String	OVAP_VIDEO_MODULE_EP			= "ovap.video.module";
	public static final String	OVAP_VIDEO_MODULE_EP_ATTR_ID	= "id";
	public static final String	OVAP_VIDEO_MODULE_EP_ELEMENT_CONFIG_GUI_ID	= "configuration_gui";
	// The shared instance
	private static Activator	plugin;
	// The plug-in ID
	public static final String	PLUGIN_ID						= "ovap.video.module";			//$NON-NLS-1$
	public static final String	SETTINGS_ID_POST_FIX			= "__id";
	public static final String	SETTINGS_PARAMS_POST_FIX		= "__params";
	public static final String	SETTINGS_SELECTED_MODULES_NAMES	= "selected_modules_names";
	public static final String	INPUT_PARAM_ELEMENT		= "in_parameter";
	public static final String	OUTPUT_PARAM_ELEMENT	= "out_parameter";
	public static final String	PARAM_ID				= "id";
	public static final String	PARAM_NAME				= "name";
	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

}
