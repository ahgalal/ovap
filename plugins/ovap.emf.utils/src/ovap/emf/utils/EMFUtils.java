/**
 * 
 */
package ovap.emf.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import utils.FileUtils;

/**
 * @author Creative
 */
public class EMFUtils {
	private static HashMap<IProject, TransactionalEditingDomain>	map	= new HashMap<IProject, TransactionalEditingDomain>();

	public static TransactionalEditingDomain getEditingDomain(
			final IProject project) {
		TransactionalEditingDomain transactionalEditingDomain = map
				.get(project);
		if (transactionalEditingDomain == null) {
			transactionalEditingDomain = TransactionalEditingDomain.Factory.INSTANCE
					.createEditingDomain(new ResourceSetImpl());
			map.put(project, transactionalEditingDomain);
		}
		return transactionalEditingDomain;
	}

	public static Resource getEMFResource(final IResource wsResource) {
		final EditingDomain editingDomain = getEditingDomain(wsResource
				.getProject());
		final ResourceSet resourceSet = editingDomain.getResourceSet();
		final Resource resource = resourceSet.getResource(URI
				.createPlatformResourceURI(wsResource.getFullPath().toString(),
						true), true);
		return resource;
	}
	
	public static HashMap<String, String> getHashMap(final EMap<String, String> map) {
		final HashMap<String, String> hashMap = new HashMap<String, String>();
		for (final Entry<String, String> entry : map) {
			hashMap.put(entry.getKey(), entry.getValue());
		}
		return hashMap;
	}

	public static void loadAllResourcesInProjectToEditingDomain(IProject project,String string) {
		final ArrayList<IFile> files = FileUtils.getFiles(project, "model");
		TransactionalEditingDomain editingDomain = getEditingDomain(project);
		for (final IFile modelFile : files) {
			final URI uri = URI.createPlatformResourceURI(modelFile
					.getFullPath().toString(), true);
			editingDomain.getResourceSet().getResource(uri, true);
		}		
	}


}
