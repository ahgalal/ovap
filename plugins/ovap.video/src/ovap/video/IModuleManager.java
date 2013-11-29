package ovap.video;

import java.util.ArrayList;
import java.util.HashMap;

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

	void initialize(HashMap<String, Object> settings);

}
