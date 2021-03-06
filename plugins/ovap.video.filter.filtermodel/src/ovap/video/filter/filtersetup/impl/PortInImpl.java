/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.filtersetup.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import ovap.video.filter.filtersetup.FilterType;
import ovap.video.filter.filtersetup.FiltersetupPackage;
import ovap.video.filter.filtersetup.PortIn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port In</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.impl.PortInImpl#getFilterType <em>Filter Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortInImpl extends PortImpl implements PortIn {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortInImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FiltersetupPackage.Literals.PORT_IN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterType getFilterType() {
		if (eContainerFeatureID() != FiltersetupPackage.PORT_IN__FILTER_TYPE) return null;
		return (FilterType)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFilterType(FilterType newFilterType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFilterType, FiltersetupPackage.PORT_IN__FILTER_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterType(FilterType newFilterType) {
		if (newFilterType != eInternalContainer() || (eContainerFeatureID() != FiltersetupPackage.PORT_IN__FILTER_TYPE && newFilterType != null)) {
			if (EcoreUtil.isAncestor(this, newFilterType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFilterType != null)
				msgs = ((InternalEObject)newFilterType).eInverseAdd(this, FiltersetupPackage.FILTER_TYPE__PORT_IN, FilterType.class, msgs);
			msgs = basicSetFilterType(newFilterType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FiltersetupPackage.PORT_IN__FILTER_TYPE, newFilterType, newFilterType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FiltersetupPackage.PORT_IN__FILTER_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFilterType((FilterType)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FiltersetupPackage.PORT_IN__FILTER_TYPE:
				return basicSetFilterType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case FiltersetupPackage.PORT_IN__FILTER_TYPE:
				return eInternalContainer().eInverseRemove(this, FiltersetupPackage.FILTER_TYPE__PORT_IN, FilterType.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FiltersetupPackage.PORT_IN__FILTER_TYPE:
				return getFilterType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FiltersetupPackage.PORT_IN__FILTER_TYPE:
				setFilterType((FilterType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FiltersetupPackage.PORT_IN__FILTER_TYPE:
				setFilterType((FilterType)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FiltersetupPackage.PORT_IN__FILTER_TYPE:
				return getFilterType() != null;
		}
		return super.eIsSet(featureID);
	}

} //PortInImpl
