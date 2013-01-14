package org.cytoscape.io.read;

import org.cytoscape.work.Task;

/**
 * An extension of the Task interface that returns a property 
 * object. The type of the object returned can be be anything, 
 * but will in practice be
 * {@link java.util.Properties}, 
 * {@link org.cytoscape.property.bookmark.Bookmarks}, 
 * {@link org.cytoscape.property.session.Cysession}. The system
 * determines the specific object type based on the {@link org.cytoscape.io.CyFileFilter}
 * associated with the InputStreamTaskFactory service that  
 * produces this reader.
 * Instances of this interface are created by InputStreamTaskFactory
 * objects registered as OSGi services, which are in turn processed
 * by associated reader manager objects that distinguish 
 * InputStreamTaskFactories based on the DataCategory associated with
 * the {@link org.cytoscape.io.CyFileFilter}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CyPropertyReader extends Task {

	/**
	 * Return a property object.
	 * @return A property object.  The type can be
	 * be anything, but in practice will be
	 * {@link java.util.Properties}, 
	 * {@link org.cytoscape.property.bookmark.Bookmarks}, and
	 * {@link org.cytoscape.property.session.Cysession}.
	 */
    Object getProperty();

}

