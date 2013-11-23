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

/**
 * @author Creative
 *
 */
public class SaveSettingsPage extends AnalysisWizardPage {
	private Group grpSaveFile;
	private Label lblSettingsFile;
	private Text txtFileSave;
	private Button btnBrowse;

	public SaveSettingsPage() {
		super("");
		setDescription("Save analysis settings to file");
		setTitle("Save Settings");
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
			grpSaveFile = new Group(container, SWT.NONE);
			grpSaveFile.setLayout(new GridLayout(3, false));
			grpSaveFile.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			grpSaveFile.setText("Save file:");
			{
				lblSettingsFile = new Label(grpSaveFile, SWT.NONE);
				lblSettingsFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblSettingsFile.setText("Settings file:");
			}
			{
				txtFileSave = new Text(grpSaveFile, SWT.BORDER);
				txtFileSave.setEditable(false);
				txtFileSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			}
			{
				btnBrowse = new Button(grpSaveFile, SWT.NONE);
				btnBrowse.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						InputDialog dialog = new InputDialog(getShell(), "File name", "Please select file name to save settings to", "analysis.as", null);
						if(dialog.open()==Window.OK){
							String value = dialog.getValue();
							if(value!=null && !value.isEmpty()){
								txtFileSave.setText(value);
							}
						}
					}
				});
				btnBrowse.setText("...");
			}
		}
	}
	
	public String getSaveFilePath(){
		return txtFileSave.getText();
	}

}
