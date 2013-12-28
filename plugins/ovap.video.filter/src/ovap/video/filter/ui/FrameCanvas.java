package ovap.video.filter.ui;

import java.awt.image.BufferedImage;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class FrameCanvas extends Canvas{
	private GC				gc;
	private BufferedImage	bufferedImage;
	public FrameCanvas(Composite parent, int style) {
		super(parent, style);
		gc = new GC(this);
	}
	public GC getGc() {
		return gc;
	}
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
}