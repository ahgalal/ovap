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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ovap.video.filter.setup.model.Configuration;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.FilterType;
import ovap.video.filter.setup.model.ModelFactory;
import ovap.video.filter.setup.model.ModelPackage;
import ovap.video.filter.setup.model.PortInInstance;
import ovap.video.filter.setup.model.PortOutInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.impl.FilterInstanceImpl#getType <em>Type</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.FilterInstanceImpl#getPortInInstance <em>Port In Instance</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.FilterInstanceImpl#getPortOutInstance <em>Port Out Instance</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.FilterInstanceImpl#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterInstanceImpl extends IdentifiableImpl implements FilterInstance {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected FilterType type;

	/**
	 * The cached value of the '{@link #getPortInInstance() <em>Port In Instance</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortInInstance()
	 * @generated
	 * @ordered
	 */
	protected EList<PortInInstance> portInInstance;

	/**
	 * The cached value of the '{@link #getPortOutInstance() <em>Port Out Instance</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortOutInstance()
	 * @generated
	 * @ordered
	 */
	protected EList<PortOutInstance> portOutInstance;

	/**
	 * The cached value of the '{@link #getConfiguration() <em>Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfiguration()
	 * @generated
	 * @ordered
	 */
	protected Configuration configuration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FilterInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.FILTER_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterType getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (FilterType)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.FILTER_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterType basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(FilterType newType) {
		FilterType oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.FILTER_INSTANCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortInInstance> getPortInInstance() {
		if (portInInstance == null) {
			portInInstance = new EObjectContainmentWithInverseEList<PortInInstance>(PortInInstance.class, this, ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE, ModelPackage.PORT_IN_INSTANCE__FILTER_INSTANCE);
		}
		return portInInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortOutInstance> getPortOutInstance() {
		if (portOutInstance == null) {
			portOutInstance = new EObjectContainmentWithInverseEList<PortOutInstance>(PortOutInstance.class, this, ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE, ModelPackage.PORT_OUT_INSTANCE__FILTER_INSTANCE);
		}
		return portOutInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public Configuration getConfiguration() {
		if(configuration==null)
			configuration = ModelFactory.eINSTANCE.createConfiguration();
		return configuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConfiguration(Configuration newConfiguration, NotificationChain msgs) {
		Configuration oldConfiguration = configuration;
		configuration = newConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.FILTER_INSTANCE__CONFIGURATION, oldConfiguration, newConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfiguration(Configuration newConfiguration) {
		if (newConfiguration != configuration) {
			NotificationChain msgs = null;
			if (configuration != null)
				msgs = ((InternalEObject)configuration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.FILTER_INSTANCE__CONFIGURATION, null, msgs);
			if (newConfiguration != null)
				msgs = ((InternalEObject)newConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.FILTER_INSTANCE__CONFIGURATION, null, msgs);
			msgs = basicSetConfiguration(newConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.FILTER_INSTANCE__CONFIGURATION, newConfiguration, newConfiguration));
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
			case ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPortInInstance()).basicAdd(otherEnd, msgs);
			case ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPortOutInstance()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE:
				return ((InternalEList<?>)getPortInInstance()).basicRemove(otherEnd, msgs);
			case ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE:
				return ((InternalEList<?>)getPortOutInstance()).basicRemove(otherEnd, msgs);
			case ModelPackage.FILTER_INSTANCE__CONFIGURATION:
				return basicSetConfiguration(null, msgs);
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
			case ModelPackage.FILTER_INSTANCE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE:
				return getPortInInstance();
			case ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE:
				return getPortOutInstance();
			case ModelPackage.FILTER_INSTANCE__CONFIGURATION:
				return getConfiguration();
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
			case ModelPackage.FILTER_INSTANCE__TYPE:
				setType((FilterType)newValue);
				return;
			case ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE:
				getPortInInstance().clear();
				getPortInInstance().addAll((Collection<? extends PortInInstance>)newValue);
				return;
			case ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE:
				getPortOutInstance().clear();
				getPortOutInstance().addAll((Collection<? extends PortOutInstance>)newValue);
				return;
			case ModelPackage.FILTER_INSTANCE__CONFIGURATION:
				setConfiguration((Configuration)newValue);
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
			case ModelPackage.FILTER_INSTANCE__TYPE:
				setType((FilterType)null);
				return;
			case ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE:
				getPortInInstance().clear();
				return;
			case ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE:
				getPortOutInstance().clear();
				return;
			case ModelPackage.FILTER_INSTANCE__CONFIGURATION:
				setConfiguration((Configuration)null);
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
			case ModelPackage.FILTER_INSTANCE__TYPE:
				return type != null;
			case ModelPackage.FILTER_INSTANCE__PORT_IN_INSTANCE:
				return portInInstance != null && !portInInstance.isEmpty();
			case ModelPackage.FILTER_INSTANCE__PORT_OUT_INSTANCE:
				return portOutInstance != null && !portOutInstance.isEmpty();
			case ModelPackage.FILTER_INSTANCE__CONFIGURATION:
				return configuration != null;
		}
		return super.eIsSet(featureID);
	}

} //FilterInstanceImpl
