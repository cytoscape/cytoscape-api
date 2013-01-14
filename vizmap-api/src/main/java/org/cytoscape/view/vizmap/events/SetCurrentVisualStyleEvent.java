package org.cytoscape.view.vizmap.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.vizmap.VisualStyle;

/**
 * TODO: Missing documentation
 * @CyAPI.Final.Class
 * @CyAPI.InModule vizmap-api
 */
public final class SetCurrentVisualStyleEvent extends AbstractCyEvent<Object> {

	private final VisualStyle style;

	public SetCurrentVisualStyleEvent(final Object source, final VisualStyle currentVisualStyle) {
		super(source, SetCurrentVisualStyleListener.class);
		this.style = currentVisualStyle;
	}

	/**
	 * Get the newly selected current Visual Style.
	 * 
	 * @return current VisualStyle
	 */
	public VisualStyle getVisualStyle() {
		return style;
	}
}
