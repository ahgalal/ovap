/**
 * 
 */
package ovap.video.source;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

import ovap.video.FrameData;
import ovap.video.ISourceManager;
import ovap.video.IStreamEndListener;
import utils.PDEUtils;

/**
 * @author Creative
 * 
 */
public class SourceManager implements ISourceManager,IStreamEndListener{


	private ArrayList<VideoSource> videoSources;
	private SourceConfiguration configuration;
	private ArrayList<IStreamEndListener> streamEndListeners;
	public SourceManager() {
		
		videoSources = new ArrayList<VideoSource>();
		final IConfigurationElement[] config = PDEUtils
				.getExtensions("ovap.device.input");
		for (final IConfigurationElement e : config) {
			final VideoSource videoSource = PDEUtils.instantiateExtension(
					VideoSource.class, e);
			videoSources.add(videoSource);
		}
	}

	@Override
	public FrameData getFrameData() {
		return frameData;
	}
	private FrameData frameData;
	@Override
	public boolean initialize(Map<String, Object> configurations) {
		streamEndListeners = new ArrayList<IStreamEndListener>();
		configurations.put(SourceLaunchConfigs.TYPE.toString(), "file");// FIXME: move this statement to the Source tab of launch config
		configurations.put(SourceLaunchConfigs.FILE_NAME.toString(), "E:\\Documents\\Desktop\\BMT\\Videos\\FST_1_wmv2.avi");// FIXME: move this statement to the Source tab of launch config
		
		String sourceType = (String) configurations.get(SourceLaunchConfigs.TYPE.toString());
		if(sourceType.equals("file"))
			configuration=new SourceFileConfiguration(configurations);
		else
			configuration=new SourceCamConfiguration(configurations);
		
		frameData=new FrameData();
		videoSources.get(0).initialize(frameData, configuration);
		videoSources.get(0).addStreamEndListener(this);
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

	@Override
	public boolean pauseStream() {
		videoSources.get(0).pauseStream();
		return true;
	}

	@Override
	public boolean resumeStream() {
		videoSources.get(0).resumeStream();
		return true;
	}

	@Override
	public void streamEnded() {
		for(IStreamEndListener streamEndListener:streamEndListeners)
			streamEndListener.streamEnded();
	}

	@Override
	public void addStreamEndListener(IStreamEndListener streamEndListener) {
		streamEndListeners.add(streamEndListener);
	}
	@Override
	public void removeStreamEndListener(IStreamEndListener streamEndListener) {
		streamEndListeners.remove(streamEndListener);
	}
}
