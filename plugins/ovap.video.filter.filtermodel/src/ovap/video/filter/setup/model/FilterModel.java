/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.setup.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.FilterModel#getFilterTypes <em>Filter Types</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.FilterModel#getSetup <em>Setup</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.setup.model.ModelPackage#getFilterModel()
 * @model
 * @generated
 */
public interface FilterModel extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Filter Types</b></em>' containment reference list.
	 * The list contents are of type {@link ovap.video.filter.setup.model.FilterType}.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.FilterType#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Types</em>' containment reference list.
	 * @see ovap.video.filter.setup.model.ModelPackage#getFilterModel_FilterTypes()
	 * @see ovap.video.filter.setup.model.FilterType#getModel
	 * @model opposite="model" containment="true" keys="name"
	 * @generated
	 */
	EList<FilterType> getFilterTypes();

	/**
	 * Returns the value of the '<em><b>Setup</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link ovap.video.filter.setup.model.FiltersSetup#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Setup</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Setup</em>' containment reference.
	 * @see #setSetup(FiltersSetup)
	 * @see ovap.video.filter.setup.model.ModelPackage#getFilterModel_Setup()
	 * @see ovap.video.filter.setup.model.FiltersSetup#getModel
	 * @model opposite="model" containment="true" required="true"
	 * @generated
	 */
	FiltersSetup getSetup();

	/**
	 * Sets the value of the '{@link ovap.video.filter.setup.model.FilterModel#getSetup <em>Setup</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Setup</em>' containment reference.
	 * @see #getSetup()
	 * @generated
	 */
	void setSetup(FiltersSetup value);

} // FilterModel
