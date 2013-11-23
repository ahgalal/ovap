/**
 * 
 */
package ovap.video.launch.analysis.command.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import ovap.video.launch.OVAPLaunch;
import ovap.video.launch.StreamTarget;
import ovap.video.launch.analysis.wizard.AnalysisWizard;

/**
 * @author Creative
 *
 */
public class StartAnalysisCommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if(currentSelection instanceof IStructuredSelection){
			IStructuredSelection structuredSelection = (IStructuredSelection) currentSelection;
			Object firstElement = structuredSelection.getFirstElement();
			
			if(firstElement instanceof StreamTarget){
				AnalysisWizard analysisWizard =new AnalysisWizard((OVAPLaunch) ((StreamTarget) firstElement).getLaunch());
				WizardDialog dialog =new WizardDialog(PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell(), analysisWizard);
				dialog.open();
			}else if(firstElement instanceof OVAPLaunch){
				AnalysisWizard analysisWizard =new AnalysisWizard((OVAPLaunch) firstElement);
				WizardDialog dialog =new WizardDialog(PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell(), analysisWizard);
				dialog.open();
			}
		}


		return null;
	}}
