package ovap.project.examples.ui;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class DemoWizardMainPage extends WizardPage {
	private Label lblProjectName;
	private Text txtProjectName;

	/**
	 * Create the wizard.
	 */
	public DemoWizardMainPage() {
		super("wizardPage");
		setTitle("Demo project");
		setDescription("Creates a new OVAP demo project");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(2, false));
		{
			lblProjectName = new Label(container, SWT.NONE);
			lblProjectName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblProjectName.setText("Project name:");
		}
		{
			txtProjectName = new Text(container, SWT.BORDER);
			txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		}
	}
	
	public String getProjectName(){
		return txtProjectName.getText();
	}

}
