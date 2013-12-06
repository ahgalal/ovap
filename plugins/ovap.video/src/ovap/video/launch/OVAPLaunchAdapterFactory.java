/**
 * 
 */
package ovap.video.launch;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.core.IExpressionManager;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IColumnPresentationFactory;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IElementContentProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IViewerInputProvider;

import ovap.video.launch.ui.provider.OVAPLaunchContentProvider;
import ovap.video.launch.ui.provider.OVAPVariableColumnFactoryProvider;

/**
 * @author Creative
 *
 */
@SuppressWarnings({ "restriction", "unused" })
public class OVAPLaunchAdapterFactory implements IAdapterFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		
		if(adaptableObject.getClass().equals(OVAPLaunch.class)){
			if(adapterType.equals(IElementContentProvider.class)){
				return new OVAPLaunchContentProvider();
			}else if(adapterType.equals(IColumnPresentationFactory.class)){
				return new OVAPVariableColumnFactoryProvider();
			}
			
			return null;
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	@Override
	public Class[] getAdapterList() {
		return new Class[0]; // defined in plugin.xml in the adapters extension
		//{IElementContentProvider.class/*,IColumnPresentationFactory.class*/};
	}

}
