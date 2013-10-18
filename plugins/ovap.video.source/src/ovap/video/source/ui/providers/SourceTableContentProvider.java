package ovap.video.source.ui.providers;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import ovap.video.source.VideoSource;

public class SourceTableContentProvider implements
		IStructuredContentProvider {
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		ArrayList<VideoSource> videoSources = (ArrayList<VideoSource>) inputElement;
		return videoSources.toArray(new VideoSource[0]);
	}
}