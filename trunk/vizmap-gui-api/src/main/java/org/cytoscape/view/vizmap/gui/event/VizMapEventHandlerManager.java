package org.cytoscape.view.vizmap.gui.event;

/**
 * Manages {@link VizMapEventHandler}s.
 * 
 * Handlers are exported as OSGi services, and this manager keeps all of them.
 *
 * @CyAPI.Api.Interface
 */
public interface VizMapEventHandlerManager {

	/**
	 * Returns handler for the event.
	 * 
	 * @param name
	 *            name of property change event.
	 * 
	 * @return if handler exists for the given name, returns the handler.
	 *         Otherwise, returns null.
	 */
	VizMapEventHandler getHandler(final String name);

}