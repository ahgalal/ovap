/**
 * 
 */
package ovap.video.filter.ui;

import java.util.HashMap;

import org.eclipse.emf.common.command.AbstractCommand;

import ovap.video.filter.setup.model.Configuration;
import ovap.video.filter.setup.model.FilterInstance;
import ovap.video.filter.setup.model.ModelFactory;

/**
 * @author Creative
 */
public class ApplyFilterConfigurationCommand extends AbstractCommand {
	private HashMap<String, String>	configurationMap;
	public ApplyFilterConfigurationCommand(HashMap<String, String> configurationMap,
			FilterInstance filterInstance) {
		super();
		this.configurationMap = configurationMap;
		this.filterInstance = filterInstance;
	}

	private FilterInstance			filterInstance;
	private Configuration			oldConfiguration;

	@Override
	public void execute() {
		oldConfiguration = filterInstance.getConfiguration();
		applyConfigurationUpdates();
	}

	private void applyConfigurationUpdates() {
		final Configuration configuration = ModelFactory.eINSTANCE
				.createConfiguration();
		configuration.getEntries().putAll(configurationMap);
		filterInstance.setConfiguration(configuration);
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
		filterInstance.setConfiguration(oldConfiguration);
	}

}
