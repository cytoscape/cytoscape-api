
package org.cytoscape.task.creation;

import org.cytoscape.view.vizmap.VisualStyle;
import java.util.Set;
import java.io.File;

/**
 * An interface for creating new, empty network views.
 */
public interface LoadVisualStyles {

	/**
	 * Return a set of VisualStyle objects read from the specified file. 
	 * @param f The file containing visual styles to be read.
	 * @return a set of VisualStyle objects read from the specified file. 
	 */
	Set<VisualStyle> loadStyles(File f);
}
