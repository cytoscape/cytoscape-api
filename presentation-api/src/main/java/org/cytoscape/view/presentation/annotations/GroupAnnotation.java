package org.cytoscape.view.presentation.annotations;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import java.util.List;

/**
 *
 * The GroupAnnotation is a container for a collection of
 * annotations that should be grouped together.
 * 
 * <br><br>
 * A GroupAnnotation may contain members from either the foreground or background canvas.
 * Changing the canvas of the GroupAnnotation itself will not automatically set its members
 * to the same canvas, each annotation must have its setCanvas() method called individually.
 * <br><br>
 * The z-order of member annotations will automatically be set so that the annotations are adjacent
 * within the same canvas. The AnnotationManager is free to change the z-order of annotations in order
 * to maintain this property.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface GroupAnnotation extends Annotation {
	/**
	 * A comma-separate list of the UUIDs of the annotations
	 * that are part of this group
	 */
	public static final String MEMBERS = "memberUUIDs";


	/**
	 * Add a new annotation to the group.
	 * 
	 * @throws IllegalArgumentException if the given Annotation is already a member of another group
	 * @param member the annotation to add to the group
	 */
	public void addMember(Annotation member);

	/**
	 * Remove an annotation from the group
	 *
	 * @param member the annotation to be removed
	 */
	public void removeMember(Annotation member);

	/**
	 * Return the list of members for this group
	 *
	 * @return the list of group members
	 */
	public List<Annotation> getMembers();
}
