/**
 * 
 */
package ovap.video.filter.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import ovap.video.filter.FilterConfigurationContributer;
import ovap.video.filter.FilterConfigurationManager;
import ovap.video.filter.setup.model.Configuration;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.ModelFactory;

/**
 * @author Creative
 */
public class ConfigurationPropertySection extends AbstractPropertySection
		implements IPropertySourceProvider {
	private Button							btnApply;
	private Composite						composite;
	private FilterConfigurationContributer	configurationContributer;
	private EObject							prevSelection;

	@Override
	public void aboutToBeHidden() {
		super.aboutToBeHidden();

		// check if filter configurations are modified
		final Configuration inputConfiguration = ((FilterInstance) getSelectedEObject())
				.getConfiguration();
		if ((inputConfiguration == null)
				|| !isIdenticalMap(inputConfiguration.getEntries(),
						configurationContributer.getConfigurations())) {
			final FilterInstance filterInstance = (FilterInstance) getSelectedEObject();
			final TransactionalEditingDomain editingDomain = TransactionUtil
					.getEditingDomain(filterInstance);
			final Command command = new AbstractCommand() {

				@Override
				public void execute() {
					final Configuration configuration = ModelFactory.eINSTANCE
							.createConfiguration();
					configuration.getEntries().putAll(
							configurationContributer.getConfigurations());
					filterInstance.setConfiguration(configuration);
				}

				@Override
				protected boolean prepare() {
					return true;
				}

				@Override
				public void redo() {
				}

			};
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

		// add the apply button
		btnApply = new Button(composite, 0);
		btnApply.setText("Apply");
		btnApply.setSize(20, 13);
		final int x = composite.getSize().x - btnApply.getSize().x - 3;
		final int y = composite.getSize().y - btnApply.getSize().y - 3;
		btnApply.setLocation(x, y);
		btnApply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
			}
		});

	}

	private HashMap<String, String> getHashMap(final EMap<String, String> map) {
		final HashMap<String, String> hashMap = new HashMap<String, String>();
		for (final Entry<String, String> entry : map) {
			hashMap.put(entry.getKey(), entry.getValue());
		}
		return hashMap;
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
		configurationContributer = FilterConfigurationManager.getDefault()
				.getContributerForObject(selectedEObject);

		if (configurationContributer != null) {
			configurationContributer.createControls(composite);
			final Configuration inputConfiguration = ((FilterInstance) selectedEObject)
					.getConfiguration();
			final HashMap<String, String> configuration;
			if (inputConfiguration != null)
				configuration = getHashMap(inputConfiguration.getEntries());
			else
				// for first time loading configurations from EMF, they are null
				configuration = new HashMap<String, String>();
			configurationContributer.setConfigurations(configuration);
		}
	}
}
