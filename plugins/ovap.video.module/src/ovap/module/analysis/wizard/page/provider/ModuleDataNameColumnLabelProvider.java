package ovap.module.analysis.wizard.page.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

public class ModuleDataNameColumnLabelProvider extends ColumnLabelProvider {
	@Override
	public String getText(final Object element) {
		return ((ModuleData) element).name;
	}
}