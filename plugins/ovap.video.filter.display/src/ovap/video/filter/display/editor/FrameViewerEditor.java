/**
 * 
 */
package ovap.video.filter.display.editor;

import java.awt.Frame;
import java.awt.Image;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * @author Creative
 */
public class FrameViewerEditor extends EditorPart {
	public FrameViewerEditor() {
	}

	private Composite						compositeAWT;
	private Frame							frame;
	private String							sessionName;
	private org.eclipse.swt.graphics.Point	viewDimensions;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.
	 * IProgressMonitor)
	 */
	@Override
	public void doSave(final IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	public void drawImage(final Image img) {
		frame.getGraphics().drawImage(img, 0, 0, viewDimensions.x,
				viewDimensions.y, 0, 0, 640, 480, null);
	}

	public String getSessionName() {
		return sessionName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite,
	 * org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(final IEditorSite site, final IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setSessionName(input.getName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void setSessionName(final String sessionName) {
		this.sessionName = sessionName;
		setPartName(sessionName);
	}

}
