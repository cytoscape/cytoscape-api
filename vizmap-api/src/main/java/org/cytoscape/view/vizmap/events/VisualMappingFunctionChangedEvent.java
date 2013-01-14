package org.cytoscape.view.vizmap.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * VisualMappingFunctions should fire this event when the contents of the mapping
 * modified.
 * @CyAPI.InModule vizmap-api
 */
@SuppressWarnings("rawtypes")
// TODO: This really should be a final class but changing it now would break API
public class VisualMappingFunctionChangedEvent extends
		AbstractCyPayloadEvent<VisualMappingFunction, VisualMappingFunctionChangeRecord> {

	public VisualMappingFunctionChangedEvent(VisualMappingFunction source,
			Collection<VisualMappingFunctionChangeRecord> payload) {
		super(source, VisualMappingFunctionChangedListener.class, payload);
	}
}
