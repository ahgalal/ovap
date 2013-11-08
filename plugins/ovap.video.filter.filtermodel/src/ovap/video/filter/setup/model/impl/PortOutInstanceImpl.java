/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ovap.video.filter.setup.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ovap.video.filter.setup.model.FilterConnection;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.ModelPackage;
import ovap.video.filter.setup.model.PortOut;
import ovap.video.filter.setup.model.PortOutInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Out Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.impl.PortOutInstanceImpl#getFilterInstance <em>Filter Instance</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.PortOutInstanceImpl#getFilterConnection <em>Filter Connection</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.PortOutInstanceImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortOutInstanceImpl extends PortInstanceImpl implements PortOutInstance {
	/**
	 * The cached value of the '{@link #getFilterConnection() <em>Filter Connection</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterConnection()
	 * @generated
	 * @ordered
	 */
	protected EList<FilterConnection> filterConnection;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected PortOut type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortOutInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.PORT_OUT_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterInstance getFilterInstance() {
		if (eContainerFeatureID() != ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE) return null;
		return (FilterInstance)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFilterInstance(FilterInstance newFilterInstance, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFilterInstance, ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterInstance(FilterInstance newFilterInstance) {
		if (newFilterInstance != eInternalContainer() || (eContainerFeatureID() != ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE && newFilterInstance != null)) {
			if (EcoreUtil.isAncestor(this, newFilterInstance))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFilterInstance != null)
				msgs = ((InternalEObject)newFilterInstance).eInverseAdd(this, ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE, FilterInstance.class, msgs);
			msgs = basicSetFilterInstance(newFilterInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE, newFilterInstance, newFilterInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FilterConnection> getFilterConnection() {
		if (filterConnection == null) {
			filterConnection = new EObjectWithInverseResolvingEList<FilterConnection>(FilterConnection.class, this, ModelPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION, ModelPackage.FILTER_CONNECTION__PORT_OUT_INSTANCE);
		}
		return filterConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortOut getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (PortOut)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.PORT_OUT_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortOut basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(PortOut newType) {
		PortOut oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.PORT_OUT_INSTANCE__TYPE, oldType, type));
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
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFilterInstance((FilterInstance)otherEnd, msgs);
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFilterConnection()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE:
				return basicSetFilterInstance(null, msgs);
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION:
				return ((InternalEList<?>)getFilterConnection()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE:
				return eInternalContainer().eInverseRemove(this, ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE, FilterInstance.class, msgs);
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
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE:
				return getFilterInstance();
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION:
				return getFilterConnection();
			case ModelPackage.PORT_OUT_INSTANCE__TYPE:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE:
				setFilterInstance((FilterInstance)newValue);
				return;
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION:
				getFilterConnection().clear();
				getFilterConnection().addAll((Collection<? extends FilterConnection>)newValue);
				return;
			case ModelPackage.PORT_OUT_INSTANCE__TYPE:
				setType((PortOut)newValue);
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
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE:
				setFilterInstance((FilterInstance)null);
				return;
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION:
				getFilterConnection().clear();
				return;
			case ModelPackage.PORT_OUT_INSTANCE__TYPE:
				setType((PortOut)null);
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
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE:
				return getFilterInstance() != null;
			case ModelPackage.PORT_OUT_INSTANCE__FILTER_CONNECTION:
				return filterConnection != null && !filterConnection.isEmpty();
			case ModelPackage.PORT_OUT_INSTANCE__TYPE:
				return type != null;
		}
		return super.eIsSet(featureID);
	}

} //PortOutInstanceImpl
