package ovap.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	void initialize(HashMap<String, Object> settings/*, StreamInfo streamInfo*/);

	ArrayList<Parameter> getOutputParameters();
	Map<Parameter,String> getOutputParametersToModuleInstanceMap();

}
