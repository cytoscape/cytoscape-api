package org.cytoscape.view.presentation.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.annotations.Annotation;
import org.cytoscape.view.presentation.annotations.AnnotationManager;

/**
 * When {@link Annotation}s are added to the {@link AnnotationManager}, this event will be fired.
 * @CyAPI.Final.Class   
 * @CyAPI.InModule presentation-api
 */
public class AnnotationsAddedEvent extends AbstractCyPayloadEvent<CyNetworkView, Annotation> {

	public AnnotationsAddedEvent(CyNetworkView source, Collection<Annotation> annotations) {
		super(source, AnnotationsAddedListener.class, annotations);
	}
}
