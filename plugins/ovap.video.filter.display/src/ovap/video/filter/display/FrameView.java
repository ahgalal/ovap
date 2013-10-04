package ovap.video.filter.display;

import java.awt.Frame;
import java.awt.Image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class FrameView extends ViewPart {
	private Composite						compositeAWT;
	private Frame							frame;

	private org.eclipse.swt.graphics.Point	viewDimensions;

	public FrameView() {
		setPartName("FrameView");
	}

	@Override
	public void createPartControl(final Composite parent) {

		final Composite composite = new Composite(parent, SWT.EMBEDDED);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			compositeAWT = new Composite(composite, SWT.BORDER | SWT.EMBEDDED);
			{
				frame = SWT_AWT.new_Frame(compositeAWT);

				// FIXME: configuration
				// OVAP.frameGfx=frame.getGraphics();
			}
			viewDimensions = compositeAWT.getSize();
			composite.addControlListener(new ControlListener() {
				@Override
				public void controlMoved(final ControlEvent e) {
				}

				@Override
				public void controlResized(final ControlEvent e) {
					viewDimensions = composite.getSize();
				}
			});
		}
	}

	public void drawImage(final Image img) {
		frame.getGraphics().drawImage(img, 0, 0, viewDimensions.x,
				viewDimensions.y, 0, 0, 640, 480, null);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}
