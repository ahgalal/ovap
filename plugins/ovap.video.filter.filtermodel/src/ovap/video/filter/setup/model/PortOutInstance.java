/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.setup.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Out Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.PortOutInstance#getFilterInstance <em>Filter Instance</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.PortOutInstance#getFilterConnection <em>Filter Connection</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.PortOutInstance#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.setup.model.ModelPackage#getPortOutInstance()
 * @model
 * @generated
 */
public interface PortOutInstance extends PortInstance {
	/**
	 * Returns the value of the '<em><b>Filter Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.FilterInstance#getPortOutInstance <em>Port Out Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Instance</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Instance</em>' container reference.
	 * @see #setFilterInstance(FilterInstance)
	 * @see ovap.video.filter.setup.model.ModelPackage#getPortOutInstance_FilterInstance()
	 * @see ovap.video.filter.setup.model.FilterInstance#getPortOutInstance
	 * @model opposite="portOutInstance" required="true" transient="false"
	 * @generated
	 */
	FilterInstance getFilterInstance();

	/**
	 * Sets the value of the '{@link ovap.video.filter.setup.model.PortOutInstance#getFilterInstance <em>Filter Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Instance</em>' container reference.
	 * @see #getFilterInstance()
	 * @generated
	 */
	void setFilterInstance(FilterInstance value);

	/**
	 * Returns the value of the '<em><b>Filter Connection</b></em>' reference list.
	 * The list contents are of type {@link ovap.video.filter.setup.model.FilterConnection}.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.FilterConnection#getPortOutInstance <em>Port Out Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Connection</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Connection</em>' reference list.
	 * @see ovap.video.filter.setup.model.ModelPackage#getPortOutInstance_FilterConnection()
	 * @see ovap.video.filter.setup.model.FilterConnection#getPortOutInstance
	 * @model opposite="portOutInstance"
	 * @generated
	 */
	EList<FilterConnection> getFilterConnection();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(PortOut)
	 * @see ovap.video.filter.setup.model.ModelPackage#getPortOutInstance_Type()
	 * @model required="true"
	 * @generated
	 */
	PortOut getType();

	/**
	 * Sets the value of the '{@link ovap.video.filter.setup.model.PortOutInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(PortOut value);

} // PortOutInstance
