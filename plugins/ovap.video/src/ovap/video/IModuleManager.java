package ovap.video;

import java.util.ArrayList;
/**
 * 
 */

import org.eclipse.jface.dialogs.DialogSettings;

/**
 * @author Creative
 *
 */
public interface IModuleManager {

	void pause();

	void resume();

	void start();

	void stop();

	void registerParameters(ArrayList<Parameter> parameters);

	void initialize(DialogSettings settings);

}
