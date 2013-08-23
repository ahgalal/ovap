package ovap.video;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.ui.services.ISourceProviderService;

import utils.PDEUtils;

public class VideoManager implements IStartup, IExecutableExtensionFactory{

	private static VideoManager self;
	public static VideoManager getDefault(){
		if(self ==null)
			self=new VideoManager();
		return self;
	}
	private StreamState streamState = StreamState.STOPPED;
	
	private IFilterManager filterManager;
	private IModuleManager moduleManager;
	private ISourceManager sourceManager;
	
	public VideoManager() {
		if (self != null)
			return;
		else
			self = this;
	}


	@Override
	public void earlyStartup() {
		
		// Filter Manager
		IConfigurationElement[] filterManagerExtensions = PDEUtils.getExtensions("ovap.video.filter.manager");
		filterManager = PDEUtils.instantiateExtension(IFilterManager.class, filterManagerExtensions[0]);
		
		// Module Manager
		IConfigurationElement[] moduleManagerExtensions = PDEUtils.getExtensions("ovap.video.module.manager");
		moduleManager = PDEUtils.instantiateExtension(IModuleManager.class, moduleManagerExtensions[0]);
		
		// Source Manager
		IConfigurationElement[] sourceManagerExtensions = PDEUtils.getExtensions("ovap.video.source.manager");
		sourceManager = PDEUtils.instantiateExtension(ISourceManager.class, sourceManagerExtensions[0]);
		
		setState(StreamState.STOPPED);
	}


	public boolean startStream() {
		sourceManager.initialize();
		FrameData frameData=sourceManager.getFrameData();
		filterManager.initialize(frameData);
		
		sourceManager.startStream();
		filterManager.startStream();
		setState(StreamState.STREAMING);
		return true;
	}


	public void stopStream() {
		filterManager.stopStream();
		sourceManager.stopStream();
		setState(StreamState.STOPPED);
	}
	
	private void setState(StreamState state){
		streamState=state;
		
		// notify State provider
		ISourceProviderService service = (ISourceProviderService) PlatformUI.getWorkbench().getWorkbenchWindows()[0].getService(ISourceProviderService.class);
		StreamingStateProvider streamStateProvider = (StreamingStateProvider) service.getSourceProvider(StreamingStateProvider.OVAP_VIDEO_STREAMING_ENABLE_RESUME);
		streamStateProvider.notifyStateUpdate();
	}

	public StreamState getState() {
		return streamState;
	}


	@Override
	public Object create() throws CoreException {
		return getDefault();
	}
}
