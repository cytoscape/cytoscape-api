package org.cytoscape.filter.view;

/**
 * Listens for changes in the system-level state of interactivity.  Cytoscape
 * automatically switches the transformation subsystem to non-interactive mode
 * when it detects it cannot process transformations in real time.  The initial
 * state is non-interactive.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface InteractivityChangedListener {
	/**
	 * Called by the transformation subsystem to indicate the state of
	 * interactivity has changed.
	 * 
	 * @param isInteractive {@code} true, if the system is running in
	 *                      interactive mode, or {@code false}, otherwise.
	 */
	void handleInteractivityChanged(boolean isInteractive);
}
