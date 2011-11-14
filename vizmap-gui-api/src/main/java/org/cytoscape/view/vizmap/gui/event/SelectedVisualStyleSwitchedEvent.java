package org.cytoscape.view.vizmap.gui.event;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.vizmap.VisualStyle;

/**
 * This event will be fired when current Visual Style is switched.
 * 
 * @see org.cytoscape.view.vizmap.gui.SelectedVisualStyleManager
 * 
 * @CyAPI.Final.Class
 * 
 */
public final class SelectedVisualStyleSwitchedEvent extends AbstractCyEvent<Object> {

	private final VisualStyle lastVS;
	private final VisualStyle newVS;

	/**
	 * Constructor for this event.
	 * 
	 * @param source
	 *            event source object. This can be anything, but usually it is
	 *            {@link org.cytoscape.view.vizmap.gui.SelectedVisualStyleManager}
	 * @param lastVS Visual Style currently selected.
	 * @param newVS Visual Style to be switched.
	 */
	public SelectedVisualStyleSwitchedEvent(final Object source, final VisualStyle lastVS, final VisualStyle newVS) {
		super(source, SelectedVisualStyleSwitchedListener.class);
		this.newVS = newVS;
		this.lastVS = lastVS;
	}

	/**
	 * Returns current Visual Style
	 * 
	 * @return current Visual Style
	 */
	public VisualStyle getLastVisualStyle() {
		return lastVS;
	}

	
	/**
	 * Returns Visual Style to be switched to
	 * 
	 * @return Newly selected Visual Style
	 */
	public VisualStyle getNewVisualStyle() {
		return newVS;
	}
}
