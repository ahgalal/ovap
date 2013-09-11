/**
 * 
 */
package ovap.video.filter.ui;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
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
import org.eclipse.swt.widgets.Text;

import ovap.project.ui.OVAPProjectPageBase;
import ovap.video.filter.FilterSettings;
import ovap.video.filter.FilterSettingsUtil;

/**
 * @author Creative
 * 
 */
public class FilterManagerPage extends OVAPProjectPageBase {
	private Button btnBrowseActiveGraph;
	private Group grpFilterGraph;
	private Label lblActiveGraph;
	private Text txtActiveGraph;

	/**
	 * 
	 */
	public FilterManagerPage() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(final Composite parent) {
		final Composite container = new Composite(parent, 0);
		container.setLayout(new GridLayout(1, false));
		{
			grpFilterGraph = new Group(container, SWT.NONE);
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
										.openFileSelection(getShell(),
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
		return container;
	}

	@Override
	public void persistSettings() {
		FilterSettingsUtil.setFilterSetting(project, FilterSettings.ACTIVE_GRAPH, txtActiveGraph.getText());
	}

	@Override
	protected void loadValues(HashMap<Object, String> values) {
		txtActiveGraph.setText(values.get(FilterSettings.ACTIVE_GRAPH));
	}

	@Override
	protected HashMap<Object, String> getSavedValues() {
		HashMap<Object, String> values = new HashMap<Object, String>();
		values.put(FilterSettings.ACTIVE_GRAPH, FilterSettingsUtil.getFilterSetting(project, FilterSettings.ACTIVE_GRAPH));
		
		return values;
	}

	@Override
	protected HashMap<Object, String> getDefaultValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
