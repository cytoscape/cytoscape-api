
package org.cytoscape.application.swing.events;

import org.cytoscape.event.AbstractCyEvent;

/**
 * An event fired after Cytoscape startup mostly complete (but not necessarily 100 percent). 
 * This event is fired in the plugin-impl bundle, which depends on many Cytoscape bundles.
 * Warning: There is no guarantee that this event is fired after all bundles are start-up.  
 */
public final class CytoscapeStartEvent extends AbstractCyEvent<Object> {

	/**
	 * Constructor.
	 * @param source The object firing this event.
	 */
	public CytoscapeStartEvent(final Object source) {
		super(source, CytoscapeStartListener.class);
	}
}
