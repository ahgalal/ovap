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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ovap.video.filter.setup.model.FilterConnection;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.FilterModel;
import ovap.video.filter.setup.model.FiltersSetup;
import ovap.video.filter.setup.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filters Setup</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ovap.video.filter.setup.model.impl.FiltersSetupImpl#getFilterInstances <em>Filter Instances</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.FiltersSetupImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link ovap.video.filter.setup.model.impl.FiltersSetupImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FiltersSetupImpl extends IdentifiableImpl implements FiltersSetup {
	/**
	 * The cached value of the '{@link #getFilterInstances() <em>Filter Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<FilterInstance> filterInstances;

	/**
	 * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<FilterConnection> connections;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FiltersSetupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.FILTERS_SETUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FilterInstance> getFilterInstances() {
		if (filterInstances == null) {
			filterInstances = new EObjectContainmentEList<FilterInstance>(FilterInstance.class, this, ModelPackage.FILTERS_SETUP__FILTER_INSTANCES);
		}
		return filterInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FilterConnection> getConnections() {
		if (connections == null) {
			connections = new EObjectContainmentWithInverseEList<FilterConnection>(FilterConnection.class, this, ModelPackage.FILTERS_SETUP__CONNECTIONS, ModelPackage.FILTER_CONNECTION__FILTERS_SETUP);
		}
		return connections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterModel getModel() {
		if (eContainerFeatureID() != ModelPackage.FILTERS_SETUP__MODEL) return null;
		return (FilterModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(FilterModel newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, ModelPackage.FILTERS_SETUP__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(FilterModel newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != ModelPackage.FILTERS_SETUP__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, ModelPackage.FILTER_MODEL__SETUP, FilterModel.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.FILTERS_SETUP__MODEL, newModel, newModel));
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
			case ModelPackage.FILTERS_SETUP__CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnections()).basicAdd(otherEnd, msgs);
			case ModelPackage.FILTERS_SETUP__MODEL:
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
			case ModelPackage.FILTERS_SETUP__FILTER_INSTANCES:
				return ((InternalEList<?>)getFilterInstances()).basicRemove(otherEnd, msgs);
			case ModelPackage.FILTERS_SETUP__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
			case ModelPackage.FILTERS_SETUP__MODEL:
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
			case ModelPackage.FILTERS_SETUP__MODEL:
				return eInternalContainer().eInverseRemove(this, ModelPackage.FILTER_MODEL__SETUP, FilterModel.class, msgs);
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
			case ModelPackage.FILTERS_SETUP__FILTER_INSTANCES:
				return getFilterInstances();
			case ModelPackage.FILTERS_SETUP__CONNECTIONS:
				return getConnections();
			case ModelPackage.FILTERS_SETUP__MODEL:
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
			case ModelPackage.FILTERS_SETUP__FILTER_INSTANCES:
				getFilterInstances().clear();
				getFilterInstances().addAll((Collection<? extends FilterInstance>)newValue);
				return;
			case ModelPackage.FILTERS_SETUP__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends FilterConnection>)newValue);
				return;
			case ModelPackage.FILTERS_SETUP__MODEL:
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
			case ModelPackage.FILTERS_SETUP__FILTER_INSTANCES:
				getFilterInstances().clear();
				return;
			case ModelPackage.FILTERS_SETUP__CONNECTIONS:
				getConnections().clear();
				return;
			case ModelPackage.FILTERS_SETUP__MODEL:
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
			case ModelPackage.FILTERS_SETUP__FILTER_INSTANCES:
				return filterInstances != null && !filterInstances.isEmpty();
			case ModelPackage.FILTERS_SETUP__CONNECTIONS:
				return connections != null && !connections.isEmpty();
			case ModelPackage.FILTERS_SETUP__MODEL:
				return getModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //FiltersSetupImpl
