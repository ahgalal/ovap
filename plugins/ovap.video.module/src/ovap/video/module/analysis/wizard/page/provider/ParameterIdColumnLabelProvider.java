package ovap.video.module.analysis.wizard.page.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import ovap.video.Parameter;

public class ParameterIdColumnLabelProvider extends ColumnLabelProvider {
	@Override
	public String getText(final Object element) {
		return ((Parameter) element).getId();
	}
}