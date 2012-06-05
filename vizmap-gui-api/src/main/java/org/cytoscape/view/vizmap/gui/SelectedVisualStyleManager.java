package org.cytoscape.view.vizmap.gui;

import org.cytoscape.view.vizmap.VisualStyle;

/**
 * Manages current (selected) Visual Style. Selected style will be set by
 * {@link org.cytoscape.view.vizmap.gui.event.SelectedVisualStyleSwitchedListener}.
 * 
 * @CyAPI.Api.Interface
 */
public interface SelectedVisualStyleManager {

	/**
	 * Returns currently selected Visual Style.
	 * 
	 * @return Selected Visual Style.
	 * 
	 */
	VisualStyle getCurrentVisualStyle();
}
