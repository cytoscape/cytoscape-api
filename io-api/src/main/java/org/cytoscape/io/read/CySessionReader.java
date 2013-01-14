package org.cytoscape.io.read;

import org.cytoscape.session.CySession;
import org.cytoscape.work.Task;

/**
 * An extension of the Task interface that returns a
 * {@link org.cytoscape.property.session.Cysession} object. The reader does nothing
 * beyond create the {@link org.cytoscape.property.session.Cysession} object and does NOT 
 * use the {@link org.cytoscape.property.session.Cysession} object to define the state of 
 * Cytoscape - that is managed by the CySessionManager.
 * Instances of this interface are created by {@link org.cytoscape.io.read.InputStreamTaskFactory}
 * objects registered as OSGi services, which are in turn processed
 * by associated reader manager objects that distinguish 
 * InputStreamTaskFactories based on the {@link org.cytoscape.io.DataCategory} associated with
 * the {@link org.cytoscape.io.CyFileFilter}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CySessionReader extends Task {

	/**
	 * Returns a {@link org.cytoscape.session.CySession} object
	 * @return A {@link org.cytoscape.session.CySession} object. 
	 */
    CySession getSession();
}

