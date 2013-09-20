/**
 * 
 */
package ovap.video.source.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import ovap.video.source.SourceLaunchConfigs;
import ovap.video.source.SourceManager;
import ovap.video.source.SourceType;
import ovap.video.source.VideoSource;

/**
 * @author Creative
 */
public class SourceLaunchConfigurationTab extends AbstractLaunchConfigurationTab {
	private Button					btnBrowseVideoFile;
	private Combo					cmboSources;
	private Composite				composite_1;
	private Composite				container;
	private Group					grpSourceType;
	private Label					lblType;
	private Label					lblVideoFile;
	private HashMap<String, String>	source2Type;
	private TabFolder				tabFolder;
	private TabItem					tbtmCam;
	private TabItem					tbtmFile;
	private Text					txtVideoFile;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#canSave()
	 */
	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse
	 * .swt.widgets.Composite)
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createControl(final Composite parent) {
		container = new Composite(parent, 0);
		container.setLayout(new GridLayout(1, false));
		{
			grpSourceType = new Group(container, SWT.NONE);
			grpSourceType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
			grpSourceType.setText("Source:");
			grpSourceType.setLayout(new GridLayout(2, false));
			{
				lblType = new Label(grpSourceType, SWT.NONE);
				lblType.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
						false, false, 1, 1));
				lblType.setText("Stream Source:");
			}
			{
				cmboSources = new Combo(grpSourceType, SWT.READ_ONLY);
				cmboSources.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(final SelectionEvent e) {
						selectSource(cmboSources.getText());
					}
				});
				cmboSources.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
						true, false, 1, 1));
			}
		}
		{
			tabFolder = new TabFolder(container, SWT.NONE);
			tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
					false, 1, 1));
			{
				tbtmCam = new TabItem(tabFolder, SWT.NONE);
				tbtmCam.setText("CAM");
			}
			{
				tbtmFile = new TabItem(tabFolder, SWT.NONE);
				tbtmFile.setText("File");
				{
					composite_1 = new Composite(tabFolder, SWT.NONE);
					tbtmFile.setControl(composite_1);
					composite_1.setLayout(new GridLayout(3, false));
					{
						lblVideoFile = new Label(composite_1, SWT.NONE);
						lblVideoFile.setLayoutData(new GridData(SWT.RIGHT,
								SWT.CENTER, false, false, 1, 1));
						lblVideoFile.setBounds(0, 0, 49, 13);
						lblVideoFile.setText("Video file:");
					}
					{
						txtVideoFile = new Text(composite_1, SWT.BORDER
								| SWT.READ_ONLY);
						txtVideoFile.setLayoutData(new GridData(SWT.FILL,
								SWT.CENTER, true, false, 1, 1));
					}
					{
						btnBrowseVideoFile = new Button(composite_1, SWT.NONE);
						btnBrowseVideoFile
								.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(
											final SelectionEvent e) {
										final FileDialog dialog = new FileDialog(
												container.getShell());
										final String filePath = dialog.open();
										if ((filePath != null)
												&& (filePath != "")) {
											txtVideoFile.setText(filePath);
										}
									}
								});
						btnBrowseVideoFile.setText("...");
					}
				}
			}
		}

		source2Type = new HashMap<String, String>();
		
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getControl()
	 */
	@Override
	public Control getControl() {
		return container;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getErrorMessage()
	 */
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
	 */
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	@Override
	public String getName() {
		return "Source";
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		final ArrayList<VideoSource> sources = SourceManager.getSources();
		cmboSources.removeAll();
		for (final VideoSource videoSource : sources) {
			final String sourceName = videoSource.getName();
			cmboSources.add(sourceName);
			source2Type.put(sourceName, videoSource.getType().toString());
		}

		String savedSourceName = null;
		try {
			savedSourceName = configuration.getAttribute(
					SourceLaunchConfigs.SOURCE_NAME.toString(), "");
		} catch (final CoreException e) {
			e.printStackTrace();
		}
		selectSource(savedSourceName);

		String videoFilePath = null;
		try {
			videoFilePath = configuration.getAttribute(
					SourceLaunchConfigs.FILE_PATH.toString(), "");
		} catch (final CoreException e) {
			e.printStackTrace();
		}
		txtVideoFile.setText(videoFilePath);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#isValid(org.eclipse.debug
	 * .core.ILaunchConfiguration)
	 */
	@Override
	public boolean isValid(final ILaunchConfiguration launchConfig) {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#launched(org.eclipse.debug
	 * .core.ILaunch)
	 */
	@Override
	public void launched(final ILaunch launch) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse
	 * .debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(SourceLaunchConfigs.SOURCE_NAME.toString(),
				cmboSources.getText());
		configuration.setAttribute(SourceLaunchConfigs.FILE_PATH.toString(),
				txtVideoFile.getText());
	}

	private void selectSource(final String sourceName) {
		final int index = cmboSources.indexOf(sourceName);
		cmboSources.select(index);
		final String type = source2Type.get(sourceName);
		if (type != null) {
			if (type.equals(SourceType.CAM.toString())) {
				tabFolder.setSelection(0);
			} else if (type.equals(SourceType.FILE.toString())) {
				tabFolder.setSelection(1);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.
	 * debug.core.ILaunchConfigurationWorkingCopy)
	 */
	@Override
	public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub

	}

}
