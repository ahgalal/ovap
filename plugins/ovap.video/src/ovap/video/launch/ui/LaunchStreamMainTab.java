/**
 * 
 */
package ovap.video.launch.ui;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.window.Window;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;

import ovap.video.launch.LaunchConfigs;

/**
 * @author Creative
 *
 */
public class LaunchStreamMainTab implements ILaunchConfigurationTab {
	protected Composite topLevel;
	private Button btnBrowseActiveGraph;
	private Group grpFilterGraph;
	private Label lblActiveGraph;
	private Text txtActiveGraph;
	private Group grpProject;
	private Button btnBrowseProject;
	private Text txtProjectName;
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		topLevel = new Composite(parent, 0);
		topLevel.setLayout(new GridLayout(1, false));
		{
			grpProject = new Group(topLevel, SWT.NONE);
			grpProject.setLayout(new GridLayout(14, false));
			grpProject.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
			grpProject.setText("Project:");
			{
				txtProjectName = new Text(grpProject, SWT.BORDER);
				txtProjectName.setEditable(false);
				txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 13, 1));
			}
			{
				btnBrowseProject = new Button(grpProject, SWT.NONE);
				btnBrowseProject.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						//FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(null, false, ResourcesPlugin.getWorkspace().getRoot(), IProject.PROJECT);
						//ListDialog dialog = new ListDialog(null);
						
						
						
						ITreeContentProvider contentProvider =new WorkbenchContentProvider(){
							@Override
							public Object[] getChildren(Object element) {
								ArrayList<Object> ret = new ArrayList<Object>();
								Object[] children = super.getChildren(element);
								for(Object child:children){
									if(child instanceof IProject)
										ret.add(child);
								}
								return ret.toArray(new Object[0]);
							}
						};
						
						ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(null, new WorkbenchLabelProvider(),contentProvider);
						
						dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));
						dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
						//dialog.setContentProvider(contentProvider);
						//dialog.setLabelProvider(new WorkbenchLabelProvider());
						if(dialog.open()==Window.OK){
							IProject selectedProject = (IProject) dialog.getResult()[0];
							txtProjectName.setText(selectedProject.getName());
						}
						
						
					}
				});
				btnBrowseProject.setText("...");
			}
		}

		grpFilterGraph = new Group(topLevel, SWT.NONE);
		grpFilterGraph.setLayout(new GridLayout(3, false));
		grpFilterGraph.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));
		grpFilterGraph.setText("Filter Graph:");
		{
			lblActiveGraph = new Label(grpFilterGraph, SWT.NONE);
			lblActiveGraph.setLayoutData(new GridData(SWT.RIGHT,
					SWT.CENTER, false, false, 1, 1));
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
			btnBrowseActiveGraph
					.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(final SelectionEvent e) {
							// FIXME: use a dialog that displays only
							// available EMF filter graph model file (.fg
							// extension?)
							// ElementListSelectionDialog dialog = new
							// ElementListSelectionDialog(getShell(),
							// PlatformUI.getWorkbench().);
							final IFile[] selections = WorkspaceResourceDialog
									.openFileSelection(null,
											"Filter graph",
											"Select filter graph", false,
											null, null);
							if (selections.length > 0) {
								final IFile selection = selections[0];
								txtActiveGraph.setText(selection
										.getProjectRelativePath()
										.toString());
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
	
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getControl()
	 */
	@Override
	public Control getControl() {
		return topLevel;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			txtActiveGraph.setText(configuration.getAttribute(LaunchConfigs.FILTER_GRAPH.toString(), "none"));
			txtProjectName.setText(configuration.getAttribute(LaunchConfigs.PROJECT_NAME.toString(), "none"));
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(LaunchConfigs.FILTER_GRAPH.toString(), txtActiveGraph.getText());
		configuration.setAttribute(LaunchConfigs.PROJECT_NAME.toString(), txtProjectName.getText());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getErrorMessage()
	 */
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Update stream options";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#canSave()
	 */
	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setLaunchConfigurationDialog(org.eclipse.debug.ui.ILaunchConfigurationDialog)
	 */
	@Override
	public void setLaunchConfigurationDialog(ILaunchConfigurationDialog dialog) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#launched(org.eclipse.debug.core.ILaunch)
	 */
	@Override
	public void launched(ILaunch launch) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	@Override
	public String getName() {
		return "Stream";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
	 */
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#activated(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void activated(ILaunchConfigurationWorkingCopy workingCopy) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#deactivated(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void deactivated(ILaunchConfigurationWorkingCopy workingCopy) {
		// TODO Auto-generated method stub

	}

}
