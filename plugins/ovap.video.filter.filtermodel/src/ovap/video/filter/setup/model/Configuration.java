/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.setup.model;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.Configuration#getEntries <em>Entries</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.setup.model.ModelPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends EObject {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entries</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entries</em>' map.
	 * @see ovap.video.filter.setup.model.ModelPackage#getConfiguration_Entries()
	 * @model mapType="ovap.video.filter.setup.model.ConfigurationMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getEntries();

} // Configuration
