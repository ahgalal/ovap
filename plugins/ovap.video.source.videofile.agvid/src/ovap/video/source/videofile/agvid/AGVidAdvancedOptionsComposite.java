/**
 * 
 */
package ovap.video.source.videofile.agvid;

import java.util.HashMap;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ovap.video.source.ui.AdvancedOptionsGUI;

/**
 * @author Creative
 */
public class AGVidAdvancedOptionsComposite extends AdvancedOptionsGUI {
	private Label	lbl;

	@Override
	public void createControls(final Composite parent) {
		composite = new Composite(parent, 0);
		composite.setBounds(10, 20, 300, 100);
		lbl = new Label(composite, 0);
		lbl.setBounds(0, 0, 300, 50);
		lbl.setText("PLACEHOLDER FOR VIS ADVANCED OPTIONS");
	}

	@Override
	public HashMap<String, String> getUpdatedOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadOptions(final HashMap<String, String> options) {
		// TODO Auto-generated method stub

	}

}
