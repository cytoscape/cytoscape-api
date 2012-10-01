package org.cytoscape.view.vizmap.gui;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.presentation.RenderingEngine;

/**
 * GUI component to display default appearance of current Visual Style.
 *
 * @CyAPI.Api.Interface
 */
public interface DefaultViewPanel {

	/**
	 * Get dummy network view.
	 * Dummy network view is a network displayed on the default view editor.
	 * Usually it is a network with an edge and two nodes.
	 * 
	 * @return rendering engine object for the default view.
	 */
	RenderingEngine<CyNetwork> getRenderingEngine();

}