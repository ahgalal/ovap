/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.setup.model.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import ovap.video.filter.setup.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("model"); 
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.FILTER_INSTANCE: return createFilterInstance();
			case ModelPackage.FILTER_TYPE: return createFilterType();
			case ModelPackage.FILTERS_SETUP: return createFiltersSetup();
			case ModelPackage.FILTER_CONNECTION: return createFilterConnection();
			case ModelPackage.PORT_IN: return createPortIn();
			case ModelPackage.PORT_OUT: return createPortOut();
			case ModelPackage.PORT_IN_INSTANCE: return createPortInInstance();
			case ModelPackage.PORT_OUT_INSTANCE: return createPortOutInstance();
			case ModelPackage.IDENTIFIABLE: return createIdentifiable();
			case ModelPackage.CONFIGURATION_MAP_ENTRY: return (EObject)createConfigurationMapEntry();
			case ModelPackage.CONFIGURATION: return createConfiguration();
			case ModelPackage.FILTER_MODEL: return createFilterModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterInstance createFilterInstance() {
		FilterInstanceImpl filterInstance = new FilterInstanceImpl();
		return filterInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterType createFilterType() {
		FilterTypeImpl filterType = new FilterTypeImpl();
		return filterType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FiltersSetup createFiltersSetup() {
		FiltersSetupImpl filtersSetup = new FiltersSetupImpl();
		return filtersSetup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterConnection createFilterConnection() {
		FilterConnectionImpl filterConnection = new FilterConnectionImpl();
		return filterConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortIn createPortIn() {
		PortInImpl portIn = new PortInImpl();
		return portIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortOut createPortOut() {
		PortOutImpl portOut = new PortOutImpl();
		return portOut;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortInInstance createPortInInstance() {
		PortInInstanceImpl portInInstance = new PortInInstanceImpl();
		return portInInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortOutInstance createPortOutInstance() {
		PortOutInstanceImpl portOutInstance = new PortOutInstanceImpl();
		return portOutInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Identifiable createIdentifiable() {
		IdentifiableImpl identifiable = new IdentifiableImpl();
		return identifiable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createConfigurationMapEntry() {
		ConfigurationMapEntryImpl configurationMapEntry = new ConfigurationMapEntryImpl();
		return configurationMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Configuration createConfiguration() {
		ConfigurationImpl configuration = new ConfigurationImpl();
		return configuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterModel createFilterModel() {
		FilterModelImpl filterModel = new FilterModelImpl();
		return filterModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} //ModelFactoryImpl
