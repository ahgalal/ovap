/**
 * 
 */
package ovap.product.ui.perspective;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

/**
 * @author Creative
 */
public class OVAPPerspective implements IPerspectiveFactory {

	private void addFastViews(final IPageLayout layout) {
	}

	private void addViewShortcuts(final IPageLayout layout) {
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui
	 * .IPageLayout)
	 */
	@Override
	public void createInitialLayout(final IPageLayout layout) {
		addFastViews(layout);
		addViewShortcuts(layout);

		layout.addView("org.eclipse.debug.ui.DebugView", IPageLayout.TOP,
				0.24f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("org.eclipse.ui.navigator.ProjectExplorer",
				IPageLayout.LEFT, 0.2f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("org.eclipse.ui.views.PropertySheet",
				IPageLayout.BOTTOM, 0.68f, IPageLayout.ID_EDITOR_AREA);
		//layout.addView("ovap.video.filter.display.frame.view", IPageLayout.RIGHT, 0.57f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("org.eclipse.debug.ui.VariableView", IPageLayout.RIGHT,
				0.5f, "org.eclipse.debug.ui.DebugView");
		
		IPlaceholderFolderLayout placeholderFolder = layout.createPlaceholderFolder("ovap.video.filter.display.frame.folder", IPageLayout.RIGHT, 0.57f, IPageLayout.ID_EDITOR_AREA);
		placeholderFolder.addPlaceholder("ovap.video.filter.display.frame.view:*");
		
		//layout.addPlaceholder("ovap.video.filter.display.frame.view:*", IPageLayout.RIGHT, 0.57f, IPageLayout.ID_EDITOR_AREA);
		
		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
	}
}
