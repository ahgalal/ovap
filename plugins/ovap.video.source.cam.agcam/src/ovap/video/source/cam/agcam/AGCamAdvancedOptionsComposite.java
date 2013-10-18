/**
 * 
 */
package ovap.video.source.cam.agcam;

import java.util.HashMap;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ovap.video.source.ui.AdvancedOptionsGUI;

/**
 * @author Creative
 */
public class AGCamAdvancedOptionsComposite extends AdvancedOptionsGUI {

	private Label	lbl;

	public AGCamAdvancedOptionsComposite() {
	}

	@Override
	public void createControls(final Composite parent) {
		composite = new Composite(parent, 0);
		composite.setBounds(10, 20, 300, 100);
		lbl = new Label(composite, 0);
		lbl.setBounds(0, 0, 300, 50);
		lbl.setText("PLACEHOLDER FOR CAM ADVANCED OPTIONS");
	}

	/*
	 * (non-Javadoc)
	 * @see ovap.video.source.ui.AdvancedOptionsComposite#getUpdatedOptions()
	 */
	@Override
	public HashMap<String, String> getUpdatedOptions() {
		return getOptions();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * ovap.video.source.ui.AdvancedOptionsComposite#loadOptions(java.util.HashMap
	 * )
	 */
	@Override
	public void loadOptions(final HashMap<String, String> options) {
		// TODO merge incoming options into the local copy
		// EX:
		// lbl.setText(options.get(SourceLaunchConfigs.CAM_FRAME_SIZE.toString()));
	}

}
