
package org.cytoscape.application.swing.events;

import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelState;
import org.cytoscape.event.AbstractCyEvent;

/**
 * An event to indicate that a CytoPanel has changed state. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-application-api
 */
public final class CytoPanelStateChangedEvent extends AbstractCyEvent<Object> {

	private final CytoPanel cp;
	private final CytoPanelState newState;

	/**
	 * Constructor.
	 * @param source The object firing the event.
	 * @param cp The CytoPanel whose state has changed. 
	 * @param newState The new state of the CytoPanel. 
	 */
	public CytoPanelStateChangedEvent(final Object source, final CytoPanel cp, final CytoPanelState newState) {
		super(source, CytoPanelStateChangedListener.class);
		this.cp = cp;
		this.newState = newState;
	}

	/**
	 * Returns the CytoPanel whose state has changed. 
	 * @return the CytoPanel whose state has changed. 
	 */
	public CytoPanel getCytoPanel() {
		return cp;
	}

	/**
	 * Returns the new state of the CytoPanel. 
	 * @return The new state of the CytoPanel. 
	 */
	public CytoPanelState getNewState() {
		return newState;
	}
}
