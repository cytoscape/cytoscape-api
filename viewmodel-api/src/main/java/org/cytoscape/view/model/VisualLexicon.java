package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import java.util.Collection;
import java.util.Set;

/**
 * A RenderingEngine should provide <strong>one, immutable</strong> lexicon implementing this interface.
 * This is a pre-defined tree of VisualProperties designed by the RenderingEngine developer.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface VisualLexicon {
	
	/**
	 * Get root of this tree.
	 * To traverse this tree, use this node as the entry point.
	 * 
	 * @return root node.
	 */
	VisualProperty<NullDataType> getRootVisualProperty();
	
	
	/**
	 * Returns the Set of VisualPropertys supported by this Renderer.
	 * 
	 * @return Set of all visual properties
	 * 
	 */
	Set<VisualProperty<?>> getAllVisualProperties();

	/**
	 * Returns the appropriate visual property for the descriptive
	 * string.  The string is generally expected to be descriptive identifier
	 * from a file format (e.g. XGMML, GML) that can be mapped to a VisualProperty
	 * by the implementer of the VisualLexicon.  This method will return null if
	 * no match is found.
	 * @param type The type of the visual property sought, which should 
	 * (in general) be CyNode.class, CyEdge.class, or CyNetwork.class.
	 * @param identifier A string identifying a particular visual property.
	 * @return A VisualProperty identified by the input string or null if no
	 * match is found.
	 */
	VisualProperty<?> lookup(Class<?> type, String identifier);
	
	/**
	 * Get a tree node in for the given VisualProperty.
	 * 
	 * @param vp target VisualProperty in this lexicon.
	 * @return tree node for the VisualProperty in this lexicon.
	 * 
	 * @throws IllegalArgumentException If vp does not exist in the lexicon.
	 */
	VisualLexiconNode getVisualLexiconNode(VisualProperty<?> vp);
	
	
	/**
	 * Get collection of visual properties for a given object type (node/edge/network).
	 * 
	 * @param prop any visual property
	 * 
	 * @return Collection of visual properties for the type.
	 */
	Collection<VisualProperty<?>> getAllDescendants(VisualProperty<?> prop);
	
	
	/**
	 * Test the given Visual Property is supported in this Lexicon.
	 * 
	 * @param vp visual property to be tested.
	 * @return true if this lexicon supports the given vp.
	 */
	boolean isSupported(VisualProperty<?> vp);
	
	/**
	 * Returns a filtered value range for a {@link VisualProperty} that uses a {@link DiscreteRange}.
	 * VisualLexicons may override this method to remove or add values to
	 * a {@link DiscreteRange} for a built-in {@link VisualProperty} from BasicVisualLexicon.
	 *
	 * @param vp VisualProperty where vp.getRange().isDiscrete() == true
	 */
	<T> Set<T> getSupportedValueRange(VisualProperty<T> vp);
}
