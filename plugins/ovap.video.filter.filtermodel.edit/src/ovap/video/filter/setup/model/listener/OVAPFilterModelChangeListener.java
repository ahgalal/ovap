/**
 * 
 */
package ovap.video.filter.setup.model.listener;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.FilterType;
import ovap.video.filter.setup.model.ModelFactory;
import ovap.video.filter.setup.model.PortIn;
import ovap.video.filter.setup.model.PortInInstance;
import ovap.video.filter.setup.model.PortOut;
import ovap.video.filter.setup.model.PortOutInstance;
import ovap.video.filter.setup.model.impl.PortInInstanceImpl;

/**
 * @author Creative
 */
public class OVAPFilterModelChangeListener extends EContentAdapter {
	private static OVAPFilterModelChangeListener	self;

	public static OVAPFilterModelChangeListener getDefault() {
		if (self == null)
			self = new OVAPFilterModelChangeListener();
		return self;
	}

	private OVAPFilterModelChangeListener() {
	}

	@Override
	public void notifyChanged(final Notification notification) {
		super.notifyChanged(notification);
		final Object notifier = notification.getNotifier();
		if (notifier instanceof EObject) { // we are only interested in EObject
											// changes
			final Object newValue = notification.getNewValue();
			final Object oldValue = notification.getOldValue();
			final Object feature = notification.getFeature();

			switch (notification.getEventType()) {
				case Notification.ADD:
					break;
				case Notification.SET:
					// handle changing FilterType of a filter instance
					if (notifier instanceof FilterInstance) {
						if (newValue instanceof FilterType) {
							final ArrayList<Object> objectsToBeRemoved = new ArrayList<Object>();
							final EList<PortInInstance> portInInstances = ((FilterInstance) notifier)
									.getPortInInstance();

							// delete portin instances
							objectsToBeRemoved.addAll(portInInstances);

							final EList<PortOutInstance> portOutInstances = ((FilterInstance) notifier)
									.getPortOutInstance();

							// delete portout instances
							objectsToBeRemoved.addAll(portOutInstances);

							for (final Object instance : objectsToBeRemoved)
								EcoreUtil.delete((EObject) instance);
							objectsToBeRemoved.clear();

							// add port instances based on the new filter type
							final EList<PortIn> portIns = ((FilterType) newValue)
									.getPortIn();
							final EList<PortOut> portOuts = ((FilterType) newValue)
									.getPortOut();

							for (final PortIn in : portIns) {
								final PortInInstance portInInstance = ModelFactory.eINSTANCE
										.createPortInInstance();
								portInInstance.setType(in);
								portInInstance.setName(in.getName());
								((FilterInstance) notifier).getPortInInstance()
										.add(portInInstance);
							}

							for (final PortOut out : portOuts) {
								final PortOutInstance portOutInstance = ModelFactory.eINSTANCE
										.createPortOutInstance();
								portOutInstance.setType(out);
								portOutInstance.setName(out.getName());
								((FilterInstance) notifier)
										.getPortOutInstance().add(
												portOutInstance);
							}

							System.out.println(notification);
						}
					}

					break;
				default:
					break;
			}
		}
	}

}
