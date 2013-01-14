package org.cytoscape.view.vizmap;

/**
 * TODO: Missing documentation
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface VisualPropertyDependencyFactory<T> {
	
	VisualPropertyDependency<T> createVisualPropertyDependency();

}
