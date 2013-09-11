/**
 * 
 */
package ovap.project.ui;

import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * @author Creative
 *
 */
public abstract class OVAPProjectPageBase extends PropertyPage{
	protected IProject project;
	@Override
	public void setElement(IAdaptable element) {
		super.setElement(element);
		project = (IProject) element;
	}
	
	protected abstract void persistSettings();
	
	@Override
	public boolean performOk() {
		persistSettings();

		return super.performOk();
	}
	@Override
	public Point computeSize() {
		loadValues(getSavedValues());
		return super.computeSize();
	}
	
	protected abstract void loadValues(HashMap<Object, String> values);
	
	protected abstract HashMap<Object, String> getSavedValues();
	protected abstract HashMap<Object, String> getDefaultValues();
}
