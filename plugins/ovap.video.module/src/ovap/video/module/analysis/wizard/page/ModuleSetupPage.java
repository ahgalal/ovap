/**
 * 
 */
package ovap.video.module.analysis.wizard.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import ovap.video.ConfigurationChangeListener;
import ovap.video.ConfigurationContributer;
import ovap.video.Parameter;
import ovap.video.launch.DialogSettings2;
import ovap.video.launch.analysis.wizard.page.AnalysisWizardPage;
import ovap.video.module.Activator;
import ovap.video.module.Module;
import ovap.video.module.ModuleConfigurationContributer;
import ovap.video.module.ModuleManager;
import ovap.video.module.analysis.wizard.page.provider.ModuleData;
import ovap.video.module.analysis.wizard.page.provider.ModuleDataIdColumnLabelProvider;
import ovap.video.module.analysis.wizard.page.provider.ModuleDataNameColumnLabelProvider;
import ovap.video.module.analysis.wizard.page.provider.ModuleIdColumnLabelProvider;
import ovap.video.module.analysis.wizard.page.provider.ParameterIdColumnLabelProvider;
import ovap.video.module.analysis.wizard.page.provider.ParameterNameColumnLabelProvider;
import utils.PDEUtils;
import utils.StringUtils;

/**
 * @author Creative
 */
public class ModuleSetupPage extends AnalysisWizardPage implements
		ConfigurationChangeListener {
	private final class AddModuleInstanceHandler extends SelectionAdapter {
		@Override
		public void widgetSelected(final SelectionEvent e) {
			final ElementListSelectionDialog dialog = new ElementListSelectionDialog(
					getShell(), new ModuleIdColumnLabelProvider());
			dialog.setElements(ModuleManager.getInstalledModules().toArray(
					new Module[0]));
			if (dialog.open() == Window.OK) {
				final Object[] result = dialog.getResult();
				final Module module = (Module) result[0];
				final InputDialog inputDialog = new InputDialog(getShell(),
						"Module instance name",
						"Please enter the name of the new module instance",
						"Module" + moduleInstanceCount++, null);
				if (inputDialog.open() == Window.OK) {
					final String moduleInstanceName = inputDialog.getValue();
					final ModuleData moduleData = new ModuleData(
							moduleInstanceName, module.getID());

					final ArrayList<Parameter> outputParameters = module
							.getOutputParameters();
					final Map<Parameter, Boolean> map = new HashMap<Parameter, Boolean>();
					for (final Parameter parameter : outputParameters)
						map.put(parameter, true);
					selectionData.put(moduleData, map);
					updateModuleInstancesTable();
				}

			}
		}
	}

	private final class ModuleTableSelectionListener implements
			ISelectionChangedListener {
		@Override
		public void selectionChanged(final SelectionChangedEvent event) {
			final ModuleData moduleData = getSelectedModule();
			if (moduleData != null) {
				final Map<Parameter, Boolean> paramsList = selectionData
						.get(moduleData);
				final Set<Parameter> params = paramsList.keySet();
				checkboxTableViewer.setInput(params.toArray());
				for (final Parameter parameter : params) {
					checkboxTableViewer.setChecked(parameter,
							paramsList.get(parameter));
				}

				// show configuration contributer
				final ModuleConfigurationContributer configurationContributer = getConfigurationContributer(moduleData);

				for (final Control child : cmpstConfigs.getChildren()) {
					child.setVisible(false);
					child.setParent(cmpstDummy);
				}
				if (configurationContributer != null) {
					configurationContributer.getContainer().setParent(
							cmpstConfigs);
					configurationContributer.show();
					configurationContributer.getContainer().setLayoutData(
							new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
					cmpstConfigs.layout();
				}

			} else
				checkboxTableViewer.setInput(new Object[0]);
		}
	}

	private Button										btnAdd;

	private Button										btnRemove;
	private CheckboxTableViewer							checkboxTableViewer;
	private Composite									cmpstConfigs;
	private Composite									cmpstDummy;
	private Map<String, ModuleConfigurationContributer>	configurationContributers;
	private Group										grpModulesAndParameters;
	private Label										label;
	private int											moduleInstanceCount	= 0;
	private Map<ModuleData, Map<Parameter, Boolean>>	selectionData;
	private TableViewerColumn							tableViewerColumn;

	private TableViewerColumn							tableViewerColumn_1;
	private TableViewerColumn							tableViewerColumn_2;
	private TableViewerColumn							tableViewerColumn_3;

	private TableColumn									tblclmnId;

	private TableColumn									tblclmnName;

	private TableColumn									tblclmnName_1;

	private TableColumn									tblclmnType;
	private Table										tblModuleInstances;
	private Table										tblParams;
	private TableViewer									tblViewerModuleInstances;

	public ModuleSetupPage() {
		this("");
		configurationContributers = new HashMap<String, ModuleConfigurationContributer>();
	}

	/**
	 * @wbp.parser.constructor
	 */
	protected ModuleSetupPage(final String pageName) {
		super(pageName);
		setDescription("");
		setTitle("Modules Setup");
		setMessage("Set modules' configurations");
		selectionData = new HashMap<ModuleData, Map<Parameter, Boolean>>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(final Composite parent) {
		final Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(1, false));
		new Label(container, SWT.NONE);
		{
			grpModulesAndParameters = new Group(container, SWT.NONE);
			grpModulesAndParameters.setLayoutData(new GridData(SWT.FILL,
					SWT.FILL, true, true, 1, 1));
			grpModulesAndParameters.setText("Modules and Parameters:");
			grpModulesAndParameters.setLayout(new GridLayout(4, false));
			{
				tblViewerModuleInstances = new TableViewer(
						grpModulesAndParameters, SWT.BORDER
								| SWT.FULL_SELECTION);
				tblModuleInstances = tblViewerModuleInstances.getTable();
				tblModuleInstances.setLinesVisible(true);
				tblModuleInstances.setHeaderVisible(true);
				{
					final GridData gd_tblModuleInstances = new GridData(
							SWT.FILL, SWT.FILL, true, true, 2, 2);
					gd_tblModuleInstances.heightHint = 213;
					tblModuleInstances.setLayoutData(gd_tblModuleInstances);
				}
				{
					tableViewerColumn = new TableViewerColumn(
							tblViewerModuleInstances, SWT.NONE);
					tableViewerColumn
							.setLabelProvider(new ModuleDataNameColumnLabelProvider());
					tblclmnName = tableViewerColumn.getColumn();
					tblclmnName.setWidth(100);
					tblclmnName.setText("Name");
				}
				{
					tableViewerColumn_1 = new TableViewerColumn(
							tblViewerModuleInstances, SWT.NONE);
					tableViewerColumn_1
							.setLabelProvider(new ModuleDataIdColumnLabelProvider());
					tblclmnType = tableViewerColumn_1.getColumn();
					tblclmnType.setWidth(100);
					tblclmnType.setText("Type");
				}
				tblViewerModuleInstances
						.setContentProvider(new ArrayContentProvider());
				tblViewerModuleInstances
						.addSelectionChangedListener(new ModuleTableSelectionListener());
			}
			{
				label = new Label(grpModulesAndParameters, SWT.SEPARATOR
						| SWT.VERTICAL);
				label.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false,
						true, 1, 1));
			}
			{
				checkboxTableViewer = CheckboxTableViewer.newCheckList(
						grpModulesAndParameters, SWT.BORDER
								| SWT.FULL_SELECTION);
				checkboxTableViewer
						.addCheckStateListener(new ICheckStateListener() {
							@Override
							public void checkStateChanged(
									final CheckStateChangedEvent event) {
								final ModuleData moduleData = getSelectedModule();
								final Object[] params = (Object[]) checkboxTableViewer
										.getInput();
								final Map<Parameter, Boolean> map = selectionData
										.get(moduleData);
								for (final Object parameter : params) {
									if (checkboxTableViewer
											.getChecked(parameter))
										map.put((Parameter) parameter, true);
									else
										map.put((Parameter) parameter, false);
								}
								updateModuleInstancesTable();
							}
						});
				tblParams = checkboxTableViewer.getTable();
				tblParams.setLinesVisible(true);
				tblParams.setHeaderVisible(true);
				{
					final GridData gd_tblParams = new GridData(SWT.FILL,
							SWT.FILL, true, true, 1, 1);
					gd_tblParams.heightHint = 110;
					tblParams.setLayoutData(gd_tblParams);
				}
				{
					tableViewerColumn_2 = new TableViewerColumn(
							checkboxTableViewer, SWT.NONE);
					tableViewerColumn_2
							.setLabelProvider(new ParameterNameColumnLabelProvider());
					tblclmnName_1 = tableViewerColumn_2.getColumn();
					tblclmnName_1.setWidth(100);
					tblclmnName_1.setText("Name");
				}
				{
					tableViewerColumn_3 = new TableViewerColumn(
							checkboxTableViewer, SWT.NONE);
					tableViewerColumn_3
							.setLabelProvider(new ParameterIdColumnLabelProvider());
					tblclmnId = tableViewerColumn_3.getColumn();
					tblclmnId.setWidth(100);
					tblclmnId.setText("ID");
				}
				checkboxTableViewer
						.setContentProvider(new ArrayContentProvider());
			}
			new Label(grpModulesAndParameters, SWT.NONE);
			{
				cmpstConfigs = new Composite(grpModulesAndParameters, SWT.NONE);
				cmpstConfigs.setLayout(new GridLayout(1, false));
				{
					final GridData gd_cmpstConfigs = new GridData(SWT.FILL,
							SWT.FILL, true, true, 1, 1);
					gd_cmpstConfigs.heightHint = 267;
					gd_cmpstConfigs.widthHint = 420;
					cmpstConfigs.setLayoutData(gd_cmpstConfigs);
				}
			}
			{
				btnAdd = new Button(grpModulesAndParameters, SWT.NONE);
				btnAdd.addSelectionListener(new AddModuleInstanceHandler());
				{
					final GridData gd_btnAdd = new GridData(SWT.RIGHT,
							SWT.CENTER, false, false, 1, 1);
					gd_btnAdd.widthHint = 84;
					btnAdd.setLayoutData(gd_btnAdd);
				}
				btnAdd.setText("Add");
			}
			{
				btnRemove = new Button(grpModulesAndParameters, SWT.NONE);
				btnRemove.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(final SelectionEvent e) {
						final ISelection selection = tblViewerModuleInstances
								.getSelection();
						final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
						if (!structuredSelection.isEmpty()) {
							final Object firstElement = structuredSelection
									.getFirstElement();
							final ModuleData module = (ModuleData) firstElement;
							selectionData.remove(module);
							removeConfigurationContributer(module);
							updateModuleInstancesTable();
						}
					}
				});
				{
					final GridData gd_btnRemove = new GridData(SWT.LEFT,
							SWT.CENTER, false, false, 1, 1);
					gd_btnRemove.widthHint = 82;
					btnRemove.setLayoutData(gd_btnRemove);
				}
				btnRemove.setText("Remove");
			}
			new Label(grpModulesAndParameters, SWT.NONE);
			new Label(grpModulesAndParameters, SWT.NONE);
		}
		{
			cmpstDummy = new Composite(container, SWT.NONE);
			{
				final GridData gd_cmpstDummy = new GridData(SWT.LEFT,
						SWT.CENTER, false, false, 1, 1);
				gd_cmpstDummy.heightHint = 0;
				gd_cmpstDummy.widthHint = 0;
				cmpstDummy.setLayoutData(gd_cmpstDummy);
			}
		}
	}

	private ModuleConfigurationContributer getConfigurationContributer(
			final ModuleData moduleData) {
		final String moduleName = moduleData.name;
		ModuleConfigurationContributer ret = configurationContributers
				.get(moduleName);
		if (ret != null)
			return ret;

		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(Activator.OVAP_VIDEO_MODULE_EP);
		for (final IConfigurationElement element : extensions) {
			for (final IConfigurationElement childElement : element
					.getChildren()) {
				if (childElement.getName().equals(
						Activator.OVAP_VIDEO_MODULE_EP_ELEMENT_CONFIG_GUI_ID)) {
					final ModuleConfigurationContributer configContributer = PDEUtils
							.instantiateExtension(
									ModuleConfigurationContributer.class,
									childElement);
					configurationContributers
							.put(moduleName, configContributer);
					configContributer.createControls(cmpstConfigs);
					final Map<String, String> attributes = getSettings()
							.getAttributes();
					final Map<String, String> configMap = StringUtils
							.convertToInstanceConfigMap(moduleName, attributes);
					configContributer.setConfigurations(configMap, moduleName);
					configContributer.addChangeListener(this);
					configContributer.hide();
					ret = configContributer;
					break;
				}
			}
		}
		return ret;
	}

	private ModuleData getSelectedModule() {
		final IStructuredSelection selection = (IStructuredSelection) tblViewerModuleInstances
				.getSelection();
		final ModuleData moduleData = (ModuleData) selection.getFirstElement();
		return moduleData;
	}

	private void loadSelectionData() {
		final DialogSettings2 settings = getSettings();
		final String moduleNamesStr = settings
				.get(Activator.SETTINGS_SELECTED_MODULES_NAMES);

		if (moduleNamesStr != null) {
			final String[] moduleNames = moduleNamesStr
					.split(Activator.DELIM_SETTINGS_REGEX);
			final ArrayList<ModuleData> modulesData = new ArrayList<ModuleData>();
			for (final String moduleName : moduleNames) {
				final String id = settings.get(moduleName
						+ Activator.SETTINGS_ID_POST_FIX);
				final ModuleData moduleData = new ModuleData(moduleName, id);
				modulesData.add(moduleData);
			}

			selectionData = new HashMap<ModuleData, Map<Parameter, Boolean>>();
			final ArrayList<Module> installedModules = ModuleManager
					.getInstalledModules();

			for (final ModuleData moduleData : modulesData) {
				Module module = null;
				for (final Module tmpModule : installedModules) {
					if (tmpModule.getID().equals(moduleData.id)) {
						module = tmpModule;
						break;
					}
				}

				final ArrayList<Parameter> outputParameters = module
						.getOutputParameters();
				String selectedParamsIdStr = settings.get(moduleData.name
						+ Activator.SETTINGS_PARAMS_POST_FIX);
				final HashMap<Parameter, Boolean> params = new HashMap<Parameter, Boolean>();
				if (selectedParamsIdStr == null)
					selectedParamsIdStr = "";
				final String[] selectedParamsId = selectedParamsIdStr
						.split(Activator.DELIM_SETTINGS_REGEX);

				for (final Parameter tmpParameter : outputParameters) {
					params.put(tmpParameter, false);
					for (final String paramId : selectedParamsId) {
						if (tmpParameter.getId().equals(paramId)) {
							params.put(tmpParameter, true);
							break;
						}
					}
				}

				selectionData.put(moduleData, params);
			}
		}
	}

	private void removeConfigurationContributer(final ModuleData moduleData) {
		final String moduleName = moduleData.name;
		final ModuleConfigurationContributer configurationContributer = configurationContributers
				.get(moduleName);
		configurationContributer.hide();
		// FIXME: is this enough to unload instance from memory? do we need SWT
		// dispose?
		configurationContributers.remove(moduleName);
	}

	@Override
	public void setSettings(final DialogSettings2 settings) {
		super.setSettings(settings);

		loadSelectionData();
		updateModuleInstancesTable();
	}

	@Override
	public void signalConfigurationChange(
			final ConfigurationContributer contributer) {
		updateSettingsFromGUI();
	}

	private void updateModuleInstancesTable() {
		if (tblViewerModuleInstances != null) {
			tblViewerModuleInstances.setInput(selectionData.keySet().toArray());
			getWizard().getContainer().updateButtons();
		}
	}

	@Override
	public void updateSettingsFromGUI() {
		final DialogSettings settings = getSettings();

		final ArrayList<String> moduleNames = new ArrayList<String>();
		for (final ModuleData moduleData : selectionData.keySet()) {
			moduleNames.add(moduleData.name);
		}
		final String moduleNamesStr = StringUtils.join(
				Activator.DELIM_SETTINGS, moduleNames.toArray(new String[0]));
		settings.put(Activator.SETTINGS_SELECTED_MODULES_NAMES, moduleNamesStr);

		for (final ModuleData moduleData : selectionData.keySet()) {
			settings.put(moduleData.name + Activator.SETTINGS_ID_POST_FIX,
					moduleData.id);
			final Map<Parameter, Boolean> params = selectionData
					.get(moduleData);
			final ArrayList<String> paramsIds = new ArrayList<String>();
			for (final Parameter parameter : params.keySet())
				if (params.get(parameter) == true)
					paramsIds.add(parameter.getId());
			final String paramsStr = StringUtils.join(Activator.DELIM_SETTINGS,
					paramsIds.toArray(new String[0]));
			settings.put(moduleData.name + Activator.SETTINGS_PARAMS_POST_FIX,
					paramsStr);

			// update configs
			final ModuleConfigurationContributer configContributer = getConfigurationContributer(moduleData);
			if (configContributer != null) {
				/* configurations data ex: setting1=5 */
				final Map<String, String> configurations = configContributer
						.getConfigurations();
				/* flatMap data ex: moduleA__setting1=5 */
				final Map<String, String> flatMap = StringUtils
						.convertToFlatConfigMap(moduleData.name, configurations);
				for (final String key : flatMap.keySet())
					getSettings().put(key, flatMap.get(key));
			}
		}

	}

	@Override
	public String validateInput() {
		if (selectionData.size() == 0) {
			return "Please add at least one module instance";
		}
		// FIXME: handle module configuration errors, accepting feedback from
		// config contributers
		return null;
	}

}
