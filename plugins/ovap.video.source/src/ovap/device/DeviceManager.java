/**
 * 
 */
package ovap.device;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtensionFactory;

import ovap.video.FrameData;
import ovap.video.ISourceManager;
import utils.PDEUtils;

/**
 * @author Creative
 * 
 */
public class DeviceManager implements ISourceManager,
		IExecutableExtensionFactory {

	private static DeviceManager self;

	public static DeviceManager getDefault() {
		if (self == null)
			self = new DeviceManager();
		return self;
	}

	private ArrayList<VideoSource> videoSources;

	public DeviceManager() {
		if (self != null)
			return;
		else
			self = this;

		videoSources = new ArrayList<VideoSource>();
		final IConfigurationElement[] config = PDEUtils
				.getExtensions("ovap.device.input");
		for (final IConfigurationElement e : config) {
			final VideoSource videoSource = PDEUtils.instantiateExtension(
					VideoSource.class, e);
			videoSources.add(videoSource);
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object create() throws CoreException {
		return getDefault();
	}

	@Override
	public FrameData getFrameData() {
		return frameData;
	}
	private FrameData frameData;
	@Override
	public boolean initialize() {
		frameData=new FrameData();
		videoSources.get(0).initialize(frameData, null);
		return true;
	}

	@Override
	public boolean startStream() {
		videoSources.get(0).startStream();
		return true;
	}

	@Override
	public boolean stopStream() {
		videoSources.get(0).stopStream();
		return false;
	}
}
