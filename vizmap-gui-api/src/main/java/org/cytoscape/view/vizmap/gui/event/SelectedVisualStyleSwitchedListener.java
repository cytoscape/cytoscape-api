package org.cytoscape.view.vizmap.gui.event;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link SelectedVisualStyleSwitchedEvent}.
 * @CyAPI.Spi.Interface
 */
public interface SelectedVisualStyleSwitchedListener extends CyListener {
	/**
	 * Handles the specified event.
	 * @param e the {@link SelectedVisualStyleSwitchedEvent} to handle.
	 */
	public void handleEvent(SelectedVisualStyleSwitchedEvent e);
}
