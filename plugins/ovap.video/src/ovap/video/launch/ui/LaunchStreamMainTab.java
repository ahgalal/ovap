/**
 * 
 */
package ovap.video.launch.ui;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;

import ovap.video.launch.LaunchConfigs;

/**
 * @author Creative
 */
public class LaunchStreamMainTab extends OVAPLaunchConfigurationTab {
	private Button		btnBrowseProject;

	private Group		grpProject;
	protected Composite	topLevel;
	private Text		txtProjectName;

	protected boolean validateData(){
		String projName = txtProjectName.getText();
		if(projName==null || projName.isEmpty()){
			errorMessage="Please select a valid project";
			return false;
		}
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		if(!project.exists()){
			errorMessage="Selected project does not exist, Please select a valid project";
			return false;
		}
		errorMessage=null;
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
		topLevel = new Composite(parent, 0);
		topLevel.setLayout(new GridLayout(1, false));
		{
			grpProject = new Group(topLevel, SWT.NONE);
			grpProject.setLayout(new GridLayout(14, false));
			grpProject.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
					false, 1, 1));
			grpProject.setText("Project:");
			{
				txtProjectName = new Text(grpProject, SWT.BORDER);
				txtProjectName.setEditable(false);
				txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.TOP,
						true, false, 13, 1));
			}
			{
				btnBrowseProject = new Button(grpProject, SWT.NONE);
				btnBrowseProject.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(final SelectionEvent e) {
						// FilteredResourcesSelectionDialog dialog = new
						// FilteredResourcesSelectionDialog(null, false,
						// ResourcesPlugin.getWorkspace().getRoot(),
						// IProject.PROJECT);
						// ListDialog dialog = new ListDialog(null);

						final ITreeContentProvider contentProvider = new WorkbenchContentProvider() {
							@Override
							public Object[] getChildren(final Object element) {
								final ArrayList<Object> ret = new ArrayList<Object>();
								final Object[] children = super
										.getChildren(element);
								for (final Object child : children) {
									if (child instanceof IProject)
										ret.add(child);
								}
								return ret.toArray(new Object[0]);
							}
						};

						final ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
								null, new WorkbenchLabelProvider(),
								contentProvider);

						dialog.setComparator(new ResourceComparator(
								ResourceComparator.NAME));
						dialog.setInput(ResourcesPlugin.getWorkspace()
								.getRoot());
						// dialog.setContentProvider(contentProvider);
						// dialog.setLabelProvider(new
						// WorkbenchLabelProvider());
						if (dialog.open() == Window.OK) {
							final IProject selectedProject = (IProject) dialog
									.getResult()[0];
							txtProjectName.setText(selectedProject.getName());
							updateLaunchConfigurationDialog();
						}

					}
				});
				btnBrowseProject.setText("...");
			}
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
		return topLevel;
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
		return "Update stream options";
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	@Override
	public String getName() {
		return "Main";
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
			txtProjectName.setText(configuration.getAttribute(
					LaunchConfigs.PROJECT_NAME.toString(), "none"));
		} catch (final CoreException e) {
			e.printStackTrace();
		}
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
		configuration.setAttribute(LaunchConfigs.PROJECT_NAME.toString(),
				txtProjectName.getText());
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
