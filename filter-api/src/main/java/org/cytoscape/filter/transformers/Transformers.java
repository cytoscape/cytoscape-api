package org.cytoscape.filter.transformers;

/**
 * Identifiers of the {@link org.cytoscape.filter.model.Transformer Transformer}s
 * supplied by the core.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule filter-api
 */
public interface Transformers {
	/**
	 * The id of the core Attribute Filter. 
	 */
	static final String ATTRIBUTE_FILTER = "org.cytoscape.AttributeFilter";
	
	/**
	 * The id of the core Degree Filter. 
	 */
	static final String DEGREE_FILTER = "org.cytoscape.DegreeFilter";

	/**
	 * The id of the core Topology Filter. 
	 */
	static final String TOPOLOGY_FILTER = "org.cytoscape.TopologyFilter";

	/**
	 * The id of the core Interaction Transformer. 
	 */
	static final String INTERACTION_TRANSFORMER = "org.cytoscape.InteractionTransformer";
}
