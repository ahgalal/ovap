/**
 * 
 */
package ovap.video.filter.filtersetup.listener;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ovap.video.filter.filtersetup.Configuration;
import ovap.video.filter.filtersetup.FilterInstance;
import ovap.video.filter.filtersetup.FilterType;
import ovap.video.filter.filtersetup.FiltersSetup;
import ovap.video.filter.filtersetup.FiltersetupFactory;
import ovap.video.filter.filtersetup.PortIn;
import ovap.video.filter.filtersetup.PortInInstance;
import ovap.video.filter.filtersetup.PortOut;
import ovap.video.filter.filtersetup.PortOutInstance;

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

	private void handleChangingFilterInstanceType(
			final Notification notification) {
		// handle changing FilterType of a filter instance
		final ArrayList<Object> objectsToBeRemoved = new ArrayList<Object>();
		final Object notifier = notification.getNotifier();
		final Object newValue = notification.getNewValue();
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
		final EList<PortIn> portIns = ((FilterType) newValue).getPortIn();
		final EList<PortOut> portOuts = ((FilterType) newValue).getPortOut();

		for (final PortIn in : portIns) {
			final PortInInstance portInInstance = FiltersetupFactory.eINSTANCE
					.createPortInInstance();
			portInInstance.setType(in);
			portInInstance.setName(in.getName());
			((FilterInstance) notifier).getPortInInstance().add(portInInstance);
		}

		for (final PortOut out : portOuts) {
			final PortOutInstance portOutInstance = FiltersetupFactory.eINSTANCE
					.createPortOutInstance();
			portOutInstance.setType(out);
			portOutInstance.setName(out.getName());
			((FilterInstance) notifier).getPortOutInstance().add(
					portOutInstance);
		}

		System.out.println(notification);
	}

	@Override
	public void notifyChanged(final Notification notification) {
		super.notifyChanged(notification);
		final Object notifier = notification.getNotifier();
		if (notifier instanceof EObject) { // we are only interested in EObject
											// changes
			final Object newValue = notification.getNewValue();
			switch (notification.getEventType()) {
				case Notification.SET:
					if (notifier instanceof FilterInstance) {
						if (newValue instanceof FilterType) {
							handleChangingFilterInstanceType(notification);
						}
					}
					break;
				case Notification.ADD:
					if(notifier instanceof FiltersSetup){
						if(newValue instanceof FilterInstance){
							Configuration configs = FiltersetupFactory.eINSTANCE.createConfiguration();
							((FilterInstance) newValue).setConfiguration(configs );
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
