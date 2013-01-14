package org.cytoscape.view.vizmap.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.vizmap.VisualStyle;

/**
 * When contents (default values or mappings) of an existing
 * {@linkplain VisualStyle} is modified, this event should be fired.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule vizmap-api
 */
public final class VisualStyleChangedEvent extends AbstractCyPayloadEvent<VisualStyle, VisualStyleChangeRecord> {

	/**
	 * Constructor of this event.
	 * 
	 * @param source
	 *            VisualStyle changed.
	 */
	public VisualStyleChangedEvent(final VisualStyle source, Collection<VisualStyleChangeRecord> payload) {
		super(source, VisualStyleChangedListener.class, payload);
	}
}