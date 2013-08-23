/**
 * 
 */
package ovap.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.osgi.service.prefs.BackingStoreException;

import utils.PDEUtils;

/**
 * @author Creative
 *
 */
public class OVAPPropertyPage1 extends PropertyPage implements
		IWorkbenchPropertyPage {
	private Label labelAuthor;
	private Text textAuthor;
	private Label labelDescription;
	private Text textDescription;

	/**
	 * 
	 */
	public OVAPPropertyPage1() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean performOk() {
		IProject project=(IProject) getElement();
		PDEUtils.setProjectSetting(project, "ovap.project.info", "ovap.project.info.description", textDescription.getText());
		PDEUtils.setProjectSetting(project, "ovap.project.info", "ovap.project.info.author", textAuthor.getText());
		
		return super.performOk();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(2, false));
		{
			labelAuthor = new Label(container, SWT.NONE);
			labelAuthor.setText("Author");
		}
		{
			textAuthor = new Text(container, SWT.BORDER);
			{
				GridData gd_textAuthor = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_textAuthor.widthHint = 184;
				textAuthor.setLayoutData(gd_textAuthor);
			}
		}
		{
			labelDescription = new Label(container, SWT.NONE);
			{
				GridData gd_labelDescription = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_labelDescription.widthHint = 108;
				labelDescription.setLayoutData(gd_labelDescription);
			}
			labelDescription.setText("Description");
		}
		{
			textDescription = new Text(container, SWT.BORDER);
			textDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		}
		
		IScopeContext projectScope = new ProjectScope((IProject) getElement());
		IEclipsePreferences projectNode = projectScope.getNode("ovap.project.info");
		if (projectNode != null) {
			String description = projectNode.get("ovap.project.info.description", "");
			String author = projectNode.get("ovap.project.info.author", "");
			textDescription.setText(description);
			textAuthor.setText(author);
		}

		return container;
	}

}
