package org.cytoscape.view.presentation.annotations;

import java.util.List;

import org.cytoscape.view.model.CyNetworkView;

/**
 * The annotation manager is responsible for managing all of the
 * annotations currently assigned to a particular 
 * {@link org.cytoscape.view.model.NetworkView}.  Annotations must
 * be added to the annotation manager to be displayed.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface AnnotationManager {
	/**
	 * Add an annotation to the specified network view.
	 *
	 * @param annotation the annotation to add
	 * @param networkView the network view to add this annotation to
	 */
	public void addAnnotation(Annotation annotation);

	/**
	 * Remove an annotation from its network view.  If there are
	 * no other references to this annotation, it will be garbage collected.
	 *
	 * @param annotation the annotation to remove
	 */
	public void removeAnnotation(Annotation annotation);

	/**
	 * Retrieve the list of annotations for a specific network view.
	 *
	 * @param networkView the network view to get the list from
	 * @return the list of annotations
	 */
	public List<Annotation> getAnnotations(CyNetworkView networkView);
}
