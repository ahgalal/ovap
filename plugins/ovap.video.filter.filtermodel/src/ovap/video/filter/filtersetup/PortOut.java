/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.filtersetup;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Out</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.PortOut#getFilterType <em>Filter Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getPortOut()
 * @model
 * @generated
 */
public interface PortOut extends Port {
	/**
	 * Returns the value of the '<em><b>Filter Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.filtersetup.FilterType#getPortOut <em>Port Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Type</em>' container reference.
	 * @see #setFilterType(FilterType)
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getPortOut_FilterType()
	 * @see ovap.video.filter.filtersetup.FilterType#getPortOut
	 * @model opposite="portOut" transient="false"
	 * @generated
	 */
	FilterType getFilterType();

	/**
	 * Sets the value of the '{@link ovap.video.filter.filtersetup.PortOut#getFilterType <em>Filter Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Type</em>' container reference.
	 * @see #getFilterType()
	 * @generated
	 */
	void setFilterType(FilterType value);

} // PortOut
