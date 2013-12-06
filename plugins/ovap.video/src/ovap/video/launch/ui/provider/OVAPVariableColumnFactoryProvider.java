package ovap.video.launch.ui.provider;

import org.eclipse.debug.internal.ui.elements.adapters.VariableColumnFactoryAdapter;
import org.eclipse.debug.internal.ui.elements.adapters.VariableColumnPresentation;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IColumnPresentation;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;
import org.eclipse.debug.ui.IDebugUIConstants;

import ovap.video.launch.OVAPLaunch;

@SuppressWarnings("restriction")
public final class OVAPVariableColumnFactoryProvider extends
		VariableColumnFactoryAdapter {
	@Override
	public IColumnPresentation createColumnPresentation(
			IPresentationContext context, Object element) {
		String id = context.getId();
	    if (IDebugUIConstants.ID_VARIABLE_VIEW.equals(id)) 
	    {
			if (element instanceof OVAPLaunch) {
				return new VariableColumnPresentation();
			}
		}
		return null;
	}

	@Override
	public String getColumnPresentationId(
			IPresentationContext context, Object element) {
		String id = context.getId();
		if (IDebugUIConstants.ID_VARIABLE_VIEW.equals(id)) 
		{
			if (element instanceof OVAPLaunch) {
				return VariableColumnPresentation.DEFAULT_VARIABLE_COLUMN_PRESENTATION;
			}
		}
		return null;
	}
}