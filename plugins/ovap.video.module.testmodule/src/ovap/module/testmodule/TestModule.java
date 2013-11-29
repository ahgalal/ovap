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
		System.out.println(blobsLocations.size() + " " + getConfiguration().get(Activator.SETTING_PRINT_CONSOLE));
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
