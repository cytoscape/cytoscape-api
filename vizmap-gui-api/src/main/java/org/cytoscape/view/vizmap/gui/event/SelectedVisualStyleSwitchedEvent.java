package org.cytoscape.view.vizmap.gui.event;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.vizmap.VisualStyle;

/**
 * #ASKMIKE constructor/method comments
 * @CyAPI.Final.Class
 */
public final class SelectedVisualStyleSwitchedEvent extends AbstractCyEvent<Object> {
	
	private final VisualStyle lastVS;
	private final VisualStyle newVS;

	public SelectedVisualStyleSwitchedEvent(final Object source, final VisualStyle lastVS, final VisualStyle newVS) {
		super(source, SelectedVisualStyleSwitchedListener.class);
		this.newVS = newVS;
		this.lastVS = lastVS;
	}
	
	public VisualStyle getLastVisualStyle() {
		return lastVS;
	}

	public VisualStyle getNewVisualStyle() {
		return newVS;
	}

}
