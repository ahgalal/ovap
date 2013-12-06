/**
 * 
 */
package ovap.video;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.internal.ui.model.elements.ElementContentProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IViewerUpdate;
import org.eclipse.debug.ui.IDebugUIConstants;

import ovap.video.launch.AnalysisTarget;

/**
 * @author Creative
 *
 */
@SuppressWarnings("restriction")
public class AnalysisTargetContentProvider extends ElementContentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.internal.ui.model.elements.ElementContentProvider#getChildren(java.lang.Object, int, int, org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext, org.eclipse.debug.internal.ui.viewers.model.provisional.IViewerUpdate)
	 */
	@Override
	protected Object[] getChildren(Object parent, int index, int length,
			IPresentationContext context, IViewerUpdate monitor)
			throws CoreException {
		if(context.getId().equals(IDebugUIConstants.ID_VARIABLE_VIEW)){
			return ((AnalysisTarget)parent).getVariables();
		}
		return EMPTY;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.internal.ui.model.elements.ElementContentProvider#getChildCount(java.lang.Object, org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext, org.eclipse.debug.internal.ui.viewers.model.provisional.IViewerUpdate)
	 */
	@Override
	protected int getChildCount(Object element, IPresentationContext context,
			IViewerUpdate monitor) throws CoreException {
		return getChildren(element, 0, 0, context, monitor).length;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.internal.ui.model.elements.ElementContentProvider#supportsContextId(java.lang.String)
	 */
	@Override
	protected boolean supportsContextId(String id) {
		if(IDebugUIConstants.ID_VARIABLE_VIEW.equals(id))
			return true;
		return false;
	}

}
