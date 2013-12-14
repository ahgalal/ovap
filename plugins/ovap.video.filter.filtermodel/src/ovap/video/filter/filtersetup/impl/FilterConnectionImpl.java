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

import ovap.video.filter.filtersetup.FilterConnection;
import ovap.video.filter.filtersetup.FiltersSetup;
import ovap.video.filter.filtersetup.FiltersetupPackage;
import ovap.video.filter.filtersetup.PortInInstance;
import ovap.video.filter.filtersetup.PortOutInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ovap.video.filter.filtersetup.impl.FilterConnectionImpl#getPortInInstance <em>Port In Instance</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.impl.FilterConnectionImpl#getPortOutInstance <em>Port Out Instance</em>}</li>
 *   <li>{@link ovap.video.filter.filtersetup.impl.FilterConnectionImpl#getFiltersSetup <em>Filters Setup</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterConnectionImpl extends IdentifiableImpl implements FilterConnection {
	/**
	 * The cached value of the '{@link #getPortInInstance() <em>Port In Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortInInstance()
	 * @generated
	 * @ordered
	 */
	protected PortInInstance portInInstance;

	/**
	 * The cached value of the '{@link #getPortOutInstance() <em>Port Out Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortOutInstance()
	 * @generated
	 * @ordered
	 */
	protected PortOutInstance portOutInstance;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FilterConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FiltersetupPackage.Literals.FILTER_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortInInstance getPortInInstance() {
		if (portInInstance != null && portInInstance.eIsProxy()) {
			InternalEObject oldPortInInstance = (InternalEObject)portInInstance;
			portInInstance = (PortInInstance)eResolveProxy(oldPortInInstance);
			if (portInInstance != oldPortInInstance) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE, oldPortInInstance, portInInstance));
			}
		}
		return portInInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortInInstance basicGetPortInInstance() {
		return portInInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPortInInstance(PortInInstance newPortInInstance, NotificationChain msgs) {
		PortInInstance oldPortInInstance = portInInstance;
		portInInstance = newPortInInstance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE, oldPortInInstance, newPortInInstance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortInInstance(PortInInstance newPortInInstance) {
		if (newPortInInstance != portInInstance) {
			NotificationChain msgs = null;
			if (portInInstance != null)
				msgs = ((InternalEObject)portInInstance).eInverseRemove(this, FiltersetupPackage.PORT_IN_INSTANCE__FILTER_CONNECTION, PortInInstance.class, msgs);
			if (newPortInInstance != null)
				msgs = ((InternalEObject)newPortInInstance).eInverseAdd(this, FiltersetupPackage.PORT_IN_INSTANCE__FILTER_CONNECTION, PortInInstance.class, msgs);
			msgs = basicSetPortInInstance(newPortInInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE, newPortInInstance, newPortInInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortOutInstance getPortOutInstance() {
		if (portOutInstance != null && portOutInstance.eIsProxy()) {
			InternalEObject oldPortOutInstance = (InternalEObject)portOutInstance;
			portOutInstance = (PortOutInstance)eResolveProxy(oldPortOutInstance);
			if (portOutInstance != oldPortOutInstance) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE, oldPortOutInstance, portOutInstance));
			}
		}
		return portOutInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortOutInstance basicGetPortOutInstance() {
		return portOutInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPortOutInstance(PortOutInstance newPortOutInstance, NotificationChain msgs) {
		PortOutInstance oldPortOutInstance = portOutInstance;
		portOutInstance = newPortOutInstance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE, oldPortOutInstance, newPortOutInstance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortOutInstance(PortOutInstance newPortOutInstance) {
		if (newPortOutInstance != portOutInstance) {
			NotificationChain msgs = null;
			if (portOutInstance != null)
				msgs = ((InternalEObject)portOutInstance).eInverseRemove(this, FiltersetupPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION, PortOutInstance.class, msgs);
			if (newPortOutInstance != null)
				msgs = ((InternalEObject)newPortOutInstance).eInverseAdd(this, FiltersetupPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION, PortOutInstance.class, msgs);
			msgs = basicSetPortOutInstance(newPortOutInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE, newPortOutInstance, newPortOutInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FiltersSetup getFiltersSetup() {
		if (eContainerFeatureID() != FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP) return null;
		return (FiltersSetup)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFiltersSetup(FiltersSetup newFiltersSetup, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFiltersSetup, FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFiltersSetup(FiltersSetup newFiltersSetup) {
		if (newFiltersSetup != eInternalContainer() || (eContainerFeatureID() != FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP && newFiltersSetup != null)) {
			if (EcoreUtil.isAncestor(this, newFiltersSetup))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFiltersSetup != null)
				msgs = ((InternalEObject)newFiltersSetup).eInverseAdd(this, FiltersetupPackage.FILTERS_SETUP__CONNECTIONS, FiltersSetup.class, msgs);
			msgs = basicSetFiltersSetup(newFiltersSetup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP, newFiltersSetup, newFiltersSetup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE:
				if (portInInstance != null)
					msgs = ((InternalEObject)portInInstance).eInverseRemove(this, FiltersetupPackage.PORT_IN_INSTANCE__FILTER_CONNECTION, PortInInstance.class, msgs);
				return basicSetPortInInstance((PortInInstance)otherEnd, msgs);
			case FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE:
				if (portOutInstance != null)
					msgs = ((InternalEObject)portOutInstance).eInverseRemove(this, FiltersetupPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION, PortOutInstance.class, msgs);
				return basicSetPortOutInstance((PortOutInstance)otherEnd, msgs);
			case FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFiltersSetup((FiltersSetup)otherEnd, msgs);
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
			case FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE:
				return basicSetPortInInstance(null, msgs);
			case FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE:
				return basicSetPortOutInstance(null, msgs);
			case FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP:
				return basicSetFiltersSetup(null, msgs);
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
			case FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP:
				return eInternalContainer().eInverseRemove(this, FiltersetupPackage.FILTERS_SETUP__CONNECTIONS, FiltersSetup.class, msgs);
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
			case FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE:
				if (resolve) return getPortInInstance();
				return basicGetPortInInstance();
			case FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE:
				if (resolve) return getPortOutInstance();
				return basicGetPortOutInstance();
			case FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP:
				return getFiltersSetup();
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
			case FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE:
				setPortInInstance((PortInInstance)newValue);
				return;
			case FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE:
				setPortOutInstance((PortOutInstance)newValue);
				return;
			case FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP:
				setFiltersSetup((FiltersSetup)newValue);
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
			case FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE:
				setPortInInstance((PortInInstance)null);
				return;
			case FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE:
				setPortOutInstance((PortOutInstance)null);
				return;
			case FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP:
				setFiltersSetup((FiltersSetup)null);
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
			case FiltersetupPackage.FILTER_CONNECTION__PORT_IN_INSTANCE:
				return portInInstance != null;
			case FiltersetupPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE:
				return portOutInstance != null;
			case FiltersetupPackage.FILTER_CONNECTION__FILTERS_SETUP:
				return getFiltersSetup() != null;
		}
		return super.eIsSet(featureID);
	}

} //FilterConnectionImpl
