package org.cytoscape.view.vizmap.gui;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.presentation.RenderingEngine;

public interface DefaultViewPanel {

	/**
	 * Get dummy network view.
	 * Dummy network view is a network displayed on the default view editor.
	 * Usually it is a network with an edge and two nodes.
	 * 
	 * @return DOCUMENT ME!
	 */
	public RenderingEngine<CyNetwork> getRenderingEngine();

}