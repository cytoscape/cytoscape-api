package org.cytoscape.view.presentation.annotations;

import java.util.Collection;
import java.util.List;

import org.cytoscape.view.model.CyNetworkView;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

/**
 * The annotation manager is responsible for managing all of the
 * annotations currently assigned to a particular 
 * {@link org.cytoscape.view.model.NetworkView}.  Annotations must
 * be added to the annotation manager to be displayed.
 * 
 * <br><br>
 * Note: When using {@link GroupAnnotation} cycles are not allowed. The structure created by using GroupAnnotations
 * must be a tree. If adding a GroupAnnotation would cause a cycle then IllegalArgumentException will be thrown. 
 * 
 * <br><br>
 * Note: Changing the canvas of a GroupAnnotation will not automatically set its members
 * to the same canvas. Each annotation must have its setCanvas() method called individually.
 * 
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface AnnotationManager {
	
	/**
	 * Add an annotation to the specified network view. If the annotation is a GroupAnnotation
	 * then its members will be added as well.
	 *
	 * @param annotation the annotation to add
	 * @param networkView the network view to add this annotation to
	 * @throws IllegalArgumentException if the annotation is a GroupAnnotation and it causes a cycle
	 */
	public void addAnnotation(Annotation annotation);
	
	/**
	 * Adds annotations to the specified network view. GroupAnnotations
	 * will have its members added as well.
	 *
	 * @param annotations the annotations to add
	 * @param networkView the network view to add this annotation to
	 * @throws IllegalArgumentException if any of the annotations are a GroupAnnotation and causes a cycle
	 */
	public void addAnnotations(Collection<? extends Annotation> annotations);

	/**
	 * Remove an annotation from its network view.
	 * If there are no other references to this annotation, it will be garbage collected.
	 *
	 * @param annotation the annotation to remove
	 */
	public void removeAnnotation(Annotation annotation);
	
	/**
	 * Removes annotations from its network view .
	 *
	 * @param annotations the annotations to remove
	 */
	public void removeAnnotations(Collection<? extends Annotation> annotations);

	/**
	 * Retrieve the list of annotations for a specific network view.
	 *
	 * @param networkView the network view to get the list from
	 * @return the list of annotations
	 */
	public List<Annotation> getAnnotations(CyNetworkView networkView);
	
	/**
	 * Retrieve the list of selected annotations for a specific network view.
	 *
	 * @param networkView the network view to get the list from
	 * @return the list of selected annotations or an empty list if there is none.
	 */
	public List<Annotation> getSelectedAnnotations(CyNetworkView networkView);
}
