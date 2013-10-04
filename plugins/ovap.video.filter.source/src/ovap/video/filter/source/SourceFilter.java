/**
 * 
 */
package ovap.video.filter.source;

import ovap.video.FrameData;
import ovap.video.filter.FilterData;
import ovap.video.filter.VideoFilter;

/**
 * @author Creative
 *
 */
public class SourceFilter extends VideoFilter {
	
	@Override
	public void enable(boolean enable) {
		// TODO Auto-generated method stub
		
	}
	private FrameData data;

	@Override
	public FilterData getFilterData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		return Activator.PLUGIN_ID;
	}

	@Override
	public int getInPortCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOutPortCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void process() {
		linkOut.setData(linkIn.getData());
		//System.out.println("SourceFilter.process()");
	}

	@Override
	public void registerDependentData(FilterData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VideoFilter newInstance(String name,String contextId) {
		SourceFilter sourceFilter = new SourceFilter();
		sourceFilter.setName(name);
		return sourceFilter;
	}

}
