/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.filtersetup;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifiable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.Identifiable#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getIdentifiable()
 * @model
 * @generated
 */
public interface Identifiable extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see ovap.video.filter.filtersetup.FiltersetupPackage#getIdentifiable_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link ovap.video.filter.filtersetup.Identifiable#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='\r\n\t\t//return ((Identifiable)eObject).getName();\r\n\t\t//String container=\"\";\r\n\t\t//if(eObject.eContainer().eContainer()==null) // if root is our container\r\n\t\t//\tcontainer= ((Identifiable)eObject.eContainer()).getName()+\"/\";\r\n\t\t\r\n\t\t//return container + ((Identifiable)eObject).getName();\r\n\t\treturn super.eURIFragmentSegment(eStructuralFeature, eObject);\r\n\t'"
	 * @generated
	 */
	String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='\r\n\t\t// TODO: need to override getEObjectForURIFragmentRootSegment.getEObjectForURIFragmentRootSegment\r\n\t\t// to fully support hierarchical EObject paths\r\n\t\t//if(getName().equals(uriFragmentSegment))\r\n\t\t//\treturn this;\r\n\t\t\r\n\t\t//for(EObject eObject:eContents()){\r\n\t\t//\tif(((Identifiable)eObject).getName().equals(uriFragmentSegment))\r\n\t\t//\t\treturn eObject;\r\n\t\t//}\r\n\t\t//return null;\r\n\t\treturn super.eObjectForURIFragmentSegment(uriFragmentSegment);\r\n\t'"
	 * @generated
	 */
	EObject eObjectForURIFragmentSegment(String uriFragmentSegment);

} // Identifiable
