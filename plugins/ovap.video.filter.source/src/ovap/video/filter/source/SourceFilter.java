/**
 * 
 */
package ovap.video.filter.source;

import java.util.HashMap;

import ovap.video.filter.FilterData;
import ovap.video.filter.VideoFilter;

/**
 * @author Creative
 */
public class SourceFilter extends VideoFilter {

	@Override
	public void enable(final boolean enable) {
		// TODO Auto-generated method stub
	}

	@Override
	public FilterData getFilterData() {
		return null;
	}

	@Override
	public String getID() {
		return Activator.PLUGIN_ID;
	}

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, Object> updatedConfigurations) {
		// TODO Auto-generated method stub

	}

	@Override
	public VideoFilter newInstance(final String name, final String contextId) {
		final SourceFilter sourceFilter = new SourceFilter();
		sourceFilter.setName(name);
		return sourceFilter;
	}

	@Override
	public void process() {
		linkOut.setData(linkIn.getData());
		// System.out.println("SourceFilter.process()");
	}

	@Override
	public void registerDependentData(final FilterData data) {
	}
	@Override
	public String[] getInPortIDs() {
		return new String[0];
	}

	@Override
	public String[] getOutPortIDs() {
		return new String[]{"out"};
		
	}
}
