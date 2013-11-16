/**
 * 
 */
package ovap.video.filter.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import ovap.emf.utils.EMFUtils;
import ovap.video.filter.FilterConfigurationChangeListener;
import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.FilterConfigurationManager;
import ovap.video.filter.setup.model.Configuration;
import ovap.video.filter.setup.model.FilterInstance;

/**
 * @author Creative
 */
public class ConfigurationPropertySection extends AbstractPropertySection
		implements IPropertySourceProvider, FilterConfigurationChangeListener, ResourceSetListener {
	private Composite						composite;
	private FilterConfigurationContributer	configurationContributer;
	private EObject							prevSelection;

	private void applyConfigurationUpdates(
			final FilterConfigurationContributer contributer) {
		// check if filter configurations are modified
		final Configuration inputConfiguration = ((FilterInstance) getSelectedEObject())
				.getConfiguration();
		if ((inputConfiguration == null)
				|| !isIdenticalMap(inputConfiguration.getEntries(),
						contributer.getConfigurations())) {
			final FilterInstance filterInstance = (FilterInstance) getSelectedEObject();
			final TransactionalEditingDomain editingDomain = TransactionUtil
					.getEditingDomain(filterInstance);
			final Command command = new ApplyFilterConfigurationCommand(
					contributer.getConfigurations(), filterInstance);
			editingDomain.getCommandStack().execute(command);
		}
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControls(final Composite parent,
			final TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		composite = getWidgetFactory().createFlatFormComposite(parent);
	}

	@Override
	public IPropertySource getPropertySource(final Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	private EObject getSelectedEObject() {
		final Object firstElement = ((StructuredSelection) getSelection())
				.getFirstElement();
		final Shape shape = (Shape) ((ShapeEditPart) firstElement).getModel();
		final EObject eObject = shape.getElement();
		return eObject;
	}

	private boolean isIdenticalMap(final EMap<String, String> eMap,
			final Map<String, String> map) {
		for (final String key : map.keySet()) {
			final String eValue = eMap.get(key);
			final String value = map.get(key);
			if (!eValue.equals(value))
				return false;
		}

		return true;
	}

	/**
	 * @generated
	 */
	@Override
	public void setInput(final IWorkbenchPart part, final ISelection selection) {
		super.setInput(part, selection);

		final EObject selectedEObject = getSelectedEObject();
		if (prevSelection == selectedEObject)
			return;

		prevSelection = selectedEObject;
		if(selectedEObject instanceof FilterInstance)
		configurationContributer = FilterConfigurationManager.getDefault()
				.getContributerForFilter((FilterInstance) selectedEObject);

		if (configurationContributer != null) {
			configurationContributer.createControls(composite);

			setConfigurationInContributer((FilterInstance) selectedEObject);
			configurationContributer.addChangeListener(this);
		}
		final TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(selectedEObject);
		// listen to resource changes
		editingDomain.addResourceSetListener(this);
	}

	private void setConfigurationInContributer(FilterInstance filterInstance) {
		final Configuration inputConfiguration = filterInstance
				.getConfiguration();
		final HashMap<String, String> configuration;
		//if (inputConfiguration != null)
			configuration = EMFUtils.getHashMap(inputConfiguration.getEntries());
		//else
			// for first time loading configurations from EMF, they are null
			//configuration = new HashMap<String, String>();
		if(configurationContributer.getConfigurations()==null || !isIdenticalMap(inputConfiguration.getEntries(), configurationContributer.getConfigurations()))
			configurationContributer.setConfigurations(configuration,filterInstance.getName());
	}

	@Override
	public void signalConfigurationChange(
			final FilterConfigurationContributer contributer) {
		applyConfigurationUpdates(contributer);
	}

	@Override
	public NotificationFilter getFilter() {
		return NotificationFilter.NOT_TOUCH;
	}

	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event)
			throws RollbackException {
		return null;
	}

	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		setConfigurationInContributer((FilterInstance) prevSelection);
	}

	@Override
	public boolean isAggregatePrecommitListener() {
		return false;
	}

	@Override
	public boolean isPrecommitOnly() {
		return false;
	}

	@Override
	public boolean isPostcommitOnly() {
		return true;
	}
	
	@Override
	public void aboutToBeHidden() {
		final TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(prevSelection);
		// listen to resource changes
		editingDomain.removeResourceSetListener(this);
		super.aboutToBeHidden();
	}
}
