/**
 * 
 */
package ovap.video.filter.display;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.part.NullEditorInput;

import ovap.video.filter.VideoFilter;
import ovap.video.filter.display.editor.FrameViwerEditor;

/**
 * @author Creative
 */
public class FrameDisplayFilter extends VideoFilter {

	private BufferedImage		img;
	private int[]				imgData;
	private FrameViwerEditor viewer;

	public FrameDisplayFilter() {
		super("", "");
	}

	public FrameDisplayFilter(final String name, final String contextId) {
		super(name, contextId);
	}

	@Override
	public void enable(final boolean enable) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getInPortIDs() {
		return new String[] { "in" };
	}

	@Override
	public String[] getOutPortIDs() {
		return new String[0];
	}

	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, Object> updatedConfigurations) {
		// TODO Auto-generated method stub

	}

	@Override
	public VideoFilter newInstance(final String name, final String contextId) {
		Display.getDefault().syncExec(new Runnable() {

			@SuppressWarnings("restriction")
			@Override
			public void run() {
				try {
					IWorkbenchPage activePage = PlatformUI.getWorkbench()
							.getWorkbenchWindows()[0].getActivePage();

					FrameViwerEditor editorToUse=null;
					IEditorReference[] editorReferences = activePage.getEditorReferences();
					for(IEditorReference editorReference:editorReferences){
						IEditorPart editor = editorReference.getEditor(false);
						if(editor instanceof FrameViwerEditor){
							// check the session name
							FrameViwerEditor viewer = (FrameViwerEditor) editor;
							String sessionName = viewer.getSessionName();
							if(sessionName.equals(contextId)){ // use editor instance
								editorToUse=viewer;
								break;
							}
						}
					}
					if(editorToUse==null){// create a new editor instance
						editorToUse = (FrameViwerEditor) activePage.openEditor(new NullEditorInput(), Activator.EDITOR_ID, true);
						editorToUse.setSessionName(contextId);
					}
					setViewer(editorToUse);
					activePage.activate(editorToUse);

				} catch (final PartInitException e) {
					e.printStackTrace();
				}
			}
		});

		final FrameDisplayFilter frameDisplayFilter = new FrameDisplayFilter(
				name, contextId);
		frameDisplayFilter.setViewer(viewer);
		return frameDisplayFilter;
	}

	@Override
	public void process() {
		// System.out.println("FrameDisplayFilter.process()");
		if (img == null) {
			img = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
			imgData = ((DataBufferInt) img.getRaster().getDataBuffer())
					.getData();
		}

		if (linkIn.getData() != null) {
			System.arraycopy(linkIn.getData(), 0, imgData, 0,
					linkIn.getData().length);

			//view.drawImage(img);
			viewer.drawImage(img);
		} else
			System.out.println("Skipping null frame");
	}

	public void setViewer(final FrameViwerEditor viewer) {
		this.viewer = viewer;
	}

}
