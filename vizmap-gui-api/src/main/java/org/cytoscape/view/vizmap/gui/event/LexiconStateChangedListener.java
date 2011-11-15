package org.cytoscape.view.vizmap.gui.event;

/**
 * Listener for {@link LexiconStateChangedEvent}.
 * 
 * @CyAPI.Spi.Interface
 */
public interface LexiconStateChangedListener {

	/**
	 * Handles the specified event.
	 * @param e the {@link LexiconStateChangedEvent} to handle.
	 */
	public void handleEvent(LexiconStateChangedEvent e);
}
