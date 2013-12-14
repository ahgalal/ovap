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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ovap.video.filter.filtersetup.FilterModel;
import ovap.video.filter.filtersetup.FilterType;
import ovap.video.filter.filtersetup.FiltersetupPackage;
import ovap.video.filter.filtersetup.PortIn;
import ovap.video.filter.filtersetup.PortOut;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.impl.FilterTypeImpl#getPortOut <em>Port Out</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.impl.FilterTypeImpl#getPortIn <em>Port In</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.impl.FilterTypeImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterTypeImpl extends IdentifiableImpl implements FilterType {
	/**
	 * The cached value of the '{@link #getPortOut() <em>Port Out</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortOut()
	 * @generated
	 * @ordered
	 */
	protected EList<PortOut> portOut;

	/**
	 * The cached value of the '{@link #getPortIn() <em>Port In</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortIn()
	 * @generated
	 * @ordered
	 */
	protected EList<PortIn> portIn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FilterTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FiltersetupPackage.Literals.FILTER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortOut> getPortOut() {
		if (portOut == null) {
			portOut = new EObjectContainmentWithInverseEList<PortOut>(PortOut.class, this, FiltersetupPackage.FILTER_TYPE__PORT_OUT, FiltersetupPackage.PORT_OUT__FILTER_TYPE);
		}
		return portOut;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortIn> getPortIn() {
		if (portIn == null) {
			portIn = new EObjectContainmentWithInverseEList<PortIn>(PortIn.class, this, FiltersetupPackage.FILTER_TYPE__PORT_IN, FiltersetupPackage.PORT_IN__FILTER_TYPE);
		}
		return portIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterModel getModel() {
		if (eContainerFeatureID() != FiltersetupPackage.FILTER_TYPE__MODEL) return null;
		return (FilterModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(FilterModel newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, FiltersetupPackage.FILTER_TYPE__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(FilterModel newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != FiltersetupPackage.FILTER_TYPE__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, FiltersetupPackage.FILTER_MODEL__FILTER_TYPES, FilterModel.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FiltersetupPackage.FILTER_TYPE__MODEL, newModel, newModel));
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
			case FiltersetupPackage.FILTER_TYPE__PORT_OUT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPortOut()).basicAdd(otherEnd, msgs);
			case FiltersetupPackage.FILTER_TYPE__PORT_IN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPortIn()).basicAdd(otherEnd, msgs);
			case FiltersetupPackage.FILTER_TYPE__MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModel((FilterModel)otherEnd, msgs);
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
			case FiltersetupPackage.FILTER_TYPE__PORT_OUT:
				return ((InternalEList<?>)getPortOut()).basicRemove(otherEnd, msgs);
			case FiltersetupPackage.FILTER_TYPE__PORT_IN:
				return ((InternalEList<?>)getPortIn()).basicRemove(otherEnd, msgs);
			case FiltersetupPackage.FILTER_TYPE__MODEL:
				return basicSetModel(null, msgs);
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
			case FiltersetupPackage.FILTER_TYPE__MODEL:
				return eInternalContainer().eInverseRemove(this, FiltersetupPackage.FILTER_MODEL__FILTER_TYPES, FilterModel.class, msgs);
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
			case FiltersetupPackage.FILTER_TYPE__PORT_OUT:
				return getPortOut();
			case FiltersetupPackage.FILTER_TYPE__PORT_IN:
				return getPortIn();
			case FiltersetupPackage.FILTER_TYPE__MODEL:
				return getModel();
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
			case FiltersetupPackage.FILTER_TYPE__PORT_OUT:
				getPortOut().clear();
				getPortOut().addAll((Collection<? extends PortOut>)newValue);
				return;
			case FiltersetupPackage.FILTER_TYPE__PORT_IN:
				getPortIn().clear();
				getPortIn().addAll((Collection<? extends PortIn>)newValue);
				return;
			case FiltersetupPackage.FILTER_TYPE__MODEL:
				setModel((FilterModel)newValue);
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
			case FiltersetupPackage.FILTER_TYPE__PORT_OUT:
				getPortOut().clear();
				return;
			case FiltersetupPackage.FILTER_TYPE__PORT_IN:
				getPortIn().clear();
				return;
			case FiltersetupPackage.FILTER_TYPE__MODEL:
				setModel((FilterModel)null);
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
			case FiltersetupPackage.FILTER_TYPE__PORT_OUT:
				return portOut != null && !portOut.isEmpty();
			case FiltersetupPackage.FILTER_TYPE__PORT_IN:
				return portIn != null && !portIn.isEmpty();
			case FiltersetupPackage.FILTER_TYPE__MODEL:
				return getModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //FilterTypeImpl
