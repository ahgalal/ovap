/**
 * 
 */
package ovap.video.filter.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import ovap.emf.utils.EMFUtils;
import ovap.video.Activator;
import ovap.video.ConfigurationChangeListener;
import ovap.video.ConfigurationContributer;
import ovap.video.VideoManager;
import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.FilterConfigurationManager;
import ovap.video.filter.FilterLaunchConfigs;
import ovap.video.filter.filtersetup.Configuration;
import ovap.video.filter.filtersetup.FilterInstance;
import ovap.video.launch.ui.OVAPLaunchConfigurationTab;
import utils.FileUtils;
import utils.StringUtils;

/**
 * @author Creative
 */
public class FiltersLaunchConfigurationTab extends OVAPLaunchConfigurationTab
		implements ConfigurationChangeListener {
	private Button											btnBrowseActiveGraph;
	private Button											btnSaveFilters;
	private Composite										cmpstConfiguration;
	private Composite										cmpstDummy;
	protected Composite										container;
	private HashMap<String, FilterConfigurationContributer>	filterInstanceToConfigContributer	= new HashMap<String, FilterConfigurationContributer>();
	private Group											grpFilterConfigurations;
	private Group											grpFilterGraph;
	private ILaunchConfiguration							launchConfiguration;
	private Label											lblActiveGraph;
	private Label											lblSs;
	private List											listActivetFilters;
	private Text											txtActiveGraph;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse
	 * .swt.widgets.Composite)
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControl(final Composite parent) {
		container = new Composite(parent, 0);
		container.setLayout(new GridLayout(1, false));

		grpFilterGraph = new Group(container, SWT.NONE);
		grpFilterGraph.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		grpFilterGraph.setLayout(new GridLayout(3, false));
		grpFilterGraph.setText("Filter Graph:");
		{
			lblActiveGraph = new Label(grpFilterGraph, SWT.NONE);
			lblActiveGraph.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
					false, false, 1, 1));
			lblActiveGraph.setText("Active graph:");
		}
		{
			txtActiveGraph = new Text(grpFilterGraph, SWT.BORDER);
			txtActiveGraph.setEditable(false);
			txtActiveGraph.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		{
			btnBrowseActiveGraph = new Button(grpFilterGraph, SWT.NONE);
			btnBrowseActiveGraph.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(final SelectionEvent e) {
					// FIXME: use a dialog that displays only
					// available EMF filter graph model file (.fg
					// extension? and those only in the selected project)
					// ElementListSelectionDialog dialog = new
					// ElementListSelectionDialog(getShell(),
					// PlatformUI.getWorkbench().);
					final IFile[] selections = WorkspaceResourceDialog
							.openFileSelection(null, "Filter graph",
									"Select filter graph", false, null, null);
					if (selections.length > 0) {
						final IFile selection = selections[0];
						txtActiveGraph.setText(selection
								.getProjectRelativePath().toString());
						updateLaunchConfigurationDialog();
						loadFilterInstances(selection, launchConfiguration);
					}

					// WorkbenchContentProvider contentProvider =
					// new WorkbenchContentProvider();
					// WorkspaceResourceDialog dialog = new
					// WorkspaceResourceDialog(getShell(), new
					// WorkbenchLabelProvider(), contentProvider);
				}
			});
			btnBrowseActiveGraph.setText("...");
		}
		{
			grpFilterConfigurations = new Group(container, SWT.NONE);
			grpFilterConfigurations.setLayout(new GridLayout(3, false));
			grpFilterConfigurations.setLayoutData(new GridData(SWT.FILL,
					SWT.FILL, true, true, 1, 1));
			grpFilterConfigurations.setText("Filter Configurations:");
			{
				listActivetFilters = new List(grpFilterConfigurations,
						SWT.BORDER);
				{
					final GridData gd_lisActivetFilters = new GridData(
							SWT.LEFT, SWT.FILL, false, true, 1, 2);
					gd_lisActivetFilters.widthHint = 119;
					listActivetFilters.setLayoutData(gd_lisActivetFilters);
					listActivetFilters
							.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(
										final SelectionEvent e) {
									final String[] selection = listActivetFilters
											.getSelection();
									if ((selection != null)
											&& (selection.length > 0)) {
										// get FilterInstance for the selected
										// filter instance
										for (final String filterInstanceName : filterInstanceToConfigContributer
												.keySet()) {
											final FilterInstance filterInstance = getFilterInstance(filterInstanceName);
											if (filterInstance.getName()
													.equals(selection[0])) {
												final FilterConfigurationContributer configContributer = getFilterConfigurationGUI(filterInstance);
												for (final Control child : cmpstConfiguration
														.getChildren()) {
													child.setVisible(false);
													child.setParent(cmpstDummy);
												}
												if (configContributer != null) {
													configContributer
															.getContainer()
															.setParent(
																	cmpstConfiguration);
													configContributer.show();
													configContributer
															.getContainer()
															.setLayoutData(
																	new GridData(
																			SWT.FILL,
																			SWT.FILL,
																			true,
																			true,
																			1,
																			1));
													cmpstConfiguration.layout();
												}
											}
										}
									}
								}
							});
				}
			}
			{
				lblSs = new Label(grpFilterConfigurations, SWT.SEPARATOR
						| SWT.VERTICAL);
				lblSs.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false,
						true, 1, 2));
				lblSs.setText("ss");
			}
			{
				cmpstConfiguration = new Composite(grpFilterConfigurations,
						SWT.NONE);
				cmpstConfiguration.setLayout(new GridLayout(1, false));
				cmpstConfiguration.setLayoutData(new GridData(SWT.FILL,
						SWT.FILL, true, true, 1, 1));
			}
			cmpstDummy = new Composite(grpFilterConfigurations, SWT.NONE);
			{
				final GridData gd_cmpstDummy = new GridData(SWT.LEFT,
						SWT.CENTER, false, false, 1, 1);
				gd_cmpstDummy.widthHint = -5;
				gd_cmpstDummy.heightHint = -6;
				cmpstDummy.setLayoutData(gd_cmpstDummy);
			}
			cmpstDummy.setSize(0, 0);
			new Label(grpFilterConfigurations, SWT.NONE);
			new Label(grpFilterConfigurations, SWT.NONE);
			{
				btnSaveFilters = new Button(grpFilterConfigurations, SWT.NONE);
				btnSaveFilters.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(final SelectionEvent e) {
						updateFilterConfigurations(
								filterInstanceToConfigContributer.keySet(),
								launchConfiguration);
						btnSaveFilters.setEnabled(false);
					}
				});
				btnSaveFilters.setLayoutData(new GridData(SWT.RIGHT,
						SWT.CENTER, false, false, 1, 1));
				btnSaveFilters.setText("Save Filters");
			}
		}

	}

	private IFile getActiveGraphFile() {
		IFile filterGraphFile = null;
		String filterGraph = null;
		try {
			filterGraph = launchConfiguration.getAttribute(
					FilterLaunchConfigs.FILTER_GRAPH.toString(), "");
		} catch (final CoreException e) {
			e.printStackTrace();
		}
		if ((filterGraph != null) && !filterGraph.isEmpty()) {
			filterGraphFile = getProject().getFile(filterGraph);
		}
		return filterGraphFile;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getControl()
	 */
	@Override
	public Control getControl() {
		return container;
	}

	private FilterConfigurationContributer getFilterConfigurationGUI(
			final FilterInstance filterInstance) {
		FilterConfigurationContributer configContributer = filterInstanceToConfigContributer
				.get(filterInstance.getName());
		if (configContributer == null)
			configContributer = loadFilterConfigurationGUI(filterInstance);
		return configContributer;
	}

	private FilterInstance getFilterInstance(final String filterInstanceName) {
		final Resource emfResource = EMFUtils
				.getEMFResource(getActiveGraphFile());
		final TreeIterator<EObject> itAllContents = emfResource
				.getAllContents();
		for (; itAllContents.hasNext();) {
			final EObject object = itAllContents.next();
			if (object instanceof FilterInstance) {
				final FilterInstance filterInstance = (FilterInstance) object;
				if (filterInstance.getName().equals(filterInstanceName))
					return filterInstance;
			}
		}
		return null;
	}

	private FilterInstance getFilterInstanceForConfigContrinuter(
			final FilterConfigurationContributer contributer) {
		FilterInstance filterInstance = null;
		for (final String filterInstanceName : filterInstanceToConfigContributer
				.keySet()) {
			if (filterInstanceToConfigContributer.get(filterInstanceName) == contributer) {
				filterInstance = getFilterInstance(filterInstanceName);
				break;
			}
		}
		return filterInstance;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
	 */
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	@Override
	public String getName() {
		return "Filters Setup";
	}

	private IProject getProject() {
		IProject selectedProject = null;
		String projectName = null;
		try {
			projectName = launchConfiguration.getAttribute(
					Activator.SETTING_PROJECT_NAME, "");
		} catch (final CoreException e) {
			e.printStackTrace();
		}
		if (!projectName.equals(""))
			selectedProject = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projectName);
		return selectedProject;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		this.launchConfiguration = configuration;
		String detectedFilterGraph = "";
		final IProject selectedProject = getProject();
		if ((selectedProject != null) && selectedProject.exists()) {
			final ArrayList<IFile> files = FileUtils.getFiles(selectedProject,
					"fg"); // FIXME
			if (files.size() > 0)
				detectedFilterGraph = files.get(0).getProjectRelativePath()
						.toString();

			txtActiveGraph.setText(getActiveGraphFile()
					.getProjectRelativePath().toString());

			EMFUtils.loadAllResourcesInProjectToEditingDomain(selectedProject,
					"model");

			loadFilterInstances(getActiveGraphFile(), configuration);

			updateLaunchConfiguration(
					filterInstanceToConfigContributer.keySet(), configuration);
			btnSaveFilters.setEnabled(false);
		}
	}

	private boolean isLaunched() {
		final ILaunch[] launches = DebugPlugin.getDefault().getLaunchManager()
				.getLaunches();
		for (final ILaunch launch : launches) {
			if (launch.getLaunchConfiguration().getName()
					.equals(launchConfiguration.getName())) {
				if (!launch.isTerminated())
					return true;
			}
		}
		return false;
	}

	private FilterConfigurationContributer loadFilterConfigurationGUI(
			final FilterInstance filterInstance) {
		final FilterConfigurationContributer configContributer = FilterConfigurationManager
				.getDefault().getContributerForFilter(filterInstance);
		if (configContributer != null) {
			configContributer.createControls(cmpstConfiguration);
			if (isLaunched())
				configContributer.setFilterManager(VideoManager.getDefault()
						.getFilterManager(launchConfiguration.getName()));
			configContributer.setConfigurations(
					EMFUtils.getHashMap(filterInstance.getConfiguration()
							.getEntries()), filterInstance.getName());
			configContributer.addChangeListener(this);
			configContributer.hide();
		}
		return configContributer;
	}

	@SuppressWarnings("unchecked")
	private void loadFilterInstances(final IFile file,
			final ILaunchConfiguration launchConfiguration) {
		filterInstanceToConfigContributer = new HashMap<String, FilterConfigurationContributer>();
		listActivetFilters.removeAll();
		if (file.exists()) {
			final Resource emfResource = EMFUtils.getEMFResource(file);
			try {
				// reload resource to catch any updates
				emfResource.unload();
				emfResource.load(null);
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
			final TreeIterator<EObject> itAllContents = emfResource
					.getAllContents();
			for (; itAllContents.hasNext();) {
				final EObject object = itAllContents.next();
				if (object instanceof FilterInstance) {
					final FilterInstance filterInstance = (FilterInstance) object;
					final FilterConfigurationContributer filterConfigContributer = getFilterConfigurationGUI(filterInstance);
					filterInstanceToConfigContributer.put(
							filterInstance.getName(), filterConfigContributer);
					if (filterConfigContributer != null) {
						try {
							final Map<String, String> filterConfigMap = StringUtils
									.convertToInstanceConfigMap(
											filterInstance.getName(),
											launchConfiguration.getAttributes());
							filterConfigContributer.setConfigurations(
									filterConfigMap, filterInstance.getName());
						} catch (final CoreException e) {
							e.printStackTrace();
						}
					}
					listActivetFilters.add(filterInstance.getName());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse
	 * .debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(FilterLaunchConfigs.FILTER_GRAPH.toString(),
				txtActiveGraph.getText());
		for (final FilterConfigurationContributer contributer : filterInstanceToConfigContributer
				.values()) {
			if (contributer != null) {
				final Map<String, String> updatedFilterConfigMap = contributer
						.getConfigurations();
				final FilterInstance filterInstance = getFilterInstanceForConfigContrinuter(contributer);
				final Map<String, String> updatedLaunchConfigMap = StringUtils
						.convertToFlatConfigMap(filterInstance.getName(),
								updatedFilterConfigMap);

				boolean configurationChange = false;
				// update launch config
				for (final String key : updatedLaunchConfigMap.keySet()) {
					// if config is changed, enable the "Save filters" button
					try {
						final String newValue = updatedLaunchConfigMap.get(key);
						if (!configuration.getAttribute(key, "").equals(
								newValue))
							configurationChange = true;
						configuration.setAttribute(key, newValue);
					} catch (final CoreException e) {
						e.printStackTrace();
					}
				}
				if (configurationChange)
					btnSaveFilters.setEnabled(true);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.
	 * debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void signalConfigurationChange(
			final ConfigurationContributer contributer) {
		updateLaunchConfigurationDialog();
	}

	@SuppressWarnings("unchecked")
	public void updateFilterConfigurations(
			final Collection<String> filterInstances,
			final ILaunchConfiguration launchConfiguration) {
		try {
			final ArrayList<Resource> resourcesToSave = new ArrayList<Resource>();
			final HashMap<FilterInstance, Map<String, String>> filterInstanceToConfig = new HashMap<FilterInstance, Map<String, String>>();
			// fill map of each filter instance with configs read from the
			// launch configs
			final Map<String, String> attributes = launchConfiguration
					.getAttributes();
			for (final String filterInstanceName : filterInstances) {
				final FilterInstance filterInstance = getFilterInstance(filterInstanceName);
				final Map<String, String> filterConfigMap = StringUtils
						.convertToInstanceConfigMap(filterInstance.getName(),
								attributes);
				filterInstanceToConfig.put(filterInstance, filterConfigMap);
				if (!resourcesToSave.contains(filterInstance.eResource()))
					resourcesToSave.add(filterInstance.eResource());
			}

			// apply update to filter configs
			for (final String filterInstanceName : filterInstances) {
				final FilterInstance filterInstance = getFilterInstance(filterInstanceName);
				final ApplyFilterConfigurationCommand command = new ApplyFilterConfigurationCommand(
						filterInstanceToConfig.get(filterInstance),
						filterInstance);
				final TransactionalEditingDomain editingDomain = TransactionUtil
						.getEditingDomain(filterInstance);
				editingDomain.getCommandStack().execute(command);
			}

			for (final Resource resource : resourcesToSave)
				resource.save(null);
		} catch (final CoreException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void updateLaunchConfiguration(
			final Collection<String> filterInstanceNames,
			final ILaunchConfiguration launchConfiguration) {
		ILaunchConfigurationWorkingCopy workingCopy = null;
		if (launchConfiguration.isWorkingCopy())
			workingCopy = (ILaunchConfigurationWorkingCopy) launchConfiguration;
		else
			try {
				workingCopy = launchConfiguration.getWorkingCopy();
			} catch (final CoreException e) {
				e.printStackTrace();
			}

		for (final String filterInstanceName : filterInstanceNames) {
			final FilterInstance filterInstance = getFilterInstance(filterInstanceName);
			final Configuration configuration = filterInstance
					.getConfiguration();
			final Map<String, String> filterConfigMap = EMFUtils
					.getHashMap(configuration.getEntries());
			final Map<String, String> launchConfigMap = StringUtils
					.convertToFlatConfigMap(filterInstance.getName(),
							filterConfigMap);

			for (final String key : launchConfigMap.keySet())
				workingCopy.setAttribute(key, launchConfigMap.get(key));
		}

		try {
			workingCopy.doSave();
		} catch (final CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validateData() {
		final String activeGraphFile = txtActiveGraph.getText();
		if ((activeGraphFile == null) || activeGraphFile.isEmpty()) {
			errorMessage = "Please select filter configuration file";
			return false;
		}
		return true;
	}
}
