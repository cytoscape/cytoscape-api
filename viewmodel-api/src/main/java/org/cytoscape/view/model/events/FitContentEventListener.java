package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain FitContentEvent}.
 * 
 * @author kono
 *
 */
public interface FitContentEventListener extends CyListener {
	
	/**
	 * Process the event.  Usually, this handler will fire event to the presentation layer and invoke the redraw method in the rendering engine.
	 * 
	 * @param e
	 */
	public void handleEvent(FitContentEvent e);
}
