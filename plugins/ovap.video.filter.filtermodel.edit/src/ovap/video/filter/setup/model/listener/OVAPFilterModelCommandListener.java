/**
 * 
 */
package ovap.video.filter.setup.model.listener;

import java.util.Collection;
import java.util.EventObject;

import org.eclipse.core.resources.ICommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.edit.command.DeleteCommand;

import ovap.video.filter.setup.model.PortInInstance;
import ovap.video.filter.setup.model.PortInstance;

/**
 * @author Creative
 *
 */
public class OVAPFilterModelCommandListener implements CommandStackListener{

	@Override
	public void commandStackChanged(EventObject event) {
		CommandStack commandStack = (CommandStack) event.getSource();
		Command mostRecentCommand = commandStack.getMostRecentCommand();
		if(mostRecentCommand instanceof DeleteCommand){
			DeleteCommand deleteCommand = (DeleteCommand) mostRecentCommand;
			Collection<?> objectsToBeDeleted = deleteCommand.getCollection();
			for(Object obj:objectsToBeDeleted){
				if(obj instanceof PortInstance)
					System.out.println("sss");
			}
		}
	}

}
