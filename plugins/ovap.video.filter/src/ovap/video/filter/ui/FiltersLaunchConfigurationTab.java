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
import ovap.video.filter.FilterConfigurationChangeListener;
import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.FilterConfigurationManager;
import ovap.video.filter.FilterLaunchConfigs;
import ovap.video.filter.setup.model.Configuration;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.launch.LaunchConfigs;
import ovap.video.launch.ui.OVAPLaunchConfigurationTab;
import utils.FileUtils;

/**
 * @author Creative
 */
public class FiltersLaunchConfigurationTab extends OVAPLaunchConfigurationTab implements FilterConfigurationChangeListener {
	private Button		btnBrowseActiveGraph;
	protected Composite	container;
	private Group		grpFilterGraph;
	private Label		lblActiveGraph;
	private Text		txtActiveGraph;
	private Group grpFilterConfigurations;
	private List listActivetFilters;
	private Label lblSs;
	private Composite cmpstConfiguration;
	private HashMap<FilterInstance,FilterConfigurationContributer> filterInstanceToConfigContributer;
	private ILaunchConfiguration	launchConfiguration;
	private Button btnSaveFilters;

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
		grpFilterGraph.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
						loadFilterInstances(selection,launchConfiguration);
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
			grpFilterConfigurations.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			grpFilterConfigurations.setText("Filter Configurations:");
			{
				listActivetFilters = new List(grpFilterConfigurations, SWT.BORDER);
				{
					GridData gd_lisActivetFilters = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 2);
					gd_lisActivetFilters.widthHint = 119;
					listActivetFilters.setLayoutData(gd_lisActivetFilters);
					listActivetFilters.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							String[] selection = listActivetFilters.getSelection();
							if(selection!=null && selection.length>0){
								// get FilterInstance for the selected filter instance
								for(FilterInstance filterInstance:filterInstanceToConfigContributer.keySet()){
									if(filterInstance.getName().equals(selection[0])){
										FilterConfigurationContributer configContributer = getFilterConfigurationGUI(filterInstance);
										for(Control child:cmpstConfiguration.getChildren())
											child.setVisible(false);
										if(configContributer!=null)
											configContributer.getContainer().setVisible(true);
									}
								}
							}
						}
					});
				}
			}
			{
				lblSs = new Label(grpFilterConfigurations, SWT.SEPARATOR | SWT.VERTICAL);
				lblSs.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 2));
				lblSs.setText("ss");
			}
			{
				cmpstConfiguration = new Composite(grpFilterConfigurations, SWT.NONE);
				cmpstConfiguration.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			}
			{
				btnSaveFilters = new Button(grpFilterConfigurations, SWT.NONE);
				btnSaveFilters.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						updateFilterConfigurations(filterInstanceToConfigContributer.keySet(), launchConfiguration);
					}
				});
				btnSaveFilters.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				btnSaveFilters.setText("Save Filters");
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void loadFilterInstances(IFile file,ILaunchConfiguration launchConfiguration){
		filterInstanceToConfigContributer=new HashMap<FilterInstance, FilterConfigurationContributer>();
		listActivetFilters.removeAll();
		if(file.exists()){
			Resource emfResource = EMFUtils.getEMFResource(file);
			try {
				// reload resource to catch any updates
				emfResource.unload();
				emfResource.load(null);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			TreeIterator<EObject> itAllContents = emfResource.getAllContents();
			for(;itAllContents.hasNext();){
				EObject object = itAllContents.next();
				if(object instanceof FilterInstance){
					FilterInstance filterInstance = (FilterInstance) object;
					FilterConfigurationContributer filterConfigContributer = getFilterConfigurationGUI(filterInstance);
					filterInstanceToConfigContributer.put(filterInstance, filterConfigContributer);
					if(filterConfigContributer!=null){
						try {
							Map<String, String> filterConfigMap = convertToFilterConfigMap(filterInstance.getName(), launchConfiguration.getAttributes());
							filterConfigContributer.setConfigurations(filterConfigMap);
						} catch (CoreException e) {
							e.printStackTrace();
						}
						
					}
					listActivetFilters.add(filterInstance.getName());
				}
			}
		}
	}
	
	private FilterConfigurationContributer getFilterConfigurationGUI(FilterInstance filterInstance){
		FilterConfigurationContributer configContributer  = filterInstanceToConfigContributer.get(filterInstance);
		if(configContributer==null)
			configContributer = loadFilterConfigurationGUI(filterInstance);
		return configContributer;
	}
	
	private FilterConfigurationContributer loadFilterConfigurationGUI(FilterInstance filterInstance){
		FilterConfigurationContributer configContributer = FilterConfigurationManager.getDefault().getContributerForObject(filterInstance);
		if(configContributer!=null){
			configContributer.createControls(cmpstConfiguration);
			configContributer.setConfigurations(EMFUtils.getHashMap(filterInstance.getConfiguration().getEntries()));
			configContributer.addChangeListener(this);
			configContributer.getContainer().setBounds(0,0,100,100);
			configContributer.getContainer().setVisible(false);
		}
		return configContributer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getControl()
	 */
	@Override
	public Control getControl() {
		return container;
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
	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		this.launchConfiguration=configuration;
		try {
			String detectedFilterGraph="";
			String projectName = configuration.getAttribute(LaunchConfigs.PROJECT_NAME.toString(), "");
			IProject selectedProject=null;
			if(!projectName.equals(""))
				selectedProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			if(selectedProject!=null && selectedProject.exists()){
				ArrayList<IFile> files = FileUtils.getFiles(selectedProject, "fg");
				if(files.size()>0)
					detectedFilterGraph=files.get(0).getProjectRelativePath().toString();
			}
			String filterGraph = configuration.getAttribute(
					FilterLaunchConfigs.FILTER_GRAPH.toString(), detectedFilterGraph);
			if(filterGraph.equals(""))
				filterGraph=detectedFilterGraph;
			txtActiveGraph.setText(filterGraph);
			loadFilterInstances(selectedProject.getFile(filterGraph),configuration);
			
			updateLaunchConfiguration(filterInstanceToConfigContributer.keySet(), configuration);
		} catch (final CoreException e) {
			e.printStackTrace();
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
		
		for(FilterConfigurationContributer contributer:filterInstanceToConfigContributer.values()){
			if(contributer!=null){
				Map<String, String> updatedFilterConfigMap = contributer.getConfigurations();
				FilterInstance filterInstance = getFilterInstanceForConfigContrinuter(contributer);
				Map<String, String> updatedLaunchConfigMap = convertToLaunchConfigMap(filterInstance.getName(), updatedFilterConfigMap);

				// update launch config
				for(String key:updatedLaunchConfigMap.keySet()){
					configuration.setAttribute(key, updatedLaunchConfigMap.get(key));
				}
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
	protected boolean validateData() {
		String activeGraphFile = txtActiveGraph.getText();
		if(activeGraphFile==null || activeGraphFile.isEmpty()){
			errorMessage="Please select filter configuration file";
			return false;
		}
		return true;
	}
	
	private FilterInstance getFilterInstanceForConfigContrinuter(FilterConfigurationContributer contributer){
		FilterInstance filterInstance = null;
		for(FilterInstance tmpFilterInstance:filterInstanceToConfigContributer.keySet())
			if(filterInstanceToConfigContributer.get(tmpFilterInstance)==contributer){
				filterInstance = tmpFilterInstance;
				break;
			}
		return filterInstance;
	}

	@Override
	public void signalConfigurationChange(
			FilterConfigurationContributer contributer) {
		updateLaunchConfigurationDialog();
	}
	
	public static void updateLaunchConfiguration(Collection<FilterInstance> filterInstances,ILaunchConfiguration launchConfiguration){
		ILaunchConfigurationWorkingCopy workingCopy = null;
		if(launchConfiguration.isWorkingCopy())
			workingCopy = (ILaunchConfigurationWorkingCopy) launchConfiguration;
		else
			try {
				workingCopy=launchConfiguration.getWorkingCopy();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		
		for(FilterInstance filterInstance:filterInstances){
			Configuration configuration = filterInstance.getConfiguration();
			Map<String, String> filterConfigMap = EMFUtils.getHashMap(configuration.getEntries());
			Map<String, String> launchConfigMap = convertToLaunchConfigMap(filterInstance.getName(), filterConfigMap);
			
			for(String key: launchConfigMap.keySet())
				workingCopy.setAttribute(key,launchConfigMap.get(key));
		}
		
		try {
			workingCopy.doSave();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> convertToLaunchConfigMap(String filterName,Map<String, String> filterConfigMap){
		Map<String, String> launchConfigMap= new HashMap<String, String>();
		
		for(String key:filterConfigMap.keySet()){
			launchConfigMap.put(filterName+"__"+key,filterConfigMap.get(key));
		}
		return launchConfigMap;
	}
	
	public static Map<String, String> convertToFilterConfigMap(String filterName,Map<String, String> attributes){
		Map<String, String> filterConfigMap= new HashMap<String, String>();
		
		for(String key:attributes.keySet()){
			if(key.startsWith(filterName+"__")){
				String filterCfgKey = key.substring(key.indexOf("__")+2);
				String filterCfgValue = attributes.get(key);
				
				filterConfigMap.put(filterCfgKey, filterCfgValue);
			}
		}
		return filterConfigMap;
	}
	
	@SuppressWarnings("unchecked")
	public static void updateFilterConfigurations(Collection<FilterInstance> filterInstances,ILaunchConfiguration launchConfiguration){
		try {
			HashMap<FilterInstance,Map<String, String>> filterInstanceToConfig = new HashMap<FilterInstance, Map<String,String>>();
			// fill map of each filter instance with configs read from the launch configs 
			Map<String, String> attributes = launchConfiguration.getAttributes();
			for(FilterInstance filterInstance:filterInstances){
				Map<String, String> filterConfigMap = convertToFilterConfigMap(filterInstance.getName(), attributes);
				filterInstanceToConfig.put(filterInstance, filterConfigMap);
			}
			
			// apply update to filter configs
			for(FilterInstance filterInstance:filterInstances){
				ApplyFilterConfigurationCommand command = new ApplyFilterConfigurationCommand(filterInstanceToConfig.get(filterInstance), filterInstance);
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(filterInstance);
				editingDomain.getCommandStack().execute(command);
			}
			
			filterInstances.iterator().next().eResource().save(null);
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
