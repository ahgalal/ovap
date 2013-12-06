/**
 * 
 */
package ovap.module.testmodule;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import ovap.module.Module;
import ovap.video.Parameter;

/**
 * @author Creative
 */
public class TestModule extends Module {
	@Override
	public String getID() {
		return Activator.PLUGIN_ID;
	}

	@Override
	public void process() {
		ArrayList<Point> blobsLocations = getBlobsLocations();
		int size = blobsLocations.size();
		//System.out.println(size + " " + getConfiguration().get(Activator.SETTING_PRINT_CONSOLE));
		Parameter outputParameter1 = getParametersContainer().getOutputParameter("out_parameter1");
		outputParameter1.setValue(size);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Point> getBlobsLocations() {
		Parameter blobsLocationsParam = getInputParameter("blobs");
		ArrayList<Point> blobsLocations = (ArrayList<Point>)blobsLocationsParam.getValue();
		return blobsLocations;
	}

	@Override
	protected void handleConfigurationUpdates(
			HashMap<String, Object> updatedConfigurations) {
	}
}
