package ovap.video.filter.display;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.SWT;
import java.awt.Frame;
import org.eclipse.swt.awt.SWT_AWT;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.JRootPane;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;

import ovap.project.OVAP;

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
				OVAP.frameGfx=frame.getGraphics();
			}
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
