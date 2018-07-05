package org.cytoscape.view.presentation.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.annotations.Annotation;
import org.cytoscape.view.presentation.annotations.AnnotationManager;

/**
 * When {@link Annotation}s are removed from a view and the {@link AnnotationManager}, this event will be fired.
 * @CyAPI.Final.Class   
 * @CyAPI.InModule presentation-api
 */
public class AnnotationsRemovedEvent extends AbstractCyPayloadEvent<CyNetworkView, Annotation> {

	public AnnotationsRemovedEvent(CyNetworkView source, Collection<Annotation> annotations) {
		super(source, AnnotationsRemovedListener.class, annotations);
	}
}
