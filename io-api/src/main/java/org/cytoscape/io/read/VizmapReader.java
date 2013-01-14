package org.cytoscape.io.read;

import java.util.Set;

import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.Task;

/**
 * An extension of the Task interface that returns a 
 * {@link org.cytoscape.view.vizmap.VisualStyle} object. 
 * Instances of this interface are created by {@link org.cytoscape.io.read.InputStreamTaskFactory}
 * objects registered as OSGi services, which are in turn processed
 * by associated reader manager objects that distinguish 
 * InputStreamTaskFactories based on the {@link org.cytoscape.io.DataCategory} associated with
 * the {@link org.cytoscape.io.CyFileFilter}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface VizmapReader extends Task {

	/**
	 * Returns a list of {@link org.cytoscape.view.vizmap.VisualStyle} objects.
	 * @return A list of {@link org.cytoscape.view.vizmap.VisualStyle} objects.
	 */
	Set<VisualStyle> getVisualStyles();
}
