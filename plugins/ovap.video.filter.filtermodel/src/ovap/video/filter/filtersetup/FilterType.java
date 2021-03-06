/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.filtersetup;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.FilterType#getPortOut <em>Port Out</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.FilterType#getPortIn <em>Port In</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.FilterType#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterType()
 * @model
 * @generated
 */
public interface FilterType extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Port Out</b></em>' containment reference list.
	 * The list contents are of type {@link ovap.video.filter.filtersetup.PortOut}.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.filtersetup.PortOut#getFilterType <em>Filter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Out</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Out</em>' containment reference list.
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterType_PortOut()
	 * @see ovap.video.filter.filtersetup.PortOut#getFilterType
	 * @model opposite="filterType" containment="true" keys="name"
	 * @generated
	 */
	EList<PortOut> getPortOut();

	/**
	 * Returns the value of the '<em><b>Port In</b></em>' containment reference list.
	 * The list contents are of type {@link ovap.video.filter.filtersetup.PortIn}.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.filtersetup.PortIn#getFilterType <em>Filter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port In</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port In</em>' containment reference list.
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterType_PortIn()
	 * @see ovap.video.filter.filtersetup.PortIn#getFilterType
	 * @model opposite="filterType" containment="true" keys="name"
	 * @generated
	 */
	EList<PortIn> getPortIn();

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.filtersetup.FilterModel#getFilterTypes <em>Filter Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(FilterModel)
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterType_Model()
	 * @see ovap.video.filter.filtersetup.FilterModel#getFilterTypes
	 * @model opposite="filterTypes" transient="false"
	 * @generated
	 */
	FilterModel getModel();

	/**
	 * Sets the value of the '{@link ovap.video.filter.filtersetup.FilterType#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(FilterModel value);

} // FilterType
