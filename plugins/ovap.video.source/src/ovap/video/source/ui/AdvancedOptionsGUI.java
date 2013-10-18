/**
 * 
 */
package ovap.video.source.ui;

import java.util.HashMap;

import org.eclipse.swt.widgets.Composite;

/**
 * @author Creative
 */
public abstract class AdvancedOptionsGUI {
	protected Composite composite;
	private final HashMap<String, String>	options;

	public AdvancedOptionsGUI() {
		options = new HashMap<String, String>();
	}
	
	public abstract void createControls(Composite parent);
	
	public Composite getControl(){
		return composite;
	}
	
	protected HashMap<String, String> getOptions() {
		return options;
	}

	public abstract HashMap<String, String> getUpdatedOptions();

	public abstract void loadOptions(HashMap<String, String> options);

}
