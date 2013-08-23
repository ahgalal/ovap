package ovap.project.ui;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;

public class NewProjectPageA extends WizardPage {
	private Label lblProjectName;
	private Text textProjectName;
	private Label lblAuthor;
	private Text textAuthor;
	private FormData fd_lblAuthor;
	private Label lblDescription;
	private Text textDescription;

	/**
	 * Create the wizard.
	 */
	public NewProjectPageA() {
		super("wizardPage");
		setTitle("New OVAP Project");
		setDescription("This wizard lets you create a new OVAP project");
	}
	
	public String getProjectName(){
		return textProjectName.getText();
	}
	
	public String getProjectDescription(){
		return textDescription.getText();
	}
	
	public String getProjectAuthor(){
		return textAuthor.getText();
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FormLayout());
		{
			lblProjectName = new Label(container, SWT.NONE);
			{
				FormData fd_lblProjectName = new FormData();
				fd_lblProjectName.top = new FormAttachment(0, 10);
				fd_lblProjectName.left = new FormAttachment(0, 10);
				lblProjectName.setLayoutData(fd_lblProjectName);
			}
			lblProjectName.setText("Project Name:");
		}
		{
			textProjectName = new Text(container, SWT.BORDER);
			{
				FormData fd_textProjectName = new FormData();
				fd_textProjectName.top = new FormAttachment(lblProjectName, -3, SWT.TOP);
				fd_textProjectName.right = new FormAttachment(lblProjectName, 332, SWT.RIGHT);
				fd_textProjectName.left = new FormAttachment(lblProjectName, 53);
				textProjectName.setLayoutData(fd_textProjectName);
			}
		}
		{
			lblAuthor = new Label(container, SWT.NONE);
			{
				fd_lblAuthor = new FormData();
				fd_lblAuthor.left = new FormAttachment(0, 10);
				lblAuthor.setLayoutData(fd_lblAuthor);
			}
			lblAuthor.setText("Author");
		}
		{
			textAuthor = new Text(container, SWT.BORDER);
			fd_lblAuthor.top = new FormAttachment(textAuthor, 3, SWT.TOP);
			{
				FormData fd_textAuthor = new FormData();
				fd_textAuthor.top = new FormAttachment(textProjectName, 46);
				fd_textAuthor.right = new FormAttachment(textProjectName, 0, SWT.RIGHT);
				fd_textAuthor.left = new FormAttachment(textProjectName, 0, SWT.LEFT);
				textAuthor.setLayoutData(fd_textAuthor);
			}
		}
		{
			lblDescription = new Label(container, SWT.NONE);
			{
				FormData fd_lblDescription = new FormData();
				fd_lblDescription.top = new FormAttachment(lblProjectName, 17);
				fd_lblDescription.left = new FormAttachment(0, 10);
				lblDescription.setLayoutData(fd_lblDescription);
			}
			lblDescription.setText("Description");
		}
		{
			textDescription = new Text(container, SWT.BORDER);
			{
				FormData fd_textDescription = new FormData();
				fd_textDescription.right = new FormAttachment(textProjectName, 0, SWT.RIGHT);
				fd_textDescription.bottom = new FormAttachment(textAuthor, -11);
				fd_textDescription.left = new FormAttachment(textProjectName, 0, SWT.LEFT);
				textDescription.setLayoutData(fd_textDescription);
			}
		}
	}
}
