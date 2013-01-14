package org.cytoscape.view.vizmap.events;

import org.cytoscape.event.CyListener;

/**
 * TODO: Missing documentation 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface SetCurrentVisualStyleListener extends CyListener {

	/**
	 * Update states/GUI using newly selected current
	 * {@linkplain org.cytoscape.view.vizmap.VisualStyle} information.
	 * 
	 * @param e
	 *            Event containing current VisualStyle.
	 */
	void handleEvent(final SetCurrentVisualStyleEvent e);
}