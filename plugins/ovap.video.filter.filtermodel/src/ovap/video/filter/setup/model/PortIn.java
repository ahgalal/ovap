/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.setup.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port In</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.PortIn#getFilterType <em>Filter Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.setup.model.ModelPackage#getPortIn()
 * @model
 * @generated
 */
public interface PortIn extends Port {
	/**
	 * Returns the value of the '<em><b>Filter Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.FilterType#getPortIn <em>Port In</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Type</em>' container reference.
	 * @see #setFilterType(FilterType)
	 * @see ovap.video.filter.setup.model.ModelPackage#getPortIn_FilterType()
	 * @see ovap.video.filter.setup.model.FilterType#getPortIn
	 * @model opposite="portIn" transient="false"
	 * @generated
	 */
	FilterType getFilterType();

	/**
	 * Sets the value of the '{@link ovap.video.filter.setup.model.PortIn#getFilterType <em>Filter Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Type</em>' container reference.
	 * @see #getFilterType()
	 * @generated
	 */
	void setFilterType(FilterType value);

} // PortIn
