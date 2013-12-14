/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.filtersetup;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port In Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.PortInInstance#getFilterInstance <em>Filter Instance</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.PortInInstance#getFilterConnection <em>Filter Connection</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.PortInInstance#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getPortInInstance()
 * @model
 * @generated
 */
public interface PortInInstance extends PortInstance {
	/**
	 * Returns the value of the '<em><b>Filter Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.filtersetup.FilterInstance#getPortInInstance <em>Port In Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Instance</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Instance</em>' container reference.
	 * @see #setFilterInstance(FilterInstance)
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getPortInInstance_FilterInstance()
	 * @see ovap.video.filter.filtersetup.FilterInstance#getPortInInstance
	 * @model opposite="portInInstance" required="true" transient="false"
	 * @generated
	 */
	FilterInstance getFilterInstance();

	/**
	 * Sets the value of the '{@link ovap.video.filter.filtersetup.PortInInstance#getFilterInstance <em>Filter Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Instance</em>' container reference.
	 * @see #getFilterInstance()
	 * @generated
	 */
	void setFilterInstance(FilterInstance value);

	/**
	 * Returns the value of the '<em><b>Filter Connection</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.filtersetup.FilterConnection#getPortInInstance <em>Port In Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Connection</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Connection</em>' reference.
	 * @see #setFilterConnection(FilterConnection)
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getPortInInstance_FilterConnection()
	 * @see ovap.video.filter.filtersetup.FilterConnection#getPortInInstance
	 * @model opposite="portInInstance" required="true"
	 * @generated
	 */
	FilterConnection getFilterConnection();

	/**
	 * Sets the value of the '{@link ovap.video.filter.filtersetup.PortInInstance#getFilterConnection <em>Filter Connection</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Connection</em>' reference.
	 * @see #getFilterConnection()
	 * @generated
	 */
	void setFilterConnection(FilterConnection value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(PortIn)
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getPortInInstance_Type()
	 * @model required="true"
	 * @generated
	 */
	PortIn getType();

	/**
	 * Sets the value of the '{@link ovap.video.filter.filtersetup.PortInInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(PortIn value);

} // PortInInstance
