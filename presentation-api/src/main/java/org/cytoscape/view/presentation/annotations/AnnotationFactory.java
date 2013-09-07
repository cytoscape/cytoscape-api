package org.cytoscape.view.presentation.annotations;

import java.util.Map;

import org.cytoscape.view.model.CyNetworkView;

/**
 * An interface describing a factory used to create {@link Annotation}s.  This factory will be provided
 * as a service through OSGi.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface AnnotationFactory <T extends Annotation> {

	/**
	 * Create an annotation.  This method takes the type of annotation to create and a list
	 * of arguments to use to actually create the annotation.  This list may be null if the caller
	 * does not wish to pre-initialize annotations.  Creating an annotation does not add the
	 * annotation to the view.  In order to make the annotation visible, it must be added
	 * using {@link AnnotationManager#addAnnotation()} method.
	 *
	 * @param type the class of annotation you want to create
	 * @param argMap the arguments to use to initialize the annotation.  See the descriptions for
	 * each annotation type to see the list of keys for the arg map.
	 * @return the new annotation
	 */
	public T createAnnotation(Class<? extends T> type, CyNetworkView view, Map<String,String> argMap);
}
