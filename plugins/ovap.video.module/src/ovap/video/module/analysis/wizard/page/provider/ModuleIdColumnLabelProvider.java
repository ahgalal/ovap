package ovap.video.module.analysis.wizard.page.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import ovap.video.module.Module;

public class ModuleIdColumnLabelProvider extends ColumnLabelProvider {
	@Override
	public String getText(final Object element) {
		return ((Module) element).getID();
	}
}