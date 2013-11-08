/**
 * 
 */
package ovap.video.filter.ui;

import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;

import ovap.emf.utils.EMFUtils;
import ovap.video.filter.setup.model.FilterInstance;

/**
 * @author Creative
 */
public class ApplyFilterConfigurationCommand extends AbstractCommand {
	private Map<String, String>	configurationMap;
	private Map<String, String>	oldConfigurationMap;
	public ApplyFilterConfigurationCommand(Map<String, String> map,
			FilterInstance filterInstance) {
		super();
		this.configurationMap = map;
		this.filterInstance = filterInstance;
	}

	private FilterInstance			filterInstance;

	@Override
	public void execute() {
		oldConfigurationMap = EMFUtils.getHashMap(filterInstance.getConfiguration().getEntries());
		applyConfigurationUpdates();
	}

	private void applyConfigurationUpdates() {
		filterInstance.getConfiguration().getEntries().putAll(configurationMap);
	}

	@Override
	protected boolean prepare() {
		return true;
	}

	@Override
	public void redo() {
		applyConfigurationUpdates();
	}

	@Override
	public void undo() {
		filterInstance.getConfiguration().getEntries().putAll(oldConfigurationMap);
	}

}
