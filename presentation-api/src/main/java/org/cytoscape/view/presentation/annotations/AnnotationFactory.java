package org.cytoscape.view.presentation.annotations;

import java.util.Map;

import javax.swing.Icon;

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
 * An interface describing a factory used to create {@link Annotation}s.  This factory will be provided
 * as a service through OSGi.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface AnnotationFactory<T extends Annotation> {

	/**
	 * Returns the unique id of the AnnotationFactory implementation.
	 * Always use namespaces to make sure it is unique (e.g. "org.myCompany.myApp.AnnotationXxxxx").
	 * @return A unique id for this AnnotationFactory.
	 */
	String getId();
	
	/**
	 * A short name to be displayed to the user.
	 * @return The name of this AnnotationFactory.
	 */
	String getName();

	/**
	 * An icon that represents this AnnotationFactory.
	 * @return If null, Cytoscape may provide a default or random icon for this search provider.
	 */
	Icon getIcon();
	
	Class<T> getType();
	
	/**
	 * Create an annotation.  This method takes the type of annotation to create and a list
	 * of arguments to use to actually create the annotation.  This list may be null if the caller
	 * does not wish to pre-initialize annotations.  Creating an annotation does not add the
	 * annotation to the view.  In order to make the annotation visible, it must be added
	 * using {@link AnnotationManager#addAnnotation()} method.
	 *
	 * @param type the class of annotation you want to create
	 * @param view the {@link CyNetworkView} the new annotation will belong to
	 * @param argMap the arguments to use to initialize the annotation.  See the descriptions for
	 * each annotation type to see the list of keys for the arg map.
	 * @return the new annotation
	 */
	T createAnnotation(Class<? extends T> type, CyNetworkView view, Map<String, String> argMap);
}
