/**
 * 
 */
package ovap.video.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IStartup;

import ovap.video.FrameData;
import ovap.video.IFilterManager;
import ovap.video.filter.setup.model.Configuration;
import ovap.video.filter.setup.model.FilterConnection;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.FilterModel;
import ovap.video.filter.setup.model.FilterType;
import ovap.video.filter.setup.model.FiltersSetup;
import ovap.video.filter.setup.model.ModelFactory;
import ovap.video.filter.setup.model.PortIn;
import ovap.video.filter.setup.model.PortOut;
import sys.utils.Utils;
import utils.FileUtils;
import utils.PDEUtils;

/**
 * @author Creative
 */
public class FilterManager implements IFilterManager, IStartup {
	private class FiltersRunnable implements Runnable {

		@Override
		public void run() {
			while (enabled) {
				Utils.sleep(30);

				synchronized (this) {
					while (paused) {
						try {
							this.wait();
						} catch (final InterruptedException e) {
							// nothing to do, as someone has just awakened this
							// thread
						}
					}
				}

				sourceLink.setData(frameData.getFrameData());
				for (final VideoFilter filter : activeFilters)
					filter.process();
			}
		}

	}

	private ArrayList<VideoFilter>	activeFilters;
	private boolean							enabled	= false;
	private Thread							filtersThread;
	private FrameData						frameData;
	private ArrayList<VideoFilter>	installedFilters;
	private boolean							paused	= false;
	private Link							sourceLink;
	private FiltersLaunchConfigurations configuration;
	public FilterManager() {
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
	
	private void createInstalledFiltersEMFModel(){
		FilterModel filterModel = ModelFactory.eINSTANCE.createFilterModel();
		ArrayList<FilterType> filterTypes=new ArrayList<FilterType>();
		for(VideoFilter filter:installedFilters){
			FilterType filterType = ModelFactory.eINSTANCE.createFilterType();
			filterType.setModel(filterModel);
			filterType.setName(filter.getID());
			
			String[] inPortIDs = filter.getInPortIDs();
			for(String inPortID: inPortIDs){
				PortIn portIn = ModelFactory.eINSTANCE.createPortIn();
				portIn.setName(inPortID);
				filterType.getPortIn().add(portIn);
			}
			
			String[] outPortIDs = filter.getOutPortIDs();
			for(String outPortID: outPortIDs ){
				PortOut portOut = ModelFactory.eINSTANCE.createPortOut();
				portOut.setName(outPortID);
				filterType.getPortOut().add(portOut);
			}
			
			filterTypes.add(filterType);
		}
		
		// create installed_filters.emf
		// Obtain a new resource set
	    ResourceSet resSet = new ResourceSetImpl();

	    // create a resource
	    Resource resource = resSet.createResource(URI
	        .createURI("/test/installed_filters.model"));
	    // Get the first model element and cast it to the right type, in my
	    // example everything is hierarchical included in this first node
	    resource.getContents().add(filterModel);

	    // now save the content.
	    try {
	      resource.save(Collections.EMPTY_MAP);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }	
	}

	private VideoFilter getActiveFilter(final String name) {
		for (final VideoFilter filter : activeFilters) {
			if (filter.getName().equals(name))
				return filter;
		}
		return null;
	}

	@Override
	public boolean initialize(Map<String, Object> launchConfigurations, FrameData frameData) {
		this.frameData = frameData;
		sourceLink = new Link();
		
		configuration = new FiltersLaunchConfigurations(launchConfigurations);

		// load filters
		/*
		 * Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		 * Map<String, Object> m = reg.getExtensionToFactoryMap(); m.put("ovap",
		 * new XMIResourceFactoryImpl());
		 */
		final EditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE
				.createEditingDomain();
		final IProject project = configuration.getProject();// ResourcesPlugin.getWorkspace().getRoot().getProjects()[0];
		final String activeGraphFile = configuration.getFilterGraphResourcePath();// FilterSettingsUtil.getFilterSetting(project,
																			// FilterSettings.ACTIVE_GRAPH);
		final IFile file = project.getFile(activeGraphFile);
		final ResourceSet resourceSet = editingDomain.getResourceSet();
		EObject modelRoot;
		FiltersSetup filtersSetup = null;
		final Resource resource = resourceSet.getResource(
				URI.createPlatformResourceURI(file.getFullPath().toString(),true), true);
		
		// Loading all available model resources into the editing domain, in order to access installed filters EObjects
		ArrayList<IFile> files = FileUtils.getFiles(project, "model");
		for(IFile modelFile:files){
			URI uri = URI.createPlatformResourceURI(modelFile.getFullPath().toString(),true);
			resourceSet.getResource(uri, true);
		}
		
		//resourceSet.getResource(URI.createURI("/test/sources.model"), true);

		modelRoot = resource.getContents().get(0);// EcoreResourceUtil.loadModelRoot(resourceSet
													// , new
													// File(file.getFullPath().toOSString()),
													// null);
		filtersSetup = (FiltersSetup) modelRoot;
		
		activeFilters.clear();
		for (final FilterInstance filterInstance : filtersSetup
				.getFilterInstances()) {
			// get filter type
			final String filterTypeID = filterInstance.getType().getName();
			VideoFilter filter = null;
			for (final VideoFilter videoFilter : installedFilters) {
				if (videoFilter.getID().equals(filterTypeID)) {
					filter = videoFilter;
					break;
				}
			}

			// instantiate filter, add it to active filters
			final VideoFilter instance = filter.newInstance(
					filterInstance.getName(), configuration.getConfigName());
			if (instance.getName().equals("source")) // FIXME: remove hardcoded source filter name
				instance.setLinkIn(sourceLink);
			
			// configure filter according to EMF saved configuration
			Configuration filterConfigs = filterInstance.getConfiguration();
			if(filterConfigs!=null)
				instance.configure(EMapToHashMap(filterConfigs.getEntries()));
			activeFilters.add(instance);
		}

		for (final FilterConnection connection : filtersSetup.getConnections()) {
			final String dstFilterName = connection.getPortInInstance()
					.getFilterInstance().getName();
			final String srcFilterName = connection.getPortOutInstance()
					.getFilterInstance().getName();

			// get filters
			final VideoFilter dstFilter = getActiveFilter(dstFilterName);
			final VideoFilter srcFilter = getActiveFilter(srcFilterName);

			// create links
			final Link link = new Link();
			link.setData(new int[640 * 480]);
			dstFilter.setLinkIn(link);
			srcFilter.setLinkOut(link);
		}

		return true;
	}
	private HashMap<String, Object> EMapToHashMap(final EMap<String, String> configuration) {
		final HashMap<String, Object> configs = new HashMap<String, Object>();
		for (final Entry<String, String> entry : configuration) {
			configs.put(entry.getKey(), entry.getValue());
		}
		return configs;
	}
	@Override
	public boolean pauseStream() {
		paused = true;
		return true;
	}

	@Override
	public boolean resumeStream() {
		if (paused) {
			paused = false;
			if (filtersThread != null)
				filtersThread.interrupt();
		}
		return true;
	}

	@Override
	public boolean startStream() {
		enabled = true;
		filtersThread = new Thread(new FiltersRunnable(), "Filters Thread");
		filtersThread.start();
		return true;
	}

	@Override
	public boolean stopStream() {
		enabled = false;
		paused=false;
		filtersThread.interrupt();
		return false;
	}

	@Override
	public void earlyStartup() {
		createInstalledFiltersEMFModel();
	}

}
