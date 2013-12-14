package ovap.module.testmodule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import ovap.video.module.ModuleConfigurationContributer;

/**
 * 
 */

/**
 * @author Creative
 *
 */
public class TestModuleConfigurationContributer extends
		ModuleConfigurationContributer {
	private Button btnPrintConsoleMessages;

	/* (non-Javadoc)
	 * @see ovap.video.ConfigurationContributer#createControls(org.eclipse.swt.widgets.Composite)
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(Composite parent) {
		Composite composite = new Composite(parent, 0);
		setContainer(composite);
		composite.setLayout(new GridLayout(1, false));
		{
			btnPrintConsoleMessages = new Button(composite, SWT.CHECK);
			btnPrintConsoleMessages.setText("Print console messages");
			btnPrintConsoleMessages.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					getConfigurations().put(Activator.SETTING_PRINT_CONSOLE, Boolean.toString(btnPrintConsoleMessages.getSelection()));
					signalConfigurationChange();					
				}
			});
		}
	}

	/* (non-Javadoc)
	 * @see ovap.video.ConfigurationContributer#initializeGUI()
	 */
	@Override
	protected void initializeGUI() {
		String printConsoleStr = getConfigurations().get(Activator.SETTING_PRINT_CONSOLE);
		if(printConsoleStr!=null){
			boolean printConsole = Boolean.parseBoolean(printConsoleStr);
			btnPrintConsoleMessages.setSelection(printConsole);
		}
	}
}
