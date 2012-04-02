package org.cytoscape.view.vizmap;

import java.util.Set;

import org.cytoscape.view.model.VisualProperty;

/**
 * 
 * Plan 1: Use existing VisualLexicon Tree as is.
 * 
 * Set of Visual Properties which are dependent to each other
 * 
 * All Visual Properties should have save data type T.
 *
 */
public interface VisualPropertyDependency<T> {
	
	/**
	 * Provides human-readable name of this dependency.
	 * For example, "Synchronize edge color to arrow head color," 
	 * or "Fit Custom Graphics to node"
	 * 
	 * @return name of this dependency.
	 */
	String getDisplayName();
	
	/**
	 * A set of Visual Properties to be set by the parent if locked.
	 * 
	 * @return set of visual properties to be set by parent value.
	 */
	Set<VisualProperty<T>> getVisualProperties();
	
}
