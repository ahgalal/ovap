/**
 * 
 */
package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

import com.thoughtworks.xstream.XStream;

/**
 * @author Creative
 *
 */
public class FileUtils {
	public static void copyFiles (File srcFolder, IContainer destFolder) {
	    for (File f: srcFolder.listFiles()) {
	        if (f.isDirectory()) {
	            IFolder newFolder = destFolder.getFolder(new Path(f.getName()));
	            try {
					newFolder.create(true, true, null);
				} catch (CoreException e) {
					e.printStackTrace();
				}
	            copyFiles(f, newFolder);
	        } else {
	            IFile newFile = destFolder.getFile(new Path(f.getName()));
	            try {
					newFile.create(new FileInputStream(f), true, null);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (CoreException e) {
					e.printStackTrace();
				}
	        }
	    }
	}
	
	public static void createFile(final IFile iFile) {
		try {
			iFile.create(new ByteArrayInputStream(new byte[0]), true,
					new NullProgressMonitor());
		} catch (final CoreException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveObject(Object o,File file){
		XStream xStream = new XStream();
		try {
			xStream.toXML(o, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Object loadObject(File file){
		Object o=null;
		XStream xStream = new XStream();
		try {
			o=xStream.fromXML(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public static ArrayList<IFile> getFiles(IContainer container,String extension){
		ArrayList<IFile> files=new ArrayList<IFile>();
		
		try {
			for(IResource member: container.members()){
				if(member instanceof IFile){
					if(member.getName().endsWith("."+extension))
						files.add((IFile) member);
				}
				else if(member instanceof IContainer){
					files.addAll(getFiles((IContainer) member, extension));
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return files;
	}
}
