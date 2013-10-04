/**
 * 
 */
package ovap.video.filter;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import ovap.video.FrameData;
import ovap.video.IFilterManager;
import ovap.video.filter.setup.model.FilterConnection;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.FiltersSetup;
import sys.utils.Utils;
import utils.PDEUtils;

/**
 * @author Creative
 */
public class FilterManager implements IFilterManager {
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
							// nothing to do, as someone has just awaken this
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
	private boolean							enabled	= false;
	private Thread							filtersThread;
	private FrameData						frameData;
	private final ArrayList<VideoFilter>	installedFilters;
	private boolean							paused	= false;
	private Link							sourceLink;
	private FiltersLaunchConfigurations configuration;
	public FilterManager() {
		/*
		 * if (self != null) return; else self = this;
		 */

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

	private VideoFilter getActiveFilter(final String name) {
		for (final VideoFilter filter : activeFilters) {
			if (filter.getName().equals(name))
				return filter;
		}
		return null;
	}

	@Override
	public boolean initialize(Map<String, Object> configurations, FrameData frameData) {
		this.frameData = frameData;
		sourceLink = new Link();
		
		configuration = new FiltersLaunchConfigurations(configurations);

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
				URI.createURI(file.getFullPath().toString()), true);

		modelRoot = resource.getContents().get(0);// EcoreResourceUtil.loadModelRoot(resourceSet
													// , new
													// File(file.getFullPath().toOSString()),
													// null);
		filtersSetup = (FiltersSetup) modelRoot;
		/*
		 * editingDomain.getCommandStack().execute(new
		 * RecordingCommand((TransactionalEditingDomain) editingDomain) {
		 * @Override protected void doExecute() { FilterType typeB =
		 * ModelFactory.eINSTANCE.createFilterType(); typeB.setId("TypeB");
		 * filtersSetup.getFilterTypes().add(typeB ); } });
		 * filtersSetup.eResource().save(null);
		 */
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
			if (instance.getName().equals("source")) // FIXME
				instance.setLinkIn(sourceLink);
			
			// configure filter according to EMF saved configuration
			instance.configure(filterInstance.getConfiguration());
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
}
