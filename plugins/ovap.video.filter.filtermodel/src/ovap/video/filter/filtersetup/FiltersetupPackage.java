/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.filtersetup;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see ovap.video.filter.filtersetup.FiltersetupFactory
 * @model kind="package"
 * @generated
 */
public interface FiltersetupPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "filtersetup";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///ovap.video.filter.filtersetup/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "filtersetup";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FiltersetupPackage eINSTANCE = ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl.init();

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.IdentifiableImpl <em>Identifiable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.IdentifiableImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getIdentifiable()
	 * @generated
	 */
	int IDENTIFIABLE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Identifiable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.FilterInstanceImpl <em>Filter Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.FilterInstanceImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFilterInstance()
	 * @generated
	 */
	int FILTER_INSTANCE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_INSTANCE__NAME = IDENTIFIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_INSTANCE__TYPE = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Port In Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_INSTANCE__PORT_IN_INSTANCE = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Port Out Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_INSTANCE__PORT_OUT_INSTANCE = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_INSTANCE__CONFIGURATION = IDENTIFIABLE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Filter Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_INSTANCE_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.FilterTypeImpl <em>Filter Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.FilterTypeImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFilterType()
	 * @generated
	 */
	int FILTER_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_TYPE__NAME = IDENTIFIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Port Out</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_TYPE__PORT_OUT = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Port In</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_TYPE__PORT_IN = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_TYPE__MODEL = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Filter Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_TYPE_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.FiltersSetupImpl <em>Filters Setup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.FiltersSetupImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFiltersSetup()
	 * @generated
	 */
	int FILTERS_SETUP = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERS_SETUP__NAME = IDENTIFIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Filter Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERS_SETUP__FILTER_INSTANCES = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERS_SETUP__CONNECTIONS = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERS_SETUP__MODEL = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Filters Setup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERS_SETUP_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.FilterConnectionImpl <em>Filter Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.FilterConnectionImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFilterConnection()
	 * @generated
	 */
	int FILTER_CONNECTION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_CONNECTION__NAME = IDENTIFIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Port In Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_CONNECTION__PORT_IN_INSTANCE = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Port Out Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_CONNECTION__PORT_OUT_INSTANCE = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Filters Setup</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_CONNECTION__FILTERS_SETUP = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Filter Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_CONNECTION_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.PortImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPort()
	 * @generated
	 */
	int PORT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__NAME = IDENTIFIABLE__NAME;

	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.PortInImpl <em>Port In</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.PortInImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortIn()
	 * @generated
	 */
	int PORT_IN = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_IN__NAME = PORT__NAME;

	/**
	 * The feature id for the '<em><b>Filter Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_IN__FILTER_TYPE = PORT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port In</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_IN_FEATURE_COUNT = PORT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.PortOutImpl <em>Port Out</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.PortOutImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortOut()
	 * @generated
	 */
	int PORT_OUT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OUT__NAME = PORT__NAME;

	/**
	 * The feature id for the '<em><b>Filter Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OUT__FILTER_TYPE = PORT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port Out</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OUT_FEATURE_COUNT = PORT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.PortInstanceImpl <em>Port Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.PortInstanceImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortInstance()
	 * @generated
	 */
	int PORT_INSTANCE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_INSTANCE__NAME = IDENTIFIABLE__NAME;

	/**
	 * The number of structural features of the '<em>Port Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_INSTANCE_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.PortInInstanceImpl <em>Port In Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.PortInInstanceImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortInInstance()
	 * @generated
	 */
	int PORT_IN_INSTANCE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_IN_INSTANCE__NAME = PORT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Filter Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_IN_INSTANCE__FILTER_INSTANCE = PORT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Filter Connection</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_IN_INSTANCE__FILTER_CONNECTION = PORT_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_IN_INSTANCE__TYPE = PORT_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Port In Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_IN_INSTANCE_FEATURE_COUNT = PORT_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.PortOutInstanceImpl <em>Port Out Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.PortOutInstanceImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortOutInstance()
	 * @generated
	 */
	int PORT_OUT_INSTANCE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OUT_INSTANCE__NAME = PORT_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Filter Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OUT_INSTANCE__FILTER_INSTANCE = PORT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Filter Connection</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OUT_INSTANCE__FILTER_CONNECTION = PORT_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OUT_INSTANCE__TYPE = PORT_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Port Out Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OUT_INSTANCE_FEATURE_COUNT = PORT_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.ConfigurationMapEntryImpl <em>Configuration Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.ConfigurationMapEntryImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getConfigurationMapEntry()
	 * @generated
	 */
	int CONFIGURATION_MAP_ENTRY = 11;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Configuration Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.ConfigurationImpl <em>Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.ConfigurationImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getConfiguration()
	 * @generated
	 */
	int CONFIGURATION = 12;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__ENTRIES = 0;

	/**
	 * The number of structural features of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link ovap.video.filter.filtersetup.impl.FilterModelImpl <em>Filter Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ovap.video.filter.filtersetup.impl.FilterModelImpl
	 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFilterModel()
	 * @generated
	 */
	int FILTER_MODEL = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MODEL__NAME = IDENTIFIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Filter Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MODEL__FILTER_TYPES = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Setup</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MODEL__SETUP = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Filter Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MODEL_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.FilterInstance <em>Filter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter Instance</em>'.
	 * @see ovap.video.filter.filtersetup.FilterInstance
	 * @generated
	 */
	EClass getFilterInstance();

	/**
	 * Returns the meta object for the reference '{@link ovap.video.filter.filtersetup.FilterInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see ovap.video.filter.filtersetup.FilterInstance#getType()
	 * @see #getFilterInstance()
	 * @generated
	 */
	EReference getFilterInstance_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link ovap.video.filter.filtersetup.FilterInstance#getPortInInstance <em>Port In Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Port In Instance</em>'.
	 * @see ovap.video.filter.filtersetup.FilterInstance#getPortInInstance()
	 * @see #getFilterInstance()
	 * @generated
	 */
	EReference getFilterInstance_PortInInstance();

	/**
	 * Returns the meta object for the containment reference list '{@link ovap.video.filter.filtersetup.FilterInstance#getPortOutInstance <em>Port Out Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Port Out Instance</em>'.
	 * @see ovap.video.filter.filtersetup.FilterInstance#getPortOutInstance()
	 * @see #getFilterInstance()
	 * @generated
	 */
	EReference getFilterInstance_PortOutInstance();

	/**
	 * Returns the meta object for the containment reference '{@link ovap.video.filter.filtersetup.FilterInstance#getConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Configuration</em>'.
	 * @see ovap.video.filter.filtersetup.FilterInstance#getConfiguration()
	 * @see #getFilterInstance()
	 * @generated
	 */
	EReference getFilterInstance_Configuration();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.FilterType <em>Filter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter Type</em>'.
	 * @see ovap.video.filter.filtersetup.FilterType
	 * @generated
	 */
	EClass getFilterType();

	/**
	 * Returns the meta object for the containment reference list '{@link ovap.video.filter.filtersetup.FilterType#getPortOut <em>Port Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Port Out</em>'.
	 * @see ovap.video.filter.filtersetup.FilterType#getPortOut()
	 * @see #getFilterType()
	 * @generated
	 */
	EReference getFilterType_PortOut();

	/**
	 * Returns the meta object for the containment reference list '{@link ovap.video.filter.filtersetup.FilterType#getPortIn <em>Port In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Port In</em>'.
	 * @see ovap.video.filter.filtersetup.FilterType#getPortIn()
	 * @see #getFilterType()
	 * @generated
	 */
	EReference getFilterType_PortIn();

	/**
	 * Returns the meta object for the container reference '{@link ovap.video.filter.filtersetup.FilterType#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see ovap.video.filter.filtersetup.FilterType#getModel()
	 * @see #getFilterType()
	 * @generated
	 */
	EReference getFilterType_Model();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.FiltersSetup <em>Filters Setup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filters Setup</em>'.
	 * @see ovap.video.filter.filtersetup.FiltersSetup
	 * @generated
	 */
	EClass getFiltersSetup();

	/**
	 * Returns the meta object for the containment reference list '{@link ovap.video.filter.filtersetup.FiltersSetup#getFilterInstances <em>Filter Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filter Instances</em>'.
	 * @see ovap.video.filter.filtersetup.FiltersSetup#getFilterInstances()
	 * @see #getFiltersSetup()
	 * @generated
	 */
	EReference getFiltersSetup_FilterInstances();

	/**
	 * Returns the meta object for the containment reference list '{@link ovap.video.filter.filtersetup.FiltersSetup#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see ovap.video.filter.filtersetup.FiltersSetup#getConnections()
	 * @see #getFiltersSetup()
	 * @generated
	 */
	EReference getFiltersSetup_Connections();

	/**
	 * Returns the meta object for the container reference '{@link ovap.video.filter.filtersetup.FiltersSetup#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see ovap.video.filter.filtersetup.FiltersSetup#getModel()
	 * @see #getFiltersSetup()
	 * @generated
	 */
	EReference getFiltersSetup_Model();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.FilterConnection <em>Filter Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter Connection</em>'.
	 * @see ovap.video.filter.filtersetup.FilterConnection
	 * @generated
	 */
	EClass getFilterConnection();

	/**
	 * Returns the meta object for the reference '{@link ovap.video.filter.filtersetup.FilterConnection#getPortInInstance <em>Port In Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port In Instance</em>'.
	 * @see ovap.video.filter.filtersetup.FilterConnection#getPortInInstance()
	 * @see #getFilterConnection()
	 * @generated
	 */
	EReference getFilterConnection_PortInInstance();

	/**
	 * Returns the meta object for the reference '{@link ovap.video.filter.filtersetup.FilterConnection#getPortOutInstance <em>Port Out Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port Out Instance</em>'.
	 * @see ovap.video.filter.filtersetup.FilterConnection#getPortOutInstance()
	 * @see #getFilterConnection()
	 * @generated
	 */
	EReference getFilterConnection_PortOutInstance();

	/**
	 * Returns the meta object for the container reference '{@link ovap.video.filter.filtersetup.FilterConnection#getFiltersSetup <em>Filters Setup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Filters Setup</em>'.
	 * @see ovap.video.filter.filtersetup.FilterConnection#getFiltersSetup()
	 * @see #getFilterConnection()
	 * @generated
	 */
	EReference getFilterConnection_FiltersSetup();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.PortIn <em>Port In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port In</em>'.
	 * @see ovap.video.filter.filtersetup.PortIn
	 * @generated
	 */
	EClass getPortIn();

	/**
	 * Returns the meta object for the container reference '{@link ovap.video.filter.filtersetup.PortIn#getFilterType <em>Filter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Filter Type</em>'.
	 * @see ovap.video.filter.filtersetup.PortIn#getFilterType()
	 * @see #getPortIn()
	 * @generated
	 */
	EReference getPortIn_FilterType();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.PortOut <em>Port Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Out</em>'.
	 * @see ovap.video.filter.filtersetup.PortOut
	 * @generated
	 */
	EClass getPortOut();

	/**
	 * Returns the meta object for the container reference '{@link ovap.video.filter.filtersetup.PortOut#getFilterType <em>Filter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Filter Type</em>'.
	 * @see ovap.video.filter.filtersetup.PortOut#getFilterType()
	 * @see #getPortOut()
	 * @generated
	 */
	EReference getPortOut_FilterType();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see ovap.video.filter.filtersetup.Port
	 * @generated
	 */
	EClass getPort();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.PortInstance <em>Port Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Instance</em>'.
	 * @see ovap.video.filter.filtersetup.PortInstance
	 * @generated
	 */
	EClass getPortInstance();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.PortInInstance <em>Port In Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port In Instance</em>'.
	 * @see ovap.video.filter.filtersetup.PortInInstance
	 * @generated
	 */
	EClass getPortInInstance();

	/**
	 * Returns the meta object for the container reference '{@link ovap.video.filter.filtersetup.PortInInstance#getFilterInstance <em>Filter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Filter Instance</em>'.
	 * @see ovap.video.filter.filtersetup.PortInInstance#getFilterInstance()
	 * @see #getPortInInstance()
	 * @generated
	 */
	EReference getPortInInstance_FilterInstance();

	/**
	 * Returns the meta object for the reference '{@link ovap.video.filter.filtersetup.PortInInstance#getFilterConnection <em>Filter Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Filter Connection</em>'.
	 * @see ovap.video.filter.filtersetup.PortInInstance#getFilterConnection()
	 * @see #getPortInInstance()
	 * @generated
	 */
	EReference getPortInInstance_FilterConnection();

	/**
	 * Returns the meta object for the reference '{@link ovap.video.filter.filtersetup.PortInInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see ovap.video.filter.filtersetup.PortInInstance#getType()
	 * @see #getPortInInstance()
	 * @generated
	 */
	EReference getPortInInstance_Type();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.PortOutInstance <em>Port Out Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Out Instance</em>'.
	 * @see ovap.video.filter.filtersetup.PortOutInstance
	 * @generated
	 */
	EClass getPortOutInstance();

	/**
	 * Returns the meta object for the container reference '{@link ovap.video.filter.filtersetup.PortOutInstance#getFilterInstance <em>Filter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Filter Instance</em>'.
	 * @see ovap.video.filter.filtersetup.PortOutInstance#getFilterInstance()
	 * @see #getPortOutInstance()
	 * @generated
	 */
	EReference getPortOutInstance_FilterInstance();

	/**
	 * Returns the meta object for the reference list '{@link ovap.video.filter.filtersetup.PortOutInstance#getFilterConnection <em>Filter Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Filter Connection</em>'.
	 * @see ovap.video.filter.filtersetup.PortOutInstance#getFilterConnection()
	 * @see #getPortOutInstance()
	 * @generated
	 */
	EReference getPortOutInstance_FilterConnection();

	/**
	 * Returns the meta object for the reference '{@link ovap.video.filter.filtersetup.PortOutInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see ovap.video.filter.filtersetup.PortOutInstance#getType()
	 * @see #getPortOutInstance()
	 * @generated
	 */
	EReference getPortOutInstance_Type();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.Identifiable <em>Identifiable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable</em>'.
	 * @see ovap.video.filter.filtersetup.Identifiable
	 * @generated
	 */
	EClass getIdentifiable();

	/**
	 * Returns the meta object for the attribute '{@link ovap.video.filter.filtersetup.Identifiable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see ovap.video.filter.filtersetup.Identifiable#getName()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_Name();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Configuration Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getConfigurationMapEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getConfigurationMapEntry()
	 * @generated
	 */
	EAttribute getConfigurationMapEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getConfigurationMapEntry()
	 * @generated
	 */
	EAttribute getConfigurationMapEntry_Value();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see ovap.video.filter.filtersetup.Configuration
	 * @generated
	 */
	EClass getConfiguration();

	/**
	 * Returns the meta object for the map '{@link ovap.video.filter.filtersetup.Configuration#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Entries</em>'.
	 * @see ovap.video.filter.filtersetup.Configuration#getEntries()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Entries();

	/**
	 * Returns the meta object for class '{@link ovap.video.filter.filtersetup.FilterModel <em>Filter Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter Model</em>'.
	 * @see ovap.video.filter.filtersetup.FilterModel
	 * @generated
	 */
	EClass getFilterModel();

	/**
	 * Returns the meta object for the containment reference list '{@link ovap.video.filter.filtersetup.FilterModel#getFilterTypes <em>Filter Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filter Types</em>'.
	 * @see ovap.video.filter.filtersetup.FilterModel#getFilterTypes()
	 * @see #getFilterModel()
	 * @generated
	 */
	EReference getFilterModel_FilterTypes();

	/**
	 * Returns the meta object for the containment reference '{@link ovap.video.filter.filtersetup.FilterModel#getSetup <em>Setup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Setup</em>'.
	 * @see ovap.video.filter.filtersetup.FilterModel#getSetup()
	 * @see #getFilterModel()
	 * @generated
	 */
	EReference getFilterModel_Setup();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FiltersetupFactory getFiltersetupFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.FilterInstanceImpl <em>Filter Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.FilterInstanceImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFilterInstance()
		 * @generated
		 */
		EClass FILTER_INSTANCE = eINSTANCE.getFilterInstance();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_INSTANCE__TYPE = eINSTANCE.getFilterInstance_Type();

		/**
		 * The meta object literal for the '<em><b>Port In Instance</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_INSTANCE__PORT_IN_INSTANCE = eINSTANCE.getFilterInstance_PortInInstance();

		/**
		 * The meta object literal for the '<em><b>Port Out Instance</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_INSTANCE__PORT_OUT_INSTANCE = eINSTANCE.getFilterInstance_PortOutInstance();

		/**
		 * The meta object literal for the '<em><b>Configuration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_INSTANCE__CONFIGURATION = eINSTANCE.getFilterInstance_Configuration();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.FilterTypeImpl <em>Filter Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.FilterTypeImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFilterType()
		 * @generated
		 */
		EClass FILTER_TYPE = eINSTANCE.getFilterType();

		/**
		 * The meta object literal for the '<em><b>Port Out</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_TYPE__PORT_OUT = eINSTANCE.getFilterType_PortOut();

		/**
		 * The meta object literal for the '<em><b>Port In</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_TYPE__PORT_IN = eINSTANCE.getFilterType_PortIn();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_TYPE__MODEL = eINSTANCE.getFilterType_Model();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.FiltersSetupImpl <em>Filters Setup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.FiltersSetupImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFiltersSetup()
		 * @generated
		 */
		EClass FILTERS_SETUP = eINSTANCE.getFiltersSetup();

		/**
		 * The meta object literal for the '<em><b>Filter Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTERS_SETUP__FILTER_INSTANCES = eINSTANCE.getFiltersSetup_FilterInstances();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTERS_SETUP__CONNECTIONS = eINSTANCE.getFiltersSetup_Connections();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTERS_SETUP__MODEL = eINSTANCE.getFiltersSetup_Model();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.FilterConnectionImpl <em>Filter Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.FilterConnectionImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFilterConnection()
		 * @generated
		 */
		EClass FILTER_CONNECTION = eINSTANCE.getFilterConnection();

		/**
		 * The meta object literal for the '<em><b>Port In Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_CONNECTION__PORT_IN_INSTANCE = eINSTANCE.getFilterConnection_PortInInstance();

		/**
		 * The meta object literal for the '<em><b>Port Out Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_CONNECTION__PORT_OUT_INSTANCE = eINSTANCE.getFilterConnection_PortOutInstance();

		/**
		 * The meta object literal for the '<em><b>Filters Setup</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_CONNECTION__FILTERS_SETUP = eINSTANCE.getFilterConnection_FiltersSetup();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.PortInImpl <em>Port In</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.PortInImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortIn()
		 * @generated
		 */
		EClass PORT_IN = eINSTANCE.getPortIn();

		/**
		 * The meta object literal for the '<em><b>Filter Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_IN__FILTER_TYPE = eINSTANCE.getPortIn_FilterType();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.PortOutImpl <em>Port Out</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.PortOutImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortOut()
		 * @generated
		 */
		EClass PORT_OUT = eINSTANCE.getPortOut();

		/**
		 * The meta object literal for the '<em><b>Filter Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_OUT__FILTER_TYPE = eINSTANCE.getPortOut_FilterType();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.PortImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPort()
		 * @generated
		 */
		EClass PORT = eINSTANCE.getPort();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.PortInstanceImpl <em>Port Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.PortInstanceImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortInstance()
		 * @generated
		 */
		EClass PORT_INSTANCE = eINSTANCE.getPortInstance();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.PortInInstanceImpl <em>Port In Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.PortInInstanceImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortInInstance()
		 * @generated
		 */
		EClass PORT_IN_INSTANCE = eINSTANCE.getPortInInstance();

		/**
		 * The meta object literal for the '<em><b>Filter Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_IN_INSTANCE__FILTER_INSTANCE = eINSTANCE.getPortInInstance_FilterInstance();

		/**
		 * The meta object literal for the '<em><b>Filter Connection</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_IN_INSTANCE__FILTER_CONNECTION = eINSTANCE.getPortInInstance_FilterConnection();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_IN_INSTANCE__TYPE = eINSTANCE.getPortInInstance_Type();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.PortOutInstanceImpl <em>Port Out Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.PortOutInstanceImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getPortOutInstance()
		 * @generated
		 */
		EClass PORT_OUT_INSTANCE = eINSTANCE.getPortOutInstance();

		/**
		 * The meta object literal for the '<em><b>Filter Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_OUT_INSTANCE__FILTER_INSTANCE = eINSTANCE.getPortOutInstance_FilterInstance();

		/**
		 * The meta object literal for the '<em><b>Filter Connection</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_OUT_INSTANCE__FILTER_CONNECTION = eINSTANCE.getPortOutInstance_FilterConnection();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_OUT_INSTANCE__TYPE = eINSTANCE.getPortOutInstance_Type();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.IdentifiableImpl <em>Identifiable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.IdentifiableImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getIdentifiable()
		 * @generated
		 */
		EClass IDENTIFIABLE = eINSTANCE.getIdentifiable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__NAME = eINSTANCE.getIdentifiable_Name();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.ConfigurationMapEntryImpl <em>Configuration Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.ConfigurationMapEntryImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getConfigurationMapEntry()
		 * @generated
		 */
		EClass CONFIGURATION_MAP_ENTRY = eINSTANCE.getConfigurationMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION_MAP_ENTRY__KEY = eINSTANCE.getConfigurationMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION_MAP_ENTRY__VALUE = eINSTANCE.getConfigurationMapEntry_Value();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.ConfigurationImpl <em>Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.ConfigurationImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getConfiguration()
		 * @generated
		 */
		EClass CONFIGURATION = eINSTANCE.getConfiguration();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__ENTRIES = eINSTANCE.getConfiguration_Entries();

		/**
		 * The meta object literal for the '{@link ovap.video.filter.filtersetup.impl.FilterModelImpl <em>Filter Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ovap.video.filter.filtersetup.impl.FilterModelImpl
		 * @see ovap.video.filter.filtersetup.impl.FiltersetupPackageImpl#getFilterModel()
		 * @generated
		 */
		EClass FILTER_MODEL = eINSTANCE.getFilterModel();

		/**
		 * The meta object literal for the '<em><b>Filter Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_MODEL__FILTER_TYPES = eINSTANCE.getFilterModel_FilterTypes();

		/**
		 * The meta object literal for the '<em><b>Setup</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_MODEL__SETUP = eINSTANCE.getFilterModel_Setup();

	}

} //FiltersetupPackage
