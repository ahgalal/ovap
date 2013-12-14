/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.filtersetup;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ovap.video.filter.filtersetup.FiltersetupPackage
 * @generated
 */
public interface FiltersetupFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FiltersetupFactory eINSTANCE = ovap.video.filter.filtersetup.impl.FiltersetupFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Filter Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filter Instance</em>'.
	 * @generated
	 */
	FilterInstance createFilterInstance();

	/**
	 * Returns a new object of class '<em>Filter Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filter Type</em>'.
	 * @generated
	 */
	FilterType createFilterType();

	/**
	 * Returns a new object of class '<em>Filters Setup</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filters Setup</em>'.
	 * @generated
	 */
	FiltersSetup createFiltersSetup();

	/**
	 * Returns a new object of class '<em>Filter Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filter Connection</em>'.
	 * @generated
	 */
	FilterConnection createFilterConnection();

	/**
	 * Returns a new object of class '<em>Port In</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port In</em>'.
	 * @generated
	 */
	PortIn createPortIn();

	/**
	 * Returns a new object of class '<em>Port Out</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Out</em>'.
	 * @generated
	 */
	PortOut createPortOut();

	/**
	 * Returns a new object of class '<em>Port In Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port In Instance</em>'.
	 * @generated
	 */
	PortInInstance createPortInInstance();

	/**
	 * Returns a new object of class '<em>Port Out Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Out Instance</em>'.
	 * @generated
	 */
	PortOutInstance createPortOutInstance();

	/**
	 * Returns a new object of class '<em>Identifiable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Identifiable</em>'.
	 * @generated
	 */
	Identifiable createIdentifiable();

	/**
	 * Returns a new object of class '<em>Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configuration</em>'.
	 * @generated
	 */
	Configuration createConfiguration();

	/**
	 * Returns a new object of class '<em>Filter Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filter Model</em>'.
	 * @generated
	 */
	FilterModel createFilterModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FiltersetupPackage getFiltersetupPackage();

} //FiltersetupFactory
