package org.cytoscape.view.model;

import java.util.Set;

public interface VisualLexicon2 {
	
	/**
	 * Returns the Set of VisualPropertys supported by this Renderer.
	 * 
	 * @return Set of all visual properties
	 * 
	 */
	Set<VisualProperty<?>> getAllVisualProperties();
	
	/**
	 * Get a set of Visual Properties.
	 * Usually, they are grouped by Graph Object: node, edge, and network.
	 * 
	 * @param groupName name of the category: node, edge, or network
	 * 
	 * @return Set of Visual properties in the category
	 */
	Set<VisualProperty<?>> getVisualPropertyGroup(final String groupName);
	
	/**
	 * Test the given Visual Property is supported in this Lexicon.
	 * 
	 * @param vp visual property to be tested.
	 * @return true if this lexicon supports the given vp.
	 */
	boolean isSupported(final VisualProperty<?> vp);
	

	/**
	 * Returns the appropriate visual property for the descriptive
	 * string.  The string is generally expected to be descriptive identifier
	 * from a file format (e.g. XGMML, GML) that can be mapped to a VisualProperty
	 * by the implementer of the VisualLexicon.  This method will return null if
	 * no match is found.
	 * 
	 * @param type The type of the visual property sought, which should 
	 * (in general) be CyNode.class, CyEdge.class, or CyNetwork.class.
	 * @param identifier A string identifying a particular visual property.
	 * 
	 * @return A VisualProperty identified by the input string or null if no
	 * match is found.
	 */
	VisualProperty<?> lookup(Class<?> type, String identifier);

}
