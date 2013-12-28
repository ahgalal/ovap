/**
 * 
 */
package ovap.video.filter.source;

import java.util.HashMap;

import ovap.video.filter.VideoFilter;

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
}
