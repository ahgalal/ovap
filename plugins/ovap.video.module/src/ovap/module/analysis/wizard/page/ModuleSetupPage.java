/**
 * 
 */
package ovap.module.analysis.wizard.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import ovap.module.Activator;
import ovap.module.Module;
import ovap.module.ModuleManager;
import ovap.video.Parameter;
import ovap.video.launch.analysis.wizard.page.AnalysisWizardPage;

/**
 * @author Creative
 */
public class ModuleSetupPage extends AnalysisWizardPage {

	private class ModuleData {
		public String	id;
		public String	name;

		public ModuleData(final String name, final String id) {
			super();
			this.name = name;
			this.id = id;
		}

		@Override
		public boolean equals(final Object obj) {
			final ModuleData otherObj = (ModuleData) obj;
			if (!name.equals(otherObj.name) || !id.equals(otherObj.id))
				return false;

			return true;
		}
	}

	private class ModuleDataIdColumnLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(final Object element) {
			return ((ModuleData) element).id;
		}
	}

	private class ModuleDataNameColumnLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(final Object element) {
			return ((ModuleData) element).name;
		}
	}

	private class ModuleIdColumnLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(final Object element) {
			return ((Module) element).getID();
		}
	}

	private class ParameterIdColumnLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(final Object element) {
			return ((Parameter) element).getId();
		}
	}

	private class ParameterNameColumnLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(final Object element) {
			return ((Parameter) element).getName();
		}
	}

	private Button										btnAdd;
	private Button										btnRemove;
	private CheckboxTableViewer							checkboxTableViewer;
	private Group										grpModulesAndParameters;
	private Label										label;
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
				tblModuleInstances.setLayoutData(new GridData(SWT.FILL,
						SWT.FILL, true, true, 2, 1));
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
						.addSelectionChangedListener(new ISelectionChangedListener() {
							@Override
							public void selectionChanged(
									final SelectionChangedEvent event) {
								final ModuleData moduleData = getSelectedModule();
								if (moduleData != null) {
									final Map<Parameter, Boolean> paramsList = selectionData
											.get(moduleData);
									final Set<Parameter> params = paramsList
											.keySet();
									checkboxTableViewer.setInput(params
											.toArray());
									for (final Parameter parameter : params) {
										checkboxTableViewer.setChecked(
												parameter,
												paramsList.get(parameter));
									}
								} else
									checkboxTableViewer.setInput(new Object[0]);
							}
						});
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
								saveToSettings();
							}
						});
				tblParams = checkboxTableViewer.getTable();
				tblParams.setLinesVisible(true);
				tblParams.setHeaderVisible(true);
				tblParams.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
						true, 1, 1));
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
			{
				btnAdd = new Button(grpModulesAndParameters, SWT.NONE);
				btnAdd.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(final SelectionEvent e) {
						final ElementListSelectionDialog dialog = new ElementListSelectionDialog(
								getShell(), new ModuleIdColumnLabelProvider());
						dialog.setElements(ModuleManager.getInstalledModules()
								.toArray(new Module[0]));
						if (dialog.open() == Window.OK) {
							final Object[] result = dialog.getResult();
							final Module module = (Module) result[0];
							final ModuleData moduleData = new ModuleData(module
									.getName(), module.getID());

							final ArrayList<Parameter> outputParameters = module
									.getOutputParameters();
							final Map<Parameter, Boolean> map = new HashMap<Parameter, Boolean>();
							for (final Parameter parameter : outputParameters)
								map.put(parameter, true);
							selectionData.put(moduleData, map);
							updateModuleInstancesTable();
						}
					}
				});
				{
					final GridData gd_btnAdd = new GridData(SWT.RIGHT,
							SWT.CENTER, true, false, 1, 1);
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
							updateModuleInstancesTable();
						}
					}
				});
				{
					final GridData gd_btnRemove = new GridData(SWT.LEFT,
							SWT.CENTER, true, false, 1, 1);
					gd_btnRemove.widthHint = 82;
					btnRemove.setLayoutData(gd_btnRemove);
				}
				btnRemove.setText("Remove");
			}
			new Label(grpModulesAndParameters, SWT.NONE);
			new Label(grpModulesAndParameters, SWT.NONE);
		}
	}

	private ModuleData getSelectedModule() {
		final IStructuredSelection selection = (IStructuredSelection) tblViewerModuleInstances
				.getSelection();
		final ModuleData moduleData = (ModuleData) selection.getFirstElement();
		return moduleData;
	}

	private void loadSelectionData() {
		final DialogSettings settings = getSettings();
		final String[] moduleNames = settings.getArray(Activator.SETTINGS_SELECTED_MODULES_NAMES);
		if (moduleNames != null) {
			final ArrayList<ModuleData> modulesData = new ArrayList<ModuleSetupPage.ModuleData>();
			for (final String moduleName : moduleNames) {
				final String id = settings.get(moduleName + Activator.SETTINGS_ID_POST_FIX);
				final ModuleData moduleData = new ModuleData(moduleName, id);
				modulesData.add(moduleData);
			}

			selectionData = new HashMap<ModuleSetupPage.ModuleData, Map<Parameter, Boolean>>();
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
				final String[] selectedParamsId = settings
						.getArray(moduleData.name + Activator.SETTINGS_PARAMS_POST_FIX);
				final HashMap<Parameter, Boolean> params = new HashMap<Parameter, Boolean>();
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

	private void saveToSettings() {
		final DialogSettings settings = getSettings();

		final ArrayList<String> moduleNames = new ArrayList<String>();
		for (final ModuleData moduleData : selectionData.keySet()) {
			moduleNames.add(moduleData.name);
		}

		settings.put(Activator.SETTINGS_SELECTED_MODULES_NAMES, moduleNames.toArray(new String[0]));

		for (final ModuleData moduleData : selectionData.keySet()) {
			settings.put(moduleData.name + Activator.SETTINGS_ID_POST_FIX, moduleData.id);
			final Map<Parameter, Boolean> params = selectionData
					.get(moduleData);
			final ArrayList<String> paramsIds = new ArrayList<String>();
			for (final Parameter parameter : params.keySet())
				if (params.get(parameter) == true)
					paramsIds.add(parameter.getId());
			settings.put(moduleData.name + Activator.SETTINGS_PARAMS_POST_FIX,
					paramsIds.toArray(new String[0]));
		}

	}

	@Override
	public void setSettings(final DialogSettings settings) {
		super.setSettings(settings);

		loadSelectionData();
		updateModuleInstancesTable();
	}

	private void updateModuleInstancesTable() {
		if (tblViewerModuleInstances != null) {
			tblViewerModuleInstances.setInput(selectionData.keySet().toArray());
			saveToSettings();
		}
	}

}
