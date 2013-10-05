/**
 * 
 */
package ovap.video.filter.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.jface.viewers.IFilter;

import ovap.video.filter.FilterConfigurationManager;
import ovap.video.filter.setup.model.FilterInstance;

/**
 * @author Creative
 *
 */
public class FilterInstancePropertySectionFilter implements IFilter {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	@Override
	public boolean select(Object toTest) {
		if(toTest instanceof ShapeEditPart){
			ShapeEditPart shapeEditPart = (ShapeEditPart)toTest;
			Object model = shapeEditPart.getModel();
			if(model instanceof Shape){
				Shape shape = (Shape)model;
				EObject element = shape.getElement();
				if(element instanceof FilterInstance && FilterConfigurationManager.getDefault().isContributerPresent(element))
					return true;
			}
		}
			
		return false;
	}

}
