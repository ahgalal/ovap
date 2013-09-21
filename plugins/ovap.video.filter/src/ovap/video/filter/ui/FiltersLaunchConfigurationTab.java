/**
 * 
 */
package ovap.video.filter.ui;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
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

import ovap.video.filter.FilterLaunchConfigs;
import ovap.video.launch.LaunchConfigs;
import utils.FileUtils;

/**
 * @author Creative
 */
public class FiltersLaunchConfigurationTab extends AbstractLaunchConfigurationTab {
	private Button		btnBrowseActiveGraph;
	protected Composite	container;
	private Group		grpFilterGraph;
	private Label		lblActiveGraph;
	private Text		txtActiveGraph;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#canSave()
	 */
	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return true;
	}

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
		grpFilterGraph.setLayout(new GridLayout(3, false));
		grpFilterGraph.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
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
					// extension?)
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
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getErrorMessage()
	 */
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
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
		} catch (final CoreException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug
	 * .core.ILaunchConfiguration)
	 */
	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#launched(org.eclipse.debug
	 * .core.ILaunch)
	 */
	@Override
	public void launched(final ILaunch launch) {
		// TODO Auto-generated method stub

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
}
