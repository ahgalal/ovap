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

import ovap.video.filter.FilterPort;
import ovap.video.filter.VideoFilter;
import ovap.video.filter.FilterPort.PortDirection;
import ovap.video.filter.display.editor.FrameViewerEditor;
import ovap.video.filter.display.editor.LaunchEditorInput;

/**
 * @author Creative
 */
public class FrameDisplayFilter extends VideoFilter {

	private BufferedImage		img;
	private int[]				imgData;
	private FrameViewerEditor viewer;

	@Override
	public void enable(final boolean enable) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean isReadyForAnalysis() {
		return true; // we are always ready
	}
	@Override
	protected void handleConfigurationUpdates(
			final HashMap<String, String> updatedConfigurations) {
		// TODO Auto-generated method stub

	}
	@Override
	protected void definePorts() {
		inPorts = new FilterPort[]{new FilterPort("in", PortDirection.IN)};
		outPorts = new FilterPort[0];		
	}
	@Override
	public void initialize(final String name, final String contextId) {
		super.initialize(name, contextId);
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				try {
					IWorkbenchPage activePage = PlatformUI.getWorkbench()
							.getWorkbenchWindows()[0].getActivePage();

					FrameViewerEditor editorToUse=null;
					IEditorReference[] editorReferences = activePage.getEditorReferences();
					for(IEditorReference editorReference:editorReferences){
						IEditorPart editor = editorReference.getEditor(false);
						if(editor instanceof FrameViewerEditor){
							// check the session name
							FrameViewerEditor viewer = (FrameViewerEditor) editor;
							String sessionName = viewer.getSessionName();
							if(sessionName.equals(contextId)){ // use editor instance
								editorToUse=viewer;
								break;
							}
						}
					}
					if(editorToUse==null){// create a new editor instance
						editorToUse = (FrameViewerEditor) activePage.openEditor(new LaunchEditorInput(contextId), Activator.EDITOR_ID, true);
					}
					setViewer(editorToUse);
					activePage.activate(editorToUse);
					editorToUse.setSessionName(contextId);

				} catch (final PartInitException e) {
					e.printStackTrace();
				}
			}
		});

		setViewer(viewer);
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

	public void setViewer(final FrameViewerEditor viewer) {
		this.viewer = viewer;
	}
	@Override
	protected String[] defineInParameters() {
		return new String[0];
	}
	@Override
	protected String[] defineOutParameters() {
		return new String[0];
	}

}
