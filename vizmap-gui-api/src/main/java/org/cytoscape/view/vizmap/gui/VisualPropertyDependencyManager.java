package org.cytoscape.view.vizmap.gui;

import java.util.Collection;

/**
 * Manager for dependencies.
 * 
 * TODO: refactor dependency mechanism.
 * 
 * @CyAPI.Api.Interface
 */
public interface VisualPropertyDependencyManager {
	
	/**
	 * Returns all registered dependencies.
	 * 
	 * @return all dependencies
	 */
	Collection<VisualPropertyDependency> getDependencies();
	
}
