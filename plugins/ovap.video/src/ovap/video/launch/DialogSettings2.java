/**
 * 
 */
package ovap.video.launch;

import java.lang.reflect.Field;
import java.util.Map;

import org.eclipse.jface.dialogs.DialogSettings;

/**
 * @author Creative
 *
 */
public class DialogSettings2 extends DialogSettings {

	public DialogSettings2(String sectionName) {
		super(sectionName);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getAttributes(){
		Field items;
		try {
			items = getClass().getSuperclass().getDeclaredField("items");
			items.setAccessible(true);
			Object object = items.get(this);
			return (Map<String, String>) object;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
