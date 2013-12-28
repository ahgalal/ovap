package ovap.video.filter.average.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public static final String		CONFIG_GRAY_THRESHOLD					= "mask_gray_threshold";

	public static final int			CONFIG_GRAY_THRESHOLD_DEFAULT_VALUE		= 20;

	public static final String		CONFIG_MASK_SIDE_LENGTH					= "mask_side_length";
	public static final int			CONFIG_MASK_SIDE_LENGTH_DEFAULT_VALUE	= 30;
	private static BundleContext	context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(final BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
