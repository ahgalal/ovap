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

	public SourceFilter() {
		super("", "");
	}

	public SourceFilter(final String name, final String contextId) {
		super(name, contextId);
	}

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, Object> updatedConfigurations) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process() {
		linkOut.setData(linkIn.getData());
	}
}
