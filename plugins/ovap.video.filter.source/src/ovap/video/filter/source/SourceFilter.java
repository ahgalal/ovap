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
	public String[] getInPortIDs() {
		return new String[0];
	}

	@Override
	public String[] getOutPortIDs() {
		return new String[] { "out" };

	}

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, Object> updatedConfigurations) {
		// TODO Auto-generated method stub

	}

	@Override
	public VideoFilter newInstance(final String name, final String contextId) {
		final SourceFilter sourceFilter = new SourceFilter(name, contextId);
		sourceFilter.setName(name);
		return sourceFilter;
	}

	@Override
	public void process() {
		linkOut.setData(linkIn.getData());
	}
}
