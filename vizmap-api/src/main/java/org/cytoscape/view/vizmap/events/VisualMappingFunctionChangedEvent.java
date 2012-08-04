package org.cytoscape.view.vizmap.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * VisualMappingFunctions should fire this event when the contents of the mapping
 * modified.
 *
 */
@SuppressWarnings("rawtypes")
public class VisualMappingFunctionChangedEvent extends
		AbstractCyPayloadEvent<VisualMappingFunction, VisualMappingFunctionChangeRecord> {

	public VisualMappingFunctionChangedEvent(VisualMappingFunction source,
			Collection<VisualMappingFunctionChangeRecord> payload) {
		super(source, VisualMappingFunctionChangedListener.class, payload);
	}
}
