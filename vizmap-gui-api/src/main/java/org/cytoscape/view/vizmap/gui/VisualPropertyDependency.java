package org.cytoscape.view.vizmap.gui;

import java.util.Set;

import org.cytoscape.view.model.VisualProperty;

/**
 * Defines the dependency of Visual Properties.
 * 
 * @CyAPI.Api.Interface
 */
public interface VisualPropertyDependency {
	
	/**
	 * Provide text for the GUI check box.
	 * 
	 * @return Check box name as string.
	 */
	String getDisplayName();
	
	Set<VisualProperty<?>> getVisualProperties();

}
