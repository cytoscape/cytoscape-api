
package org.cytoscape.application.swing.events;

import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.event.AbstractCyEvent;

/**
 * The event fired to indicate that a {@link org.cytoscape.application.swing.CytoPanelComponent} has been selected.
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-application-api
 */
public final class CytoPanelComponentSelectedEvent extends AbstractCyEvent<Object> {

	private final CytoPanel cp;
	private final int index;

	/**
	 * Constructor.
	 * @param source The object firing the event.
	 * @param cp The CytoPanel on which a component was selected.
	 * @param index The index of the CytoPanelComponent selected.
	 */
	public CytoPanelComponentSelectedEvent(final Object source, final CytoPanel cp, int index) {
		super(source, CytoPanelComponentSelectedListener.class);
		this.cp = cp;
		this.index = index;
	}

	/**
	 * Returns the CytoPanel on which a component was selected.
	 * @return the CytoPanel on which a component was selected.
	 */
	public CytoPanel getCytoPanel() {
		return cp;
	}

	/**
	 * Returns the index of the CytoPanelComponent selected.
	 * @return the index of the CytoPanelComponent selected.
	 */
	public int getSelectedIndex() {
		return index;
	}
}
