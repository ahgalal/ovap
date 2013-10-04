/**
 * 
 */
package ovap.video.filter;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Creative
 */
public class ConfigurationPropertySection extends AdvancedPropertySection
		implements IPropertySourceProvider {

	@Override
	public void createControls(final Composite parent,
			final TabbedPropertySheetPage aTabbedPropertySheetPage) {
		final Composite composite = new Composite(parent, 0);
		page = new PropertySheetPage();
	}

	/**
	 * @generated
	 */
	protected AdapterFactory getAdapterFactory(final Object object) {
		if (getEditingDomain() instanceof AdapterFactoryEditingDomain) {
			return ((AdapterFactoryEditingDomain) getEditingDomain())
					.getAdapterFactory();
		}
		final TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(object);
		if (editingDomain != null) {
			return ((AdapterFactoryEditingDomain) editingDomain)
					.getAdapterFactory();
		}
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	public IPropertySource getPropertySource(final Object object) {
		if (object instanceof IPropertySource) {
			return (IPropertySource) object;
		}
		final AdapterFactory af = getAdapterFactory(object);
		if (af != null) {
			final IItemPropertySource ips = (IItemPropertySource) af.adapt(
					object, IItemPropertySource.class);
			if (ips != null) {
				return new PropertySource(object, ips);
			}
		}
		if (object instanceof IAdaptable) {
			return (IPropertySource) ((IAdaptable) object)
					.getAdapter(IPropertySource.class);
		}
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	protected IPropertySourceProvider getPropertySourceProvider() {
		return this;
	}

	/**
	 * @generated
	 */
	@Override
	public void setInput(final IWorkbenchPart part, final ISelection selection) {
		if (selection.isEmpty()
				|| (false == (selection instanceof StructuredSelection))) {
			super.setInput(part, selection);
			return;
		}
		final StructuredSelection structuredSelection = ((StructuredSelection) selection);
		final ArrayList transformedSelection = new ArrayList(
				structuredSelection.size());
		for (final Iterator it = structuredSelection.iterator(); it.hasNext();) {
			final Object r = transformSelection(it.next());
			if (r != null) {
				transformedSelection.add(r);
			}
		}
		super.setInput(part, new StructuredSelection(transformedSelection));
	}

	/**
	 * Modify/unwrap selection.
	 * 
	 * @generated
	 */
	protected Object transformSelection(final Object selected) {
		return selected;
	}

}
