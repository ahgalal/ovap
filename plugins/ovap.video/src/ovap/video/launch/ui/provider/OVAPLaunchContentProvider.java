/**
 * 
 */
package ovap.video.launch.ui.provider;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.internal.ui.model.elements.ElementContentProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IViewerUpdate;
import org.eclipse.debug.ui.IDebugUIConstants;

import ovap.video.launch.OVAPLaunch;

/**
 * @author Creative
 */
@SuppressWarnings("restriction")
public class OVAPLaunchContentProvider extends ElementContentProvider {

	@Override
	protected int getChildCount(final Object element,
			final IPresentationContext context, final IViewerUpdate monitor)
			throws CoreException {
		return getChildren(element, 0, 0, context, monitor).length;
	}

	@Override
	protected Object[] getChildren(final Object parent, final int index,
			final int length, final IPresentationContext context,
			final IViewerUpdate monitor) throws CoreException {
		final Object[] children = ((OVAPLaunch) parent).getChildren();
		
		if (context.getId().equals(IDebugUIConstants.ID_DEBUG_VIEW)) {
			return children;
		}/* else if (context.getId().equals(IDebugUIConstants.ID_VARIABLE_VIEW)) {
			return new Object[]{new OVAPVariable()};
		}*/
		return EMPTY;
	}

	@Override
	protected boolean supportsContextId(final String id) {
		// TODO Auto-generated method stub
		return true;
	}

}
