/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.filtersetup.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ovap.video.filter.filtersetup.FilterModel;
import ovap.video.filter.filtersetup.FilterType;
import ovap.video.filter.filtersetup.FiltersSetup;
import ovap.video.filter.filtersetup.FiltersetupPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.impl.FilterModelImpl#getFilterTypes <em>Filter Types</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.impl.FilterModelImpl#getSetup <em>Setup</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterModelImpl extends IdentifiableImpl implements FilterModel {
	/**
	 * The cached value of the '{@link #getFilterTypes() <em>Filter Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<FilterType> filterTypes;

	/**
	 * The cached value of the '{@link #getSetup() <em>Setup</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetup()
	 * @generated
	 * @ordered
	 */
	protected FiltersSetup setup;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FilterModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FiltersetupPackage.Literals.FILTER_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FilterType> getFilterTypes() {
		if (filterTypes == null) {
			filterTypes = new EObjectContainmentWithInverseEList<FilterType>(FilterType.class, this, FiltersetupPackage.FILTER_MODEL__FILTER_TYPES, FiltersetupPackage.FILTER_TYPE__MODEL);
		}
		return filterTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FiltersSetup getSetup() {
		return setup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSetup(FiltersSetup newSetup, NotificationChain msgs) {
		FiltersSetup oldSetup = setup;
		setup = newSetup;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FiltersetupPackage.FILTER_MODEL__SETUP, oldSetup, newSetup);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSetup(FiltersSetup newSetup) {
		if (newSetup != setup) {
			NotificationChain msgs = null;
			if (setup != null)
				msgs = ((InternalEObject)setup).eInverseRemove(this, FiltersetupPackage.FILTERS_SETUP__MODEL, FiltersSetup.class, msgs);
			if (newSetup != null)
				msgs = ((InternalEObject)newSetup).eInverseAdd(this, FiltersetupPackage.FILTERS_SETUP__MODEL, FiltersSetup.class, msgs);
			msgs = basicSetSetup(newSetup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FiltersetupPackage.FILTER_MODEL__SETUP, newSetup, newSetup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FiltersetupPackage.FILTER_MODEL__FILTER_TYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFilterTypes()).basicAdd(otherEnd, msgs);
			case FiltersetupPackage.FILTER_MODEL__SETUP:
				if (setup != null)
					msgs = ((InternalEObject)setup).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FiltersetupPackage.FILTER_MODEL__SETUP, null, msgs);
				return basicSetSetup((FiltersSetup)otherEnd, msgs);
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
			case FiltersetupPackage.FILTER_MODEL__FILTER_TYPES:
				return ((InternalEList<?>)getFilterTypes()).basicRemove(otherEnd, msgs);
			case FiltersetupPackage.FILTER_MODEL__SETUP:
				return basicSetSetup(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FiltersetupPackage.FILTER_MODEL__FILTER_TYPES:
				return getFilterTypes();
			case FiltersetupPackage.FILTER_MODEL__SETUP:
				return getSetup();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FiltersetupPackage.FILTER_MODEL__FILTER_TYPES:
				getFilterTypes().clear();
				getFilterTypes().addAll((Collection<? extends FilterType>)newValue);
				return;
			case FiltersetupPackage.FILTER_MODEL__SETUP:
				setSetup((FiltersSetup)newValue);
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
			case FiltersetupPackage.FILTER_MODEL__FILTER_TYPES:
				getFilterTypes().clear();
				return;
			case FiltersetupPackage.FILTER_MODEL__SETUP:
				setSetup((FiltersSetup)null);
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
			case FiltersetupPackage.FILTER_MODEL__FILTER_TYPES:
				return filterTypes != null && !filterTypes.isEmpty();
			case FiltersetupPackage.FILTER_MODEL__SETUP:
				return setup != null;
		}
		return super.eIsSet(featureID);
	}

} //FilterModelImpl
