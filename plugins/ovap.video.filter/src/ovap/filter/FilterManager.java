/**
 * 
 */
package ovap.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sphinx.emf.util.EcoreResourceUtil;

import ovap.video.FrameData;
import ovap.video.IFilterManager;
import ovap.video.filter.setup.model.FilterConnection;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.FiltersSetup;
import sys.utils.Utils;
import utils.PDEUtils;

/**
 * @author Creative
 * 
 */
public class FilterManager implements IFilterManager,
		IExecutableExtensionFactory {
	private static FilterManager self;

	public static FilterManager getDefault() {
		if (self == null)
			self = new FilterManager();
		return self;
	}

	private ArrayList<VideoFilter> installedFilters;
	private ArrayList<VideoFilter> activeFilters;
	private FrameData frameData;
	private boolean enabled=false;
	private class FiltersRunnable implements Runnable{

		@Override
		public void run() {
			Utils.sleep(3000);
			while(enabled){
				Utils.sleep(30);
				sourceLink.setData(frameData.getFrameData());
				for(VideoFilter filter:activeFilters)
					filter.process();
			}
		}

	}
	
	public FilterManager() {
		if (self != null)
			return;
		else
			self = this;

		installedFilters = new ArrayList<VideoFilter>();
		activeFilters = new ArrayList<VideoFilter>();
		final IConfigurationElement[] config = PDEUtils
				.getExtensions("ovap.filter.videofilter");
		for (final IConfigurationElement e : config) {
			final VideoFilter videoFilter = PDEUtils.instantiateExtension(
					VideoFilter.class, e);
			installedFilters.add(videoFilter);
		}
	}

	@Override
	public Object create() throws CoreException {
		return getDefault();
	}
private Link sourceLink;
	@Override
	public boolean startStream() {

/*		VideoFilter display = null;
		VideoFilter source = null;
		for (final VideoFilter filter : installedFilters) {
			if (filter.getClass().getName().endsWith("FrameDisplayFilter"))
				display = filter;
			else if(filter.getClass().getName().endsWith("SourceFilter"))
				source=filter;
		}

		final Link link = new Link();
		link.setData(new int[640 * 480]);

		display.setLinkIn(link);
		source.setLinkOut(link);
		
		
		source.setLinkIn(sourceLink);*/
		
		
		enabled=true;
		Thread filtersThread = new Thread(new FiltersRunnable(),"Filters Thread");
		filtersThread.start();
		
		return true;
	}

	@Override
	public boolean stopStream() {
		enabled=false;
		return false;
	}

	@Override
	public boolean initialize(FrameData frameData) {
		this.frameData=frameData;
		sourceLink = new Link();
		
		// load filters
		EditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProjects()[0];
		IFile file = project.getFile("simple_filters_setup.ovap"); // FIXME
		ResourceSet resourceSet = editingDomain.getResourceSet();
		EObject modelRoot;
		FiltersSetup filtersSetup = null;
		try {
			modelRoot = EcoreResourceUtil.loadModelRoot(resourceSet , new File(file.getFullPath().toOSString()), null);
			filtersSetup =(FiltersSetup) modelRoot;
/*			editingDomain.getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain) editingDomain) {
				
				@Override
				protected void doExecute() {
					FilterType typeB = ModelFactory.eINSTANCE.createFilterType();
					typeB.setId("TypeB");
					filtersSetup.getFilterTypes().add(typeB );
				}
			});
			filtersSetup.eResource().save(null);*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		activeFilters.clear();
		for(FilterInstance filterInstance: filtersSetup.getFilterInstances()){
			// get filter type
			String filterTypeID = filterInstance.getType().getName();
			VideoFilter filter=null;
			for(VideoFilter videoFilter: installedFilters){
				if(videoFilter.getID().equals(filterTypeID)){
					filter=videoFilter;
					break;
				}
			}
			
			// instantiate filter, add it to active filters
			VideoFilter instance = filter.newInstance();
			instance.setName(filterInstance.getName());
			if(instance.getName().equals("source")) // FIXME
				instance.setLinkIn(sourceLink);
			activeFilters.add(instance);
		}
		
		for(FilterConnection connection:filtersSetup.getConnections()){
			String dstFilterName = connection.getPortInInstance().getFilterInstance().getName();
			String srcFilterName = connection.getPortOutInstance().getFilterInstance().getName();
			
			// get filters
			VideoFilter dstFilter = getActiveFilter(dstFilterName);
			VideoFilter srcFilter = getActiveFilter(srcFilterName);
			
			// create links
			Link link = new Link();
			link.setData(new int[640 * 480]);
			dstFilter.setLinkIn(link);
			srcFilter.setLinkOut(link);
			
		}
		
		return true;
	}
	
	private VideoFilter getActiveFilter(String name){
		for(VideoFilter filter:activeFilters){
			if(filter.getName().equals(name))
				return filter;
		}
		return null;
	}
}
