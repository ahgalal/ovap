/**
 * 
 */
package ovap.video.filter.display;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import ovap.project.OVAP;
import ovap.video.filter.FilterData;
import ovap.video.filter.VideoFilter;

/**
 * @author Creative
 *
 */
public class FrameDisplayFilter extends VideoFilter {

	private BufferedImage img;
	private int[] imgData;

	@Override
	public void enable(boolean enable) {
		// TODO Auto-generated method stub
		
	}

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
		System.out.println("FrameDisplayFilter.process()");
		if(img==null){
			img=new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
			imgData = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		}
		
		if(linkIn.getData()!=null){
			System.arraycopy(linkIn.getData(), 0, imgData, 0,linkIn.getData().length );
		
			OVAP.frameGfx.drawImage(img, 0, 0, null);
		}else
			System.out.println("Skipping null frame");
	}

	@Override
	public void registerDependentData(FilterData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VideoFilter newInstance() {
		return new FrameDisplayFilter();
	}

}
