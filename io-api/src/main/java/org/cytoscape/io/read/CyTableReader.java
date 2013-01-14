package org.cytoscape.io.read;

import org.cytoscape.model.CyTable;
import org.cytoscape.work.Task;

/**
 * An extension of the Task interface that returns an array of 
 * {@link org.cytoscape.model.CyTable} objects. 
 * Instances of this interface are created by {@link org.cytoscape.io.read.InputStreamTaskFactory}
 * objects registered as OSGi services, which are in turn processed
 * by associated reader manager objects that distinguish 
 * InputStreamTaskFactories based on the {@link org.cytoscape.io.DataCategory} associated with
 * the {@link org.cytoscape.io.CyFileFilter}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CyTableReader extends Task{

	/**
	 * Return an array of {@link org.cytoscape.model.CyTable} objects.
	 * @return An array of {@link org.cytoscape.model.CyTable} objects.
	 */
	CyTable[] getTables();
}
