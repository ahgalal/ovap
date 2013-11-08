/**
 * 
 */
package ovap.video.launch.analysis.command.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

import ovap.video.launch.analysis.wizard.AnalysisWizard;

/**
 * @author Creative
 *
 */
public class StartAnalysisCommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		AnalysisWizard analysisWizard =new AnalysisWizard();
		WizardDialog dialog =new WizardDialog(PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell(), analysisWizard);
		dialog.open();
		return null;
	}}
