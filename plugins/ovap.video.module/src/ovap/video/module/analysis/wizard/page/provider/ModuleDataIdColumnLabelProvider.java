package ovap.video.module.analysis.wizard.page.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;


public class ModuleDataIdColumnLabelProvider extends ColumnLabelProvider {
	@Override
	public String getText(final Object element) {
		return ((ModuleData) element).id;
	}
}
