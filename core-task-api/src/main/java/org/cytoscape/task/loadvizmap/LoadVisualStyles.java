
package org.cytoscape.task.loadvizmap;

import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

import java.util.Set;
import java.io.File;

/**
 * An interface for creating new, empty network views.
 */
public interface LoadVisualStyles extends TaskFactory{

	/**
	 * Return a set of VisualStyle objects read from the specified file. 
	 * @param f The file containing visual styles to be read.
	 * @return a set of VisualStyle objects read from the specified file. 
	 */
	Set<VisualStyle> loadStyles(File f);
	
	TaskIterator createTaskIterator(File file);
}
