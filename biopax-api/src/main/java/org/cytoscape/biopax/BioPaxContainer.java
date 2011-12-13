package org.cytoscape.biopax;

import java.awt.Component;

/**
 * This API is provisional and is subject to change at any time.
 * 
 *  @CyAPI.Api.Interface
 */
public interface BioPaxContainer {
	/**
	 * 
	 */
	void showDetails();
	
	/**
	 * 
	 */
	void showLegend();
	
	/**
	 * 
	 * @return
	 */
	Component getComponent();
}
