package ovap.video.launch;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate2;
import org.eclipse.debug.internal.core.LaunchManager;

import ovap.video.Activator;
import ovap.video.VideoManager;


public class OVAPLaunchDelegate implements ILaunchConfigurationDelegate2 {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		Map<String,Object> configurations = configuration.getAttributes();
		
		String configName = configuration.getName();
		configurations.put(LaunchConfigs.LAUNCH_CONFIG_NAME.toString(), configName);
		
		ILaunch[] launches = DebugPlugin.getDefault().getLaunchManager().getLaunches();
		int instancesCount=0;
		for(ILaunch launch2:launches){
			if(launch2.getLaunchConfiguration().getName().equals(configName)){
				if(launch2.isTerminated()==false)
					instancesCount++;
			}
		}
		if(instancesCount>1)
			throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, "Cannot run two sessions of the same type simultaneously"));
		
		StreamTarget streamTarget = new StreamTarget((OVAPLaunch) launch,"Stream");
		launch.addDebugTarget(streamTarget);
		
		VideoManager.getDefault().initializeSession(streamTarget);
		VideoManager.getDefault().startStream(configName,configurations);
	}

	@Override
	public ILaunch getLaunch(ILaunchConfiguration configuration, String mode)
			throws CoreException {
		return new OVAPLaunch(configuration, mode, null);
	}

	@Override
	public boolean buildForLaunch(ILaunchConfiguration configuration,
			String mode, IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean finalLaunchCheck(ILaunchConfiguration configuration,
			String mode, IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean preLaunchCheck(ILaunchConfiguration configuration,
			String mode, IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
