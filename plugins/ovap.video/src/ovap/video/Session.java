/**
 * 
 */
package ovap.video;

import org.eclipse.core.runtime.IConfigurationElement;

import utils.PDEUtils;

/**
 * @author Creative
 *
 */
public class Session {
	private StreamState streamState = StreamState.STOPPED;
	private String name;
	
	private IFilterManager filterManager;
	private IModuleManager moduleManager;
	private ISourceManager sourceManager;
	
	public Session(String name) {
		this.name=name;
		
		// Filter Manager
		IConfigurationElement[] filterManagerExtensions = PDEUtils.getExtensions(VideoManager.EP_OVAP_VIDEO_FILTER_MANAGER);
		filterManager = PDEUtils.instantiateExtension(IFilterManager.class, filterManagerExtensions[0]);
		
		// Module Manager
		IConfigurationElement[] moduleManagerExtensions = PDEUtils.getExtensions(VideoManager.EP_OVAP_VIDEO_MODULE_MANAGER);
		moduleManager = PDEUtils.instantiateExtension(IModuleManager.class, moduleManagerExtensions[0]);
		
		// Source Manager
		IConfigurationElement[] sourceManagerExtensions = PDEUtils.getExtensions(VideoManager.EP_OVAP_VIDEO_SOURCE_MANAGER);
		sourceManager = PDEUtils.instantiateExtension(ISourceManager.class, sourceManagerExtensions[0]);
		
		setState(StreamState.STOPPED);
		
	}
	
	private void setState(StreamState state){
		streamState=state;
		
		// notify State provider
/*		ISourceProviderService service = (ISourceProviderService) PlatformUI.getWorkbench().getWorkbenchWindows()[0].getService(ISourceProviderService.class);
		StreamingStateProvider streamStateProvider = (StreamingStateProvider) service.getSourceProvider(StreamingStateProvider.OVAP_VIDEO_STREAMING_ENABLE_RESUME);
		streamStateProvider.notifyStateUpdate();*/
	}

	public StreamState getState() {
		return streamState;
	}
	
	public boolean startStream() {
		sourceManager.startStream();
		filterManager.startStream();
		setState(StreamState.STREAMING);
		return true;
	}

	public void initialize(FiltersConfiguration configuration) {
		sourceManager.initialize();
		FrameData frameData=sourceManager.getFrameData();
		configuration.setFrameData(frameData);
		filterManager.initialize(configuration);
		
	
	}

	public void stopStream() {
		filterManager.stopStream();
		sourceManager.stopStream();
		setState(StreamState.STOPPED);		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
