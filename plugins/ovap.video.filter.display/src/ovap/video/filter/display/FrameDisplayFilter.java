/**
 * 
 */
package ovap.video.filter.display;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ovap.video.filter.FilterData;
import ovap.video.filter.VideoFilter;

/**
 * @author Creative
 *
 */
public class FrameDisplayFilter extends VideoFilter {

	private static final String	DISPLAY_FRAME_VIEW_ID	= "ovap.video.filter.display.frame.view";
	private BufferedImage img;
	private int[] imgData;
	private FrameView view;

	public void setView(FrameView view) {
		this.view=view;
	}

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
	public void process() {
		//System.out.println("FrameDisplayFilter.process()");
		if(img==null){
			img=new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
			imgData = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		}
		
		if(linkIn.getData()!=null){
			System.arraycopy(linkIn.getData(), 0, imgData, 0,linkIn.getData().length );
		
			view.drawImage(img);
		}else
			System.out.println("Skipping null frame");
	}

	@Override
	public void registerDependentData(FilterData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VideoFilter newInstance(final String name,final String contextId) {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				try {
					String secondaryId = contextId+"."+name;
					view = (FrameView) PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().showView(DISPLAY_FRAME_VIEW_ID, secondaryId,IWorkbenchPage.VIEW_CREATE);
					PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().showView(DISPLAY_FRAME_VIEW_ID, secondaryId,IWorkbenchPage.VIEW_ACTIVATE);
				} catch (PartInitException e) {
					e.printStackTrace();
				}		
			}
		});
		
		FrameDisplayFilter frameDisplayFilter = new FrameDisplayFilter();
		frameDisplayFilter.setName(name);
		frameDisplayFilter.setView(view);
		return frameDisplayFilter;
	}

	@Override
	protected void handleConfigurationUpdates(
			HashMap<String, Object> updatedConfigurations) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getInPortIDs() {
		return new String[]{"in"};
	}

	@Override
	public String[] getOutPortIDs() {
		return new String[0];
	}

}
