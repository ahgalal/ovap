/**
 * 
 */
package ovap.project.ui;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ovap.project.ProjectSettings;
import ovap.project.ProjectSettingsUtil;

/**
 * @author Creative
 * 
 */
public class ProjectInfoPage extends OVAPProjectPageBase {
	private Label labelAuthor;
	private Label labelDescription;
	private Text txtAuthor;
	private Text txtDescription;

	/**
	 * 
	 */
	public ProjectInfoPage() {
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
		final Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(2, false));
		{
			labelAuthor = new Label(container, SWT.NONE);
			labelAuthor.setText("Author");
		}
		{
			txtAuthor = new Text(container, SWT.BORDER);
			{
				final GridData gd_textAuthor = new GridData(SWT.LEFT,
						SWT.CENTER, false, false, 1, 1);
				gd_textAuthor.widthHint = 184;
				txtAuthor.setLayoutData(gd_textAuthor);
			}
		}
		{
			labelDescription = new Label(container, SWT.NONE);
			{
				final GridData gd_labelDescription = new GridData(SWT.LEFT,
						SWT.CENTER, false, false, 1, 1);
				gd_labelDescription.widthHint = 108;
				labelDescription.setLayoutData(gd_labelDescription);
			}
			labelDescription.setText("Description");
		}
		{
			txtDescription = new Text(container, SWT.BORDER);
			txtDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					false, false, 1, 1));
		}

		final String description = ProjectSettingsUtil.getProjectInfo(project,
				ProjectSettings.PROJECT_DESCRIPTION);
		final String author = ProjectSettingsUtil.getProjectInfo(project,
				ProjectSettings.PROJECT_AUTHOR);
		txtDescription.setText(description);
		txtAuthor.setText(author);

		return container;
	}

	@Override
	public void persistSettings() {
		ProjectSettingsUtil.setProjectInfo(project,
				ProjectSettings.PROJECT_DESCRIPTION, txtDescription.getText());
		ProjectSettingsUtil.setProjectInfo(project,
				ProjectSettings.PROJECT_AUTHOR, txtAuthor.getText());

		ProjectSettingsUtil.persistSettings(project);
	}

	@Override
	protected void loadValues(HashMap<Object, String> values) {
		txtDescription.setText(values.get(ProjectSettings.PROJECT_DESCRIPTION));
		txtAuthor.setText(values.get(ProjectSettings.PROJECT_AUTHOR));
	}

	@Override
	protected HashMap<Object, String> getSavedValues() {
		HashMap<Object, String> values = new HashMap<Object, String>();
		values.put(ProjectSettings.PROJECT_DESCRIPTION, ProjectSettingsUtil.getProjectInfo(project, ProjectSettings.PROJECT_DESCRIPTION));
		values.put(ProjectSettings.PROJECT_AUTHOR, ProjectSettingsUtil.getProjectInfo(project, ProjectSettings.PROJECT_AUTHOR));
		
		return values;
	}

	@Override
	protected HashMap<Object, String> getDefaultValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
