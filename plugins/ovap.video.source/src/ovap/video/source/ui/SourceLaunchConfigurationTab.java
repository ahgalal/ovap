/**
 * 
 */
package ovap.video.source.ui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import ovap.video.Activator;
import ovap.video.launch.ui.OVAPLaunchConfigurationTab;
import ovap.video.source.CamSource;
import ovap.video.source.SourceLaunchConfigs;
import ovap.video.source.SourceManager;
import ovap.video.source.SourceType;
import ovap.video.source.VideoFileSource;
import ovap.video.source.VideoSource;
import ovap.video.source.ui.providers.SourceTableContentProvider;
import ovap.video.source.ui.providers.SourceTableLabelProvider;
import utils.FileUtils;
import utils.PDEUtils;

/**
 * @author Creative
 */
public class SourceLaunchConfigurationTab extends OVAPLaunchConfigurationTab {
	private Button								btnBrowseVideoFile;
	private Combo								cmboCam;
	private Combo								cmboFrameSize;
	private Composite							cmpstBasicOptions;
	private Composite							cmpstVideoFile;
	private Composite							container;
	private Group								grpAdvanced;
	private Group								grpBasicCam;
	private Group								grpBasicFile;
	private Group								grpOptions;
	private Group								grpSourceType;
	private ILaunchConfiguration				launchConfiguration;
	private Label								lblCam;
	private Label								lblFrameSize;
	private Label								lblVideoFile;
	private HashMap<String, AdvancedOptionsGUI>	sourceNameToAdvancedOptionsGUIMap;
	private Table								table;
	private TableViewer							tableViewer;
	private TableViewerColumn					tableViewerColumn;
	private TableViewerColumn					tableViewerColumn_1;
	private TableColumn							tblclmnName;

	private TableColumn							tblclmnType;

	private Text								txtVideoFile;

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
		sourceNameToAdvancedOptionsGUIMap = new HashMap<String, AdvancedOptionsGUI>();
		container = new Composite(parent, 0);
		container.setLayout(new GridLayout(1, false));
		{
			grpSourceType = new Group(container, SWT.NONE);
			grpSourceType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
			grpSourceType.setLayout(new GridLayout(2, false));
			grpSourceType.setText("Source");
			{
				{
					// TableViewer tableViewer = new TableViewer(grpSourceType);
					tableViewer = new TableViewer(grpSourceType, SWT.BORDER
							| SWT.FULL_SELECTION | SWT.SINGLE);
					table = tableViewer.getTable();
					table.setSelection(0);
					table.setLinesVisible(true);
					table.setHeaderVisible(true);
					{
						final GridData gd_table = new GridData(SWT.FILL,
								SWT.FILL, true, true, 2, 1);
						gd_table.widthHint = 198;
						table.setLayoutData(gd_table);
					}
					{
						tableViewerColumn = new TableViewerColumn(tableViewer,
								SWT.NONE);
						tblclmnName = tableViewerColumn.getColumn();
						tblclmnName.setWidth(270);
						tblclmnName.setText("Name");
					}
					{
						tableViewerColumn_1 = new TableViewerColumn(
								tableViewer, SWT.NONE);
						tblclmnType = tableViewerColumn_1.getColumn();
						tblclmnType.setWidth(100);
						tblclmnType.setText("Type");
					}
				}

				tableViewer
						.setContentProvider(new SourceTableContentProvider());
				tableViewer.setLabelProvider(new SourceTableLabelProvider());

				tableViewer
						.addSelectionChangedListener(new ISelectionChangedListener() {
							@Override
							public void selectionChanged(
									final SelectionChangedEvent event) {
								final IStructuredSelection selection = (IStructuredSelection) event
										.getSelection();
								if (selection.getFirstElement() != null) {
									final VideoSource videoSource = (VideoSource) selection
											.getFirstElement();
									switch (videoSource.getType()) {
										case CAM:
											grpBasicFile.setVisible(false);
											grpBasicCam.setVisible(true);
											break;
										case FILE:
											grpBasicCam.setVisible(false);
											grpBasicFile.setVisible(true);
											break;
										default:
											break;
									}
									for (final AdvancedOptionsGUI gui : sourceNameToAdvancedOptionsGUIMap
											.values())
										if (!gui.getControl().isDisposed())
											gui.getControl().setVisible(false);

									final AdvancedOptionsGUI advancedOptionsGUI = sourceNameToAdvancedOptionsGUIMap
											.get(videoSource.getName());
									if (advancedOptionsGUI != null)
										if (!advancedOptionsGUI.getControl()
												.isDisposed())
											advancedOptionsGUI.getControl()
													.setVisible(true);

									// load values to basic options group
									initializeCamOptions(videoSource);
									initializeVideoFileOptions(videoSource);

									updateLaunchConfigurationDialog();
								}
							}
						});
			}
		}
		{
			grpOptions = new Group(container, SWT.NONE);
			grpOptions.setLayout(new GridLayout(1, false));
			grpOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
					true, 1, 1));
			grpOptions.setText("Options");
			{
				cmpstBasicOptions = new Composite(grpOptions, SWT.NONE);
				cmpstBasicOptions.setLayout(null);
				cmpstBasicOptions.setLayoutData(new GridData(SWT.FILL,
						SWT.CENTER, true, false, 1, 1));
				{
					grpBasicCam = new Group(cmpstBasicOptions, SWT.NONE);
					grpBasicCam.setBounds(cmpstBasicOptions.getBounds());
					grpBasicCam.setLayout(new GridLayout(4, false));
					grpBasicCam.setText("Basic");
					{
						lblCam = new Label(grpBasicCam, SWT.NONE);
						lblCam.setLayoutData(new GridData(SWT.RIGHT,
								SWT.CENTER, false, false, 1, 1));
						lblCam.setText("Cam:");
					}
					{
						cmboCam = new Combo(grpBasicCam, SWT.READ_ONLY);
						cmboCam.setLayoutData(new GridData(SWT.FILL,
								SWT.CENTER, true, false, 1, 1));
						cmboCam.select(0);
						cmboCam.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(final SelectionEvent e) {
								updateLaunchConfigurationDialog();
							}
						});
					}
					{
						lblFrameSize = new Label(grpBasicCam, SWT.NONE);
						lblFrameSize.setLayoutData(new GridData(SWT.RIGHT,
								SWT.CENTER, false, false, 1, 1));
						lblFrameSize.setText("Frame size:");
					}
					{
						cmboFrameSize = new Combo(grpBasicCam, SWT.READ_ONLY);
						cmboFrameSize.setLayoutData(new GridData(SWT.FILL,
								SWT.CENTER, true, false, 1, 1));
						cmboFrameSize.select(0);
						cmboFrameSize
								.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(
											final SelectionEvent e) {
										updateLaunchConfigurationDialog();
									}
								});
					}
				}
				cmpstBasicOptions.addControlListener(new ControlListener() {

					@Override
					public void controlMoved(final ControlEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void controlResized(final ControlEvent e) {
						final Control[] children = cmpstBasicOptions
								.getChildren();
						for (final Control control : children)
							control.setSize(cmpstBasicOptions.getSize());
					}
				});
				{
					grpBasicFile = new Group(cmpstBasicOptions, SWT.NONE);
					grpBasicFile.setBounds(cmpstBasicOptions.getBounds());
					grpBasicFile.setLayout(new GridLayout(1, false));
					grpBasicFile.setText("Basic");
					{
						cmpstVideoFile = new Composite(grpBasicFile, SWT.NONE);
						cmpstVideoFile.setLayoutData(new GridData(SWT.FILL,
								SWT.CENTER, true, false, 1, 1));
						cmpstVideoFile.setSize(432, 33);
						cmpstVideoFile.setLayout(new GridLayout(3, false));
						{
							lblVideoFile = new Label(cmpstVideoFile, SWT.NONE);
							lblVideoFile.setLayoutData(new GridData(SWT.RIGHT,
									SWT.CENTER, false, false, 1, 1));
							lblVideoFile.setBounds(0, 0, 49, 13);
							lblVideoFile.setText("Video file:");
						}
						{
							txtVideoFile = new Text(cmpstVideoFile, SWT.BORDER
									| SWT.READ_ONLY);
							txtVideoFile.setLayoutData(new GridData(SWT.FILL,
									SWT.CENTER, true, false, 1, 1));
						}
						{
							btnBrowseVideoFile = new Button(cmpstVideoFile,
									SWT.NONE);
							btnBrowseVideoFile
									.addSelectionListener(new SelectionAdapter() {
										@Override
										public void widgetSelected(
												final SelectionEvent e) {
											final FileDialog dialog = new FileDialog(
													container.getShell());
											final String filePath = dialog
													.open();
											if ((filePath != null)
													&& (filePath != "")) {
												txtVideoFile.setText(filePath);
												updateLaunchConfigurationDialog();
											}
										}
									});
							btnBrowseVideoFile.setText("...");
						}
					}
				}
			}
			{
				grpAdvanced = new Group(grpOptions, SWT.NONE);
				grpAdvanced.setLayout(null);
				grpAdvanced.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
						true, true, 1, 1));
				grpAdvanced.setText("Advanced");
			}
		}

		/**
		 * we instantiate both the VideoSource and the AdvancedOptionsGUI
		 * attached to it here. this is not the smartest technique, as we
		 * instantiate the VideoSource just to get its name, and fill the
		 * sourceNameToAdvGUI map. we can alternatively identify the VideoSource
		 * by adding source name as an attribute in the extension point, and it
		 * shall match the VideoSource.getName() value for the source in the
		 * extension point.
		 */
		final IConfigurationElement[] extensions = PDEUtils
				.getExtensions(SourceManager.OVAP_DEVICE_INPUT_EP);
		for (final IConfigurationElement element : extensions) {
			final IConfigurationElement[] children = element.getChildren();
			if (children.length == 1) {
				final IConfigurationElement advancedOptionsElement = children[0];
				final AdvancedOptionsGUI advancedOptionsGUI = PDEUtils
						.instantiateExtension(AdvancedOptionsGUI.class,
								advancedOptionsElement);
				final VideoSource videoSource = PDEUtils.instantiateExtension(
						VideoSource.class, element);
				if (!sourceNameToAdvancedOptionsGUIMap.containsKey(videoSource
						.getName()) && (advancedOptionsGUI != null))
					sourceNameToAdvancedOptionsGUIMap.put(
							videoSource.getName(), advancedOptionsGUI);
				advancedOptionsGUI.createControls(grpAdvanced);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#dispose()
	 */
	@Override
	public void dispose() {

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

	private void initializeCamOptions(final VideoSource source) {
		if (source.getType() == SourceType.CAM) {
			cmboCam.removeAll();
			final CamSource camSource = (CamSource) source;
			final int numberOfCams = camSource.getNumberOfCams();
			for (int i = 0; i < numberOfCams; i++)
				cmboCam.add(i + "");
			String camIndex = "";
			try {
				camIndex = launchConfiguration.getAttribute(
						SourceLaunchConfigs.CAM_INDEX.toString(), "");
			} catch (final CoreException e) {
				e.printStackTrace();
			}
			if (camIndex.isEmpty())
				camIndex = "0";
			int index = cmboCam.indexOf(camIndex);
			cmboCam.select(index);

			cmboFrameSize.removeAll();
			final Point[] supportedFrameSizes = camSource
					.getSupportedFrameSizes();
			for (final Point frameSize : supportedFrameSizes)
				cmboFrameSize.add(frameSize.x + "x" + frameSize.y);

			String camFrameSize = "";
			try {
				camFrameSize = launchConfiguration.getAttribute(
						SourceLaunchConfigs.CAM_FRAME_SIZE.toString(), "");
			} catch (final CoreException e) {
				e.printStackTrace();
			}
			index = cmboFrameSize.indexOf(camFrameSize);
			if (index == -1)
				index = 0;
			cmboFrameSize.select(index);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	@Override
	public void initializeFrom(final ILaunchConfiguration configuration) {
		launchConfiguration = configuration;
		final ArrayList<VideoSource> sources = SourceManager.getSources();
		tableViewer.setInput(sources);
		String savedSourceName = null;
		VideoSource savedSource = null;
		try {
			savedSourceName = configuration.getAttribute(
					SourceLaunchConfigs.SOURCE_NAME.toString(), "");
		} catch (final CoreException e) {
			e.printStackTrace();
		}

		// if no saved source, select the first one in the list
		if (savedSourceName.equals(""))
			savedSource = sources.get(0);
		else {
			for (final VideoSource source : sources)
				if (source.getName().equals(savedSourceName)) {
					savedSource = source;
					break;
				}
		}
		selectSource(savedSource);

		initializeVideoFileOptions(savedSource);

		initializeCamOptions(savedSource);

		try {
			final Map<?, ?> attributes = launchConfiguration.getAttributes();
			final HashMap<String, String> options = new HashMap<String, String>();
			for (final Object key : attributes.keySet())
				options.put((String) key, (String) attributes.get(key));
			sourceNameToAdvancedOptionsGUIMap.get(savedSource.getName())
					.loadOptions(options);
		} catch (final CoreException e) {
			e.printStackTrace();
		}
	}

	private void initializeVideoFileOptions(final VideoSource source) {
		if (source.getType() == SourceType.FILE) {
			String videoFilePath = null;
			try {
				videoFilePath = launchConfiguration.getAttribute(
						SourceLaunchConfigs.FILE_PATH.toString(), "");
			} catch (final CoreException e) {
				e.printStackTrace();
			}

			// if no video file path is saved, select the first one found in the
			// project
			if (videoFilePath.equals("")) {
				String projectName = "";
				try {
					projectName = launchConfiguration.getAttribute(
							Activator.SETTING_PROJECT_NAME, "");
				} catch (final CoreException e) {
					e.printStackTrace();
				}
				IProject selectedProject = null;
				if (!projectName.equals(""))
					selectedProject = ResourcesPlugin.getWorkspace().getRoot()
							.getProject(projectName);
				if ((selectedProject != null) && selectedProject.exists()) {
					final ArrayList<IFile> files = FileUtils.getFiles(
							selectedProject, "avi");
					if (files.size() > 0)
						videoFilePath = files.get(0).getLocation().toOSString();
				}
			}
			txtVideoFile.setText(videoFilePath);
		}
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
				((VideoSource) ((IStructuredSelection) tableViewer
						.getSelection()).getFirstElement()).getName());
		configuration.setAttribute(SourceLaunchConfigs.FILE_PATH.toString(),
				txtVideoFile.getText());
		configuration.setAttribute(SourceLaunchConfigs.CAM_INDEX.toString(),
				cmboCam.getText());
		configuration.setAttribute(
				SourceLaunchConfigs.CAM_FRAME_SIZE.toString(),
				cmboFrameSize.getText());

		// copy updated options from advanced options GUI of the selected source
		final VideoSource selectedSource = (VideoSource) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();
		final AdvancedOptionsGUI advancedOptionsGUI = sourceNameToAdvancedOptionsGUIMap
				.get(selectedSource.getName());
		final HashMap<String, String> updatedOptions = advancedOptionsGUI
				.getUpdatedOptions();
		for (final String key : updatedOptions.keySet())
			configuration.setAttribute(key, updatedOptions.get(key));

	}

	private void selectSource(final VideoSource source) {
		tableViewer.setSelection(new StructuredSelection(source));
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

	@Override
	protected boolean validateData() {
		final VideoSource selectedSource = (VideoSource) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();
		if (selectedSource == null) {
			errorMessage = "Please select a video source";
			return false;
		}

		if (selectedSource instanceof CamSource) {
			if (cmboCam.getText().isEmpty()) {
				errorMessage = "Please select a camera";
				return false;
			}

			if (cmboFrameSize.getText().isEmpty()) {
				errorMessage = "Please select a valid frame size";
				return false;
			}
		} else if (selectedSource instanceof VideoFileSource) {
			if (txtVideoFile.getText().isEmpty()) {
				errorMessage = "Please select a video file";
				return false;
			}
		}
		errorMessage = null;
		return true;
	}
}
