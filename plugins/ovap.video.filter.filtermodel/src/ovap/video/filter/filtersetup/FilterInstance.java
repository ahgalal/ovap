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
 * A representation of the model object '<em><b>Filter Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.FilterInstance#getType <em>Type</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.FilterInstance#getPortInInstance <em>Port In Instance</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.FilterInstance#getPortOutInstance <em>Port Out Instance</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.FilterInstance#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterInstance()
 * @model
 * @generated
 */
public interface FilterInstance extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(FilterType)
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterInstance_Type()
	 * @model keys="name" required="true"
	 * @generated
	 */
	FilterType getType();

	/**
	 * Sets the value of the '{@link ovap.video.filter.filtersetup.FilterInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(FilterType value);

	/**
	 * Returns the value of the '<em><b>Port In Instance</b></em>' containment reference list.
	 * The list contents are of type {@link ovap.video.filter.filtersetup.PortInInstance}.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.filtersetup.PortInInstance#getFilterInstance <em>Filter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port In Instance</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port In Instance</em>' containment reference list.
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterInstance_PortInInstance()
	 * @see ovap.video.filter.filtersetup.PortInInstance#getFilterInstance
	 * @model opposite="filterInstance" containment="true"
	 * @generated
	 */
	EList<PortInInstance> getPortInInstance();

	/**
	 * Returns the value of the '<em><b>Port Out Instance</b></em>' containment reference list.
	 * The list contents are of type {@link ovap.video.filter.filtersetup.PortOutInstance}.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.filtersetup.PortOutInstance#getFilterInstance <em>Filter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Out Instance</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Out Instance</em>' containment reference list.
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterInstance_PortOutInstance()
	 * @see ovap.video.filter.filtersetup.PortOutInstance#getFilterInstance
	 * @model opposite="filterInstance" containment="true"
	 * @generated
	 */
	EList<PortOutInstance> getPortOutInstance();

	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration</em>' containment reference.
	 * @see #setConfiguration(Configuration)
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getFilterInstance_Configuration()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Configuration getConfiguration();

	/**
	 * Sets the value of the '{@link ovap.video.filter.filtersetup.FilterInstance#getConfiguration <em>Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration</em>' containment reference.
	 * @see #getConfiguration()
	 * @generated
	 */
	void setConfiguration(Configuration value);

} // FilterInstance
