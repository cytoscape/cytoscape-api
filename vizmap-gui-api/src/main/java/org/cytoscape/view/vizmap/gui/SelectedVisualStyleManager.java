package org.cytoscape.view.vizmap.gui;

import org.cytoscape.view.vizmap.VisualStyle;

/**
 * @CyAPI.Api.Interface
 */
public interface SelectedVisualStyleManager {

	
	VisualStyle getCurrentVisualStyle();
	
	VisualStyle getDefaultStyle();

}
