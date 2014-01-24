/**
 * 
 */
package ovap.video.filter.source;

import java.util.HashMap;

import ovap.video.filter.FilterPort;
import ovap.video.filter.VideoFilter;
import ovap.video.filter.FilterPort.PortDirection;

/**
 * @author Creative
 */
public class SourceFilter extends VideoFilter {

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, String> updatedConfigurations) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process() {
		linkOut.setData(linkIn.getData());
	}

	@Override
	public boolean isReadyForAnalysis() {
		return true; // we are always ready
	}
	
	@Override
	protected void definePorts() {
		inPorts = new FilterPort[0];
		outPorts = new FilterPort[]{new FilterPort("out", PortDirection.OUT)};		
	}

	@Override
	protected String[] defineInParameters() {
		return new String[0];
	}

	@Override
	protected String[] defineOutParameters() {
		return new String[0];
	}
}
