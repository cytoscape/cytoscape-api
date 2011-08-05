package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain FitSelectedEvent}.  
 *  
 * @author kono
 *
 */
public interface FitSelectedEventListener extends CyListener {
	
	/**
	 * Fit the selected nodes/edges to tthe current container.  Usually this fires another event to the presentation layer.
	 * 
	 * @param e Event containing target network view.
	 */
	public void handleEvent(FitSelectedEvent e);
}
