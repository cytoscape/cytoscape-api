package org.cytoscape.view.vizmap.gui.event;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link SelectedVisualStyleSwitchedEvent}.
 */
public interface SelectedVisualStyleSwitchedListener extends CyListener {
	public void handleEvent(SelectedVisualStyleSwitchedEvent e);
}
