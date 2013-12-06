package ovap.video.launch.model;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

import ovap.video.Activator;
import ovap.video.Parameter;
import ovap.video.SessionState;
import ovap.video.VideoManager;
import sys.utils.Utils;
import utils.PDEUtils;

public class AnalysisTarget extends OVAPTarget {

	private class UpdateVariablesRunnable implements Runnable {
		@Override
		public void run() {
			while (!isTerminated()) {
				Utils.sleep(500);
				final ArrayList<Parameter> parameters = VideoManager
						.getDefault().getParameters(getSessionId());
				setParameters(parameters);
			}
		}
	}

	private class UpdateVariablesViewUIJob extends UIJob {

		public UpdateVariablesViewUIJob() {
			super(Display.getDefault(), "Update OVAP variables");
		}

		@SuppressWarnings("restriction")
		@Override
		public IStatus runInUIThread(final IProgressMonitor monitor) {
			for (final IVariable variable : getVariables()) {
				final PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(
						variable,
						IDebugUIConstants.PREF_CHANGED_VALUE_BACKGROUND, null,
						null);
				PDEUtils.getVariablesView().propertyChange(propertyChangeEvent);
			}
			return new Status(0, Activator.PLUGIN_ID, "Ok");
		}
	}

	private final Thread				thUpdateVariables;
	private final ArrayList<IVariable>	variables;

	private final UpdateVariablesViewUIJob	variableUIJob;

	public AnalysisTarget(final OVAPLaunch launch, final String name) {
		super(launch, name);
		variables = new ArrayList<IVariable>();
		thUpdateVariables = new Thread(new UpdateVariablesRunnable());
		thUpdateVariables.start();
		variableUIJob = new UpdateVariablesViewUIJob();
	}

	@Override
	public boolean canResume() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getSessionId());
		if (state == SessionState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public boolean canSuspend() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getSessionId());
		if (state == SessionState.RUNNING)
			return true;
		else
			return false;
	}

	@Override
	public boolean canTerminate() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getSessionId());
		if ((state == SessionState.RUNNING) || (state == SessionState.PAUSED))
			return true;
		else
			return false;
	}

	public String getSessionId() {
		try {
			return getLaunch().getLaunchConfiguration().getName() + "."
					+ getName();
		} catch (final DebugException e) {
			e.printStackTrace();
		}
		return null;
	}

	private IVariable getVariable(final String name) {
		for (final IVariable variable : variables)
			try {
				if (variable.getName().equals(name))
					return variable;
			} catch (final DebugException e) {
				e.printStackTrace();
			}
		final OVAPVariable ovapVariable = new OVAPVariable(name,this);
		variables.add(ovapVariable);
		return ovapVariable;
	}

	public IVariable[] getVariables() {
		return variables.toArray(new IVariable[0]);
	}

	@Override
	public boolean isSuspended() {
		final SessionState state = VideoManager.getDefault().getAnalysisState(
				getSessionId());
		if (state == SessionState.PAUSED)
			return true;
		else
			return false;
	}

	@Override
	public void resume() throws DebugException {
		VideoManager.getDefault().resumeAnalysis(getSessionId());
		fireEvent(DebugEvent.RESUME);
	}

	public void setParameters(final ArrayList<Parameter> parameters) {
		// DebugContextEvent event = new
		// DebugContextEvent(provider,structuredSelection ,
		// DebugContextEvent.ACTIVATED);
		// variablesView.debugContextChanged(event );
		for (final Parameter parameter : parameters) {
			final String name = parameter.getName();
			Object valueObj = parameter.getValue();
			if (valueObj == null)
				valueObj = "null";
			final String value = valueObj.toString();

			final IVariable variable = getVariable(name);
			try {
				variable.setValue(value);

				/*
				 * final DebugEvent debugEvent = new DebugEvent(variable,
				 * DebugEvent.CHANGE,DebugEvent.STATE | DebugEvent.CONTENT);
				 * DebugPlugin.getDefault().fireDebugEventSet( new DebugEvent[]
				 * { debugEvent });
				 */

			} catch (final DebugException e) {
				e.printStackTrace();
			}
		}

		if (variableUIJob.getState() != Job.RUNNING)
			variableUIJob.schedule();

		// fireEvent(DebugEvent.SUSPEND/*,DebugEvent.STATE | DebugEvent.CONTENT
		// | DebugEvent.s*/);
		// IElementContentProvider
		// contentProvider=(IElementContentProvider)getAdapter(IElementContentProvider.class);
		// contentProvider.update(new Child)
	}

	@Override
	public void suspend() throws DebugException {
		VideoManager.getDefault().pauseAnalysis(getSessionId());

		fireEvent(DebugEvent.SUSPEND);
	}

	@Override
	public void terminate() throws DebugException {
		VideoManager.getDefault().stopAnalysis(getSessionId());
		setTerminated(true);
		fireEvent(DebugEvent.TERMINATE);
	}

}