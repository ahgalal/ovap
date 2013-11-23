/**
 * 
 */
package ovap.module.testmodule;

import java.awt.Point;
import java.util.ArrayList;

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
		System.out.println(blobsLocations.size());
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Point> getBlobsLocations() {
		Parameter blobsLocationsParam = getInputParameter("blobs");
		ArrayList<Point> blobsLocations = (ArrayList<Point>)blobsLocationsParam.getValue();
		return blobsLocations;
	}
}
