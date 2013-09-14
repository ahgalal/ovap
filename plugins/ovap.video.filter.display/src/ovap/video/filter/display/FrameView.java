package ovap.video.filter.display;

import java.awt.Frame;
import java.awt.Graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class FrameView extends ViewPart {
	private Composite compositeAWT;
	private Frame frame;

	public FrameView() {
		setPartName("FrameView");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.EMBEDDED);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			compositeAWT = new Composite(composite, SWT.BORDER | SWT.EMBEDDED);
			{
				frame = SWT_AWT.new_Frame(compositeAWT);
				
				// FIXME: configuration
				//OVAP.frameGfx=frame.getGraphics();
			}
		}
		// TODO Auto-generated method stub

	}
	
	public Graphics getGraphics(){
		return frame.getGraphics();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
