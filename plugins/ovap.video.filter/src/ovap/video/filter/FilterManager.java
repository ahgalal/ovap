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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IStartup;

import ovap.emf.utils.EMFUtils;
import ovap.video.FrameData;
import ovap.video.IFilterManager;
import ovap.video.Parameter;
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
public class FilterManager implements IFilterManager, IStartup,IResourceChangeListener {
	

	private class ResourceDeltaVisitor implements IResourceDeltaVisitor{

		@Override
		public boolean visit(IResourceDelta delta) throws CoreException {
			if(configuration!=null){
				IResource resource = delta.getResource();
				if(resource.getFullPath().toString().endsWith(configuration.getFilterGraphResourcePath())){
					// file has changed
					
					// reload file
					Resource emfResource = EMFUtils.getEMFResource(resource);
					emfResource.unload();
					try {
						emfResource.load(null);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					configureFiltersFromActiveGraph();

					return false;
				}
			}
			return true;
		}

	}
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

	private final ArrayList<VideoFilter>	activeFilters;
	private FiltersLaunchConfigurations		configuration;
	private boolean							enabled	= false;
	private Thread							filtersThread;
	private FrameData						frameData;
	private static ArrayList<VideoFilter>	installedFilters;
	private boolean							paused	= false;
	private Link							sourceLink;

	public FilterManager() {
		activeFilters = new ArrayList<VideoFilter>();
	}

	private static void createInstalledFiltersEMFModel() {
		final FilterModel filterModel = ModelFactory.eINSTANCE
				.createFilterModel();
		final ArrayList<FilterType> filterTypes = new ArrayList<FilterType>();
		for (final VideoFilter filter : installedFilters) {
			final FilterType filterType = ModelFactory.eINSTANCE
					.createFilterType();
			filterType.setModel(filterModel);
			filterType.setName(filter.getID());

			final String[] inPortIDs = filter.getInPortIDs();
			for (final String inPortID : inPortIDs) {
				final PortIn portIn = ModelFactory.eINSTANCE.createPortIn();
				portIn.setName(inPortID);
				filterType.getPortIn().add(portIn);
			}

			final String[] outPortIDs = filter.getOutPortIDs();
			for (final String outPortID : outPortIDs) {
				final PortOut portOut = ModelFactory.eINSTANCE.createPortOut();
				portOut.setName(outPortID);
				filterType.getPortOut().add(portOut);
			}

			filterTypes.add(filterType);
		}

		// create installed_filters.emf
		// Obtain a new resource set
		final ResourceSet resSet = new ResourceSetImpl();

		// create a resource
		final Resource resource = resSet.createResource(URI
				.createURI("/test/installed_filters.model"));/*
															 * FIXME: hardcoded
															 * to project
															 * "test", need to
															 * use a linked
															 * resource to the
															 * installed_filters
															 * .model file
															 * located under
															 * .metadata folder
															 * of the workspace
															 */
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().add(filterModel);

		// now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void earlyStartup() {
		installedFilters = new ArrayList<VideoFilter>();
		final IConfigurationElement[] config = PDEUtils
				.getExtensions(Activator.OVAP_FILTER_VIDEOFILTER_EP);
		for (final IConfigurationElement e : config) {
			final VideoFilter videoFilter = PDEUtils.instantiateExtension(
					VideoFilter.class, e);
			installedFilters.add(videoFilter);
		}
		
		createInstalledFiltersEMFModel();
	}

	private HashMap<String, Object> EMapToHashMap(
			final EMap<String, String> configuration) {
		final HashMap<String, Object> configs = new HashMap<String, Object>();
		for (final Entry<String, String> entry : configuration) {
			configs.put(entry.getKey(), entry.getValue());
		}
		return configs;
	}

	private VideoFilter getActiveFilter(final String name) {
		for (final VideoFilter filter : activeFilters) {
			if (filter.getName().equals(name))
				return filter;
		}
		return null;
	}
	
	private void configureFiltersFromActiveGraph(){
		final IProject project = configuration.getProject();
		final String activeGraphFile = configuration
				.getFilterGraphResourcePath();
		final IFile file = project.getFile(activeGraphFile);
		final Resource resource = EMFUtils.getEMFResource(file);
		EObject modelRoot = resource.getContents().get(0);
		FiltersSetup filtersSetup = (FiltersSetup) modelRoot;
		
		for (final FilterInstance filterInstance : filtersSetup
				.getFilterInstances()) {
			// configure filter according to EMF saved configuration
			final Configuration filterConfigs = filterInstance
					.getConfiguration();
			configureFilter(filterInstance.getName(), filterConfigs);
		}
	}

	@Override
	public boolean initialize(final Map<String, Object> configAttributes,
			final FrameData frameData) {
		this.frameData = frameData;
		sourceLink = new Link();

		configuration = new FiltersLaunchConfigurations(configAttributes);

		// load filters
		final TransactionalEditingDomain editingDomain = EMFUtils.getEditingDomain(configuration.getProject());
		final IProject project = configuration.getProject();
		final String activeGraphFile = configuration.getFilterGraphResourcePath();
		
		final IFile file = project.getFile(activeGraphFile);
		
		final ResourceSet resourceSet = editingDomain.getResourceSet();
		EObject modelRoot;
		FiltersSetup filtersSetup = null;
		final Resource resource = resourceSet.getResource(
				URI.createPlatformResourceURI(file.getFullPath().toString(),
						true), true);

		// Loading all available model resources into the editing domain, in
		// order to access installed filters EObjects
		final ArrayList<IFile> files = FileUtils.getFiles(project, "model");
		for (final IFile modelFile : files) {
			final URI uri = URI.createPlatformResourceURI(modelFile
					.getFullPath().toString(), true);
			resourceSet.getResource(uri, true);
		}

		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		modelRoot = resource.getContents().get(0);
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
			activeFilters.add(instance);
			
			// configure filter according to EMF saved configuration
			final Configuration filterConfigs = filterInstance
					.getConfiguration();
			
			configureFilter(filterInstance.getName(), filterConfigs);
			
			if (instance.getName().equals("source")) // FIXME: remove hardcoded source filter name
				instance.setLinkIn(sourceLink);
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
	
	private void configureFilter(String filterName, Configuration filterConfigs){
		VideoFilter filter = getActiveFilter(filterName);
		if (filterConfigs != null)
			filter.configure(EMapToHashMap(filterConfigs.getEntries()));
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
	
	public ArrayList<Parameter> getParameters(){
		ArrayList<Parameter> activeParameters = new ArrayList<Parameter>();
		for(VideoFilter filter:activeFilters){
			activeParameters.addAll(filter.getParameters());
		}
		return activeParameters;
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
		paused = false;
		filtersThread.interrupt();
		return false;
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		ResourceDeltaVisitor deltaVisitor = new ResourceDeltaVisitor();
		try {
			event.getDelta().accept(deltaVisitor);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deInitialize() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		return true;
	}

}
