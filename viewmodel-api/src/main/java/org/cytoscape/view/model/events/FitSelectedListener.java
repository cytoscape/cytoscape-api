package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain FitSelectedEvent}.  
 *  
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface FitSelectedListener extends CyListener {
	
	/**
	 * Fit the selected nodes/edges to the current container.  Usually this fires another event to the presentation layer.
	 * 
	 * @param e Event containing target network view.
	 */
	public void handleEvent(FitSelectedEvent e);
}
