/**
 * 
 */
package ovap.video.filter;

import java.awt.Point;

import ovap.video.FrameData;

/**
 * @author Creative
 *
 */
public class Link {

	private FrameData data;
	
	public Link(int width,int height) {
		data = new FrameData(width, height);
	}
	
	public Point getFrameSize(){
		return new Point(data.getWidth(),data.getHeight());
	}
	
	public int[] getData() {
		return data.getFrameData();
	}
	
	public void setData(int[] data){
		this.data.setFrameData(data);
	}

}
