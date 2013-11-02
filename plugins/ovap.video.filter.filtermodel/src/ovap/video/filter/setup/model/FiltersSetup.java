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
 * A representation of the model object '<em><b>Filters Setup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.FiltersSetup#getFilterInstances <em>Filter Instances</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.FiltersSetup#getConnections <em>Connections</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.FiltersSetup#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.setup.model.ModelPackage#getFiltersSetup()
 * @model
 * @generated
 */
public interface FiltersSetup extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Filter Instances</b></em>' containment reference list.
	 * The list contents are of type {@link ovap.video.filter.setup.model.FilterInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Instances</em>' containment reference list.
	 * @see ovap.video.filter.setup.model.ModelPackage#getFiltersSetup_FilterInstances()
	 * @model containment="true" keys="name"
	 * @generated
	 */
	EList<FilterInstance> getFilterInstances();

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link ovap.video.filter.setup.model.FilterConnection}.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.FilterConnection#getFiltersSetup <em>Filters Setup</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see ovap.video.filter.setup.model.ModelPackage#getFiltersSetup_Connections()
	 * @see ovap.video.filter.setup.model.FilterConnection#getFiltersSetup
	 * @model opposite="filtersSetup" containment="true" keys="name"
	 * @generated
	 */
	EList<FilterConnection> getConnections();

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.FilterModel#getSetup <em>Setup</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(FilterModel)
	 * @see ovap.video.filter.setup.model.ModelPackage#getFiltersSetup_Model()
	 * @see ovap.video.filter.setup.model.FilterModel#getSetup
	 * @model opposite="setup" transient="false"
	 * @generated
	 */
	FilterModel getModel();

	/**
	 * Sets the value of the '{@link ovap.video.filter.setup.model.FiltersSetup#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(FilterModel value);

} // FiltersSetup
