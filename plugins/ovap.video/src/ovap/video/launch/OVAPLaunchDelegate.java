package ovap.video.launch;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate2;

import ovap.video.FiltersConfiguration;
import ovap.video.VideoManager;


public class OVAPLaunchDelegate implements ILaunchConfigurationDelegate2 {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		String filterGraphResourcePath = configuration.getAttribute(LaunchConfigs.FILTER_GRAPH.toString(), "");
		String projectName = configuration.getAttribute(LaunchConfigs.PROJECT_NAME.toString(), "");
		
		FiltersConfiguration filtersConfiguration = new FiltersConfiguration();
		
		filtersConfiguration.setFilterGraphResourcePath(filterGraphResourcePath);
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		filtersConfiguration.setProject(project);
		
		VideoManager.getDefault().startStream(filtersConfiguration);
		
		
	}

	@Override
	public ILaunch getLaunch(ILaunchConfiguration configuration, String mode)
			throws CoreException {
		// TODO Auto-generated method stub
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
