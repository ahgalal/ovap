/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.setup.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ovap.video.filter.setup.model.FilterConnection;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.ModelPackage;
import ovap.video.filter.setup.model.PortIn;
import ovap.video.filter.setup.model.PortInInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port In Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.impl.PortInInstanceImpl#getFilterInstance <em>Filter Instance</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.PortInInstanceImpl#getFilterConnection <em>Filter Connection</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.PortInInstanceImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortInInstanceImpl extends PortInstanceImpl implements PortInInstance {
	/**
	 * The cached value of the '{@link #getFilterConnection() <em>Filter Connection</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterConnection()
	 * @generated
	 * @ordered
	 */
	protected FilterConnection filterConnection;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected PortIn type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortInInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.PORT_IN_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterInstance getFilterInstance() {
		if (eContainerFeatureID() != ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE) return null;
		return (FilterInstance)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFilterInstance(FilterInstance newFilterInstance, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFilterInstance, ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterInstance(FilterInstance newFilterInstance) {
		if (newFilterInstance != eInternalContainer() || (eContainerFeatureID() != ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE && newFilterInstance != null)) {
			if (EcoreUtil.isAncestor(this, newFilterInstance))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFilterInstance != null)
				msgs = ((InternalEObject)newFilterInstance).eInverseAdd(this, ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE, FilterInstance.class, msgs);
			msgs = basicSetFilterInstance(newFilterInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE, newFilterInstance, newFilterInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterConnection getFilterConnection() {
		if (filterConnection != null && filterConnection.eIsProxy()) {
			InternalEObject oldFilterConnection = (InternalEObject)filterConnection;
			filterConnection = (FilterConnection)eResolveProxy(oldFilterConnection);
			if (filterConnection != oldFilterConnection) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION, oldFilterConnection, filterConnection));
			}
		}
		return filterConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterConnection basicGetFilterConnection() {
		return filterConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFilterConnection(FilterConnection newFilterConnection, NotificationChain msgs) {
		FilterConnection oldFilterConnection = filterConnection;
		filterConnection = newFilterConnection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION, oldFilterConnection, newFilterConnection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterConnection(FilterConnection newFilterConnection) {
		if (newFilterConnection != filterConnection) {
			NotificationChain msgs = null;
			if (filterConnection != null)
				msgs = ((InternalEObject)filterConnection).eInverseRemove(this, ModelPackage.FILTER_CONNECTION__PORT_IN_INSTANCE, FilterConnection.class, msgs);
			if (newFilterConnection != null)
				msgs = ((InternalEObject)newFilterConnection).eInverseAdd(this, ModelPackage.FILTER_CONNECTION__PORT_IN_INSTANCE, FilterConnection.class, msgs);
			msgs = basicSetFilterConnection(newFilterConnection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION, newFilterConnection, newFilterConnection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortIn getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (PortIn)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.PORT_IN_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortIn basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(PortIn newType) {
		PortIn oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PORT_IN_INSTANCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFilterInstance((FilterInstance)otherEnd, msgs);
			case ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION:
				if (filterConnection != null)
					msgs = ((InternalEObject)filterConnection).eInverseRemove(this, ModelPackage.FILTER_CONNECTION__PORT_IN_INSTANCE, FilterConnection.class, msgs);
				return basicSetFilterConnection((FilterConnection)otherEnd, msgs);
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
			case ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE:
				return basicSetFilterInstance(null, msgs);
			case ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION:
				return basicSetFilterConnection(null, msgs);
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
			case ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE:
				return eInternalContainer().eInverseRemove(this, ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE, FilterInstance.class, msgs);
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
			case ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE:
				return getFilterInstance();
			case ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION:
				if (resolve) return getFilterConnection();
				return basicGetFilterConnection();
			case ModelPackage.PORT_IN_INSTANCE__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE:
				setFilterInstance((FilterInstance)newValue);
				return;
			case ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION:
				setFilterConnection((FilterConnection)newValue);
				return;
			case ModelPackage.PORT_IN_INSTANCE__TYPE:
				setType((PortIn)newValue);
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
			case ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE:
				setFilterInstance((FilterInstance)null);
				return;
			case ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION:
				setFilterConnection((FilterConnection)null);
				return;
			case ModelPackage.PORT_IN_INSTANCE__TYPE:
				setType((PortIn)null);
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
			case ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE:
				return getFilterInstance() != null;
			case ModelPackage.PORT_IN_INSTANCE__FILTER_CONNECTION:
				return filterConnection != null;
			case ModelPackage.PORT_IN_INSTANCE__TYPE:
				return type != null;
		}
		return super.eIsSet(featureID);
	}

} //PortInInstanceImpl
