package ovap.video.launch.analysis.wizard.page;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import ovap.video.Activator;
import ovap.video.launch.DialogSettings2;
import ovap.video.launch.analysis.wizard.AnalysisWizard;

public class AnalysisSessionInfoPage extends AnalysisWizardPage {
	
	private Button	btnBrowseSettingsFile;
	private Group	grpAnalysisSettings;
	private Group	grpBasicInformation;
	private Label	lblComments;
	private Label	lblDate;
	private Label	lblLoadSettingsFile;
	private Label	lblTitle;
	private Text	txtComment;
	private Text	txtDate;
	private Text	txtSettingsFile;
	private Text	txtTitle;

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
	 * 
	 * @param parent
	 */
	@Override
	public void createControl(final Composite parent) {
		final Composite container = new Composite(parent, SWT.NULL);
		setControl(container);

		container.setLayout(new GridLayout(1, false));
		{
			grpBasicInformation = new Group(container, SWT.NONE);
			grpBasicInformation.setLayout(new GridLayout(2, false));
			grpBasicInformation.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
					true, true, 1, 1));
			grpBasicInformation.setText("Basic Information:");
			{
				lblTitle = new Label(grpBasicInformation, SWT.NONE);
				lblTitle.setText("Title:");
			}
			{
				txtTitle = new Text(grpBasicInformation, SWT.BORDER);
				txtTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
						false, 1, 1));
				txtTitle.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						getWizard().getContainer().updateButtons();
					}
				});
			}
			{
				lblDate = new Label(grpBasicInformation, SWT.NONE);
				lblDate.setText("Date:");
			}
			{
				txtDate = new Text(grpBasicInformation, SWT.BORDER);
				txtDate.setEditable(false);
				txtDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
						false, 1, 1));
				Date time = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
				txtDate.setText(dateFormat.format(time));
			}
			{
				lblComments = new Label(grpBasicInformation, SWT.NONE);
				lblComments.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
						false, false, 1, 1));
				lblComments.setText("Comments");
			}
			{
				txtComment = new Text(grpBasicInformation, SWT.BORDER
						| SWT.WRAP | SWT.MULTI);
				{
					final GridData gd_txtComment = new GridData(SWT.FILL,
							SWT.CENTER, true, false, 1, 1);
					gd_txtComment.heightHint = 86;
					txtComment.setLayoutData(gd_txtComment);
				}
			}
		}
		{
			grpAnalysisSettings = new Group(container, SWT.NONE);
			grpAnalysisSettings.setLayout(new GridLayout(3, false));
			grpAnalysisSettings.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
					true, true, 1, 1));
			grpAnalysisSettings.setText("Load Analysis Settings:");
			{
				lblLoadSettingsFile = new Label(grpAnalysisSettings, SWT.NONE);
				lblLoadSettingsFile.setLayoutData(new GridData(SWT.RIGHT,
						SWT.CENTER, false, false, 1, 1));
				lblLoadSettingsFile.setText("Settings file:");
			}
			{
				txtSettingsFile = new Text(grpAnalysisSettings, SWT.BORDER);
				txtSettingsFile.setText("");
				txtSettingsFile.setEditable(false);
				txtSettingsFile.setLayoutData(new GridData(SWT.FILL,
						SWT.CENTER, true, false, 1, 1));
			}
			{
				btnBrowseSettingsFile = new Button(grpAnalysisSettings,
						SWT.NONE);
				btnBrowseSettingsFile
						.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(final SelectionEvent e) {
								final BaseWorkbenchContentProvider contentProvider = new BaseWorkbenchContentProvider() {
									@Override
									public Object[] getChildren(
											final Object element) {
										final Object[] children = super
												.getChildren(element);
										final ArrayList<Object> ret = new ArrayList<Object>();
										for (final Object o : children)
											if (o instanceof IFile)
												if (((IFile) o)
														.getFileExtension()
														.equals(Activator.FILE_EXT_ANALYSIS_SETTINGS))
													ret.add(o);
										return ret.toArray();
									}
								};
								final IProject project = ((AnalysisWizard) getWizard())
										.getProject();

								final ListDialog dialog = new ListDialog(
										getShell());
								dialog.setContentProvider(contentProvider);
								dialog.setLabelProvider(new WorkbenchLabelProvider());
								dialog.setTitle("Load Settings");
								dialog.setMessage("Please select analysis settings file");
								dialog.setInput(project);
								if (dialog.open() == Window.OK) {
									final Object[] result = dialog.getResult();
									if (result.length > 0) {
										final IFile file = (IFile) result[0];
										txtSettingsFile.setText(file
												.getProjectRelativePath()
												.toString());
										((AnalysisWizard) getWizard())
												.loadSettings(new File(file
														.getLocation()
														.toOSString()));
									}
								}
							}
						});
				btnBrowseSettingsFile.setText("...");
			}
		}
	}
	
	@Override
	public void updateSettingsFromGUI() {
		final DialogSettings2 settings = getSettings();
		settings.put(Activator.SETTING_SESSION_NAME, txtTitle.getText());
		settings.put(Activator.SETTING_SESSION_DESCRIPTION, txtComment.getText());
		settings.put(Activator.SETTING_SESSION_DATE, txtDate.getText());
	}

	@Override
	public String validateInput() {
		String errorMessage = null;
		if(txtTitle.getText().isEmpty()){
			errorMessage="Session Title cannot be blank";
		}		
		return errorMessage;
	}
}
