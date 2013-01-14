
package org.cytoscape.application.events;

import org.cytoscape.event.AbstractCyEvent;

/**
 * An event fired after Cytoscape startup mostly complete (but not necessarily 100 percent). 
 * This event is fired in the app-impl bundle, which depends on many Cytoscape bundles.
 * Warning: There is no guarantee that this event is fired after all bundles are start-up.  
 * @CyAPI.Final.Class
 * @CyAPI.InModule application-api
 */
public final class CyStartEvent extends AbstractCyEvent<Object> {

	/**
	 * Constructor.
	 * @param source The object firing this event.
	 */
	public CyStartEvent(final Object source) {
		super(source, CyStartListener.class);
	}
}
