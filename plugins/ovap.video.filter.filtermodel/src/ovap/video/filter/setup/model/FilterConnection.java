/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.setup.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.FilterConnection#getPortInInstance <em>Port In Instance</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.FilterConnection#getPortOutInstance <em>Port Out Instance</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.FilterConnection#getFiltersSetup <em>Filters Setup</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.setup.model.ModelPackage#getFilterConnection()
 * @model
 * @generated
 */
public interface FilterConnection extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Port In Instance</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.PortInInstance#getFilterConnection <em>Filter Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port In Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port In Instance</em>' reference.
	 * @see #setPortInInstance(PortInInstance)
	 * @see ovap.video.filter.setup.model.ModelPackage#getFilterConnection_PortInInstance()
	 * @see ovap.video.filter.setup.model.PortInInstance#getFilterConnection
	 * @model opposite="filterConnection" required="true"
	 * @generated
	 */
	PortInInstance getPortInInstance();

	/**
	 * Sets the value of the '{@link ovap.video.filter.setup.model.FilterConnection#getPortInInstance <em>Port In Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port In Instance</em>' reference.
	 * @see #getPortInInstance()
	 * @generated
	 */
	void setPortInInstance(PortInInstance value);

	/**
	 * Returns the value of the '<em><b>Port Out Instance</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.PortOutInstance#getFilterConnection <em>Filter Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Out Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Out Instance</em>' reference.
	 * @see #setPortOutInstance(PortOutInstance)
	 * @see ovap.video.filter.setup.model.ModelPackage#getFilterConnection_PortOutInstance()
	 * @see ovap.video.filter.setup.model.PortOutInstance#getFilterConnection
	 * @model opposite="filterConnection" required="true"
	 * @generated
	 */
	PortOutInstance getPortOutInstance();

	/**
	 * Sets the value of the '{@link ovap.video.filter.setup.model.FilterConnection#getPortOutInstance <em>Port Out Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Out Instance</em>' reference.
	 * @see #getPortOutInstance()
	 * @generated
	 */
	void setPortOutInstance(PortOutInstance value);

	/**
	 * Returns the value of the '<em><b>Filters Setup</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.FiltersSetup#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filters Setup</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filters Setup</em>' container reference.
	 * @see #setFiltersSetup(FiltersSetup)
	 * @see ovap.video.filter.setup.model.ModelPackage#getFilterConnection_FiltersSetup()
	 * @see ovap.video.filter.setup.model.FiltersSetup#getConnections
	 * @model opposite="connections" transient="false"
	 * @generated
	 */
	FiltersSetup getFiltersSetup();

	/**
	 * Sets the value of the '{@link ovap.video.filter.setup.model.FilterConnection#getFiltersSetup <em>Filters Setup</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filters Setup</em>' container reference.
	 * @see #getFiltersSetup()
	 * @generated
	 */
	void setFiltersSetup(FiltersSetup value);

} // FilterConnection
