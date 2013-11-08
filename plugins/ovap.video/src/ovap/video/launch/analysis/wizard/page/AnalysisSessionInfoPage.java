package ovap.video.launch.analysis.wizard.page;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class AnalysisSessionInfoPage extends WizardPage {
	private Group grpBasicInformation;
	private Label lblDate;
	private Label lblComments;
	private Label lblTitle;
	private Text txtTitle;
	private Text txtDate;
	private Text txtComment;

	/**
	 * Create the wizard.
	 */
	public AnalysisSessionInfoPage() {
		super("wizardPage");
		setTitle("Basic information");
		setDescription("Analysis session's basic information");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(1, false));
		{
			grpBasicInformation = new Group(container, SWT.NONE);
			grpBasicInformation.setLayout(new GridLayout(2, false));
			grpBasicInformation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			grpBasicInformation.setText("Basic Information:");
			{
				lblTitle = new Label(grpBasicInformation, SWT.NONE);
				lblTitle.setText("Title:");
			}
			{
				txtTitle = new Text(grpBasicInformation, SWT.BORDER);
				txtTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			}
			{
				lblDate = new Label(grpBasicInformation, SWT.NONE);
				lblDate.setText("Date:");
			}
			{
				txtDate = new Text(grpBasicInformation, SWT.BORDER);
				txtDate.setEditable(false);
				txtDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			}
			{
				lblComments = new Label(grpBasicInformation, SWT.NONE);
				lblComments.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblComments.setText("Comments");
			}
			{
				txtComment = new Text(grpBasicInformation, SWT.BORDER | SWT.WRAP | SWT.MULTI);
				{
					GridData gd_txtComment = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
					gd_txtComment.heightHint = 86;
					txtComment.setLayoutData(gd_txtComment);
				}
			}
		}
	}

}
