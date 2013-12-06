/**
 * 
 */
package ovap.video.launch.analysis.wizard.page;

import org.eclipse.jface.dialogs.InputDialog;
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
import org.eclipse.swt.widgets.Text;

import ovap.video.Activator;

/**
 * @author Creative
 *
 */
public class SaveFilesPage extends AnalysisWizardPage {
	private Group grpSettings;
	private Label lblSettingsFile;
	private Text txtSettingsFile;
	private Button btnBrowseSettings;
	private Group grpResults;
	private Label lblFile;
	private Text txtResultsFile;
	private Button btnBrowseResults;

	public SaveFilesPage() {
		super("");
		setDescription("Save analysis settings/results to file");
		setTitle("Save Settings/Results");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new GridLayout(1, false));
		{
			grpSettings = new Group(container, SWT.NONE);
			grpSettings.setLayout(new GridLayout(3, false));
			grpSettings.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			grpSettings.setText("Settings:");
			{
				lblSettingsFile = new Label(grpSettings, SWT.NONE);
				lblSettingsFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblSettingsFile.setText("File:");
			}
			{
				txtSettingsFile = new Text(grpSettings, SWT.BORDER);
				txtSettingsFile.setEditable(false);
				txtSettingsFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			}
			{
				btnBrowseSettings = new Button(grpSettings, SWT.NONE);
				btnBrowseSettings.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						InputDialog dialog = new InputDialog(getShell(), "File name", "Please select file name to save settings to", "analysis_settings."+Activator.FILE_EXT_ANALYSIS_SETTINGS, null);
						if(dialog.open()==Window.OK){
							String value = dialog.getValue();
							if(value!=null && !value.isEmpty()){
								txtSettingsFile.setText(value);
							}
						}
					}
				});
				btnBrowseSettings.setText("...");
			}
		}
		{
			grpResults = new Group(container, SWT.NONE);
			grpResults.setLayout(new GridLayout(3, false));
			grpResults.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			grpResults.setText("Results:");
			{
				lblFile = new Label(grpResults, SWT.NONE);
				lblFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblFile.setText("File:");
			}
			{
				txtResultsFile = new Text(grpResults, SWT.BORDER);
				txtResultsFile.setEditable(false);
				txtResultsFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			}
			{
				btnBrowseResults = new Button(grpResults, SWT.NONE);
				btnBrowseResults.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						String sessionTitle = getSettings().get(Activator.SETTING_SESSION_NAME);
						String sessionDate = getSettings().get(Activator.SETTING_SESSION_DATE);
						String defaultFileName = sessionTitle+"_"+sessionDate+"."+Activator.FILE_EXT_ANALYSIS_RESULT;
						InputDialog dialog = new InputDialog(getShell(), "File name", "Please select file name to save results to", defaultFileName, null);
						
						if(dialog.open()==Window.OK){
							String value = dialog.getValue();
							if(value!=null && !value.isEmpty()){
								txtResultsFile.setText(value);
							}
						}
					}
				});
				btnBrowseResults.setText("...");
			}
		}
	}
	
	public String getResultsSaveFilePath(){
		return txtResultsFile.getText();
	}
	
	public String getSettingsSaveFilePath(){
		return txtSettingsFile.getText();
	}

	@Override
	public void updateSettingsFromGUI() {
		// nothing to save in this page
	}

	@Override
	public String validateInput() {
		// nothing to validate here, all are optional fields
		return null;
	}

}
