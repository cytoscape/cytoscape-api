/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package org.cytoscape.view.model;

import java.util.Collection;
import java.util.Set;

/**
 * 
 * A {@linkplain org.cytoscape.view.presentation.RenderingEngine} should provide <strong>one, immutable</strong> lexicon implementing this interface.
 * 
 * This is a pre-defined tree of VisualProperties designed by the {@linkplain org.cytoscape.view.presentation.RenderingEngine} developer.
 * 
 * @since Cytoscape 3.0
 * 
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
	VisualLexiconNode getVisualLexiconNode(final VisualProperty<?> vp);
	
	
	/**
	 * Get collection of visual properties for a given object type (node/edge/network).
	 * 
	 * @param prop any visual property
	 * 
	 * @return Collection of visual properties for the type.
	 */
	Collection<VisualProperty<?>> getAllDescendants(final VisualProperty<?> prop);
	
	
	/**
	 * Test the given Visual Property is supported in this Lexicon.
	 * 
	 * @param vp visual property to be tested.
	 * @return true if this lexicon supports the given vp.
	 */
	boolean isSupported(final VisualProperty<?> vp);
		
}
