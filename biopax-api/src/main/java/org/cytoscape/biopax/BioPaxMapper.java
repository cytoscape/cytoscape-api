package org.cytoscape.biopax;

import org.biopax.paxtools.model.BioPAXElement;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;

/**
 * This API is provisional and is subject to change at any time.
 * 
 * @CyAPI.Api.Interface
 */
public interface BioPaxMapper {
	
	/**
	 * Cytoscape Attribute:  BioPAX Network.
	 * Stores boolean indicating this CyNetwork
	 * is a BioPAX network.
	 */
	static final String BIOPAX_NETWORK = "BIOPAX_NETWORK";
	
	/**
	 * Cytoscape Attribute:  BioPAX Network.
	 * Stores boolean indicating this CyNetwork
	 * is a converted to SIF format BioPAX network.
	 */
    static final String BINARY_NETWORK = "BINARY_NETWORK";
	
	/**
	 * Cytoscape Attribute:  BioPAX Edge Type.
	 */
	static final String BIOPAX_EDGE_TYPE = "BIOPAX_EDGE_TYPE";

	/**
	 * Cytoscape Attribute:  BioPAX RDF ID.
	 */
	static final String BIOPAX_RDF_ID = "URI";

	/**
	 * BioPax Node Attribute: Entity TYPE
	 */
	static final String BIOPAX_ENTITY_TYPE = "biopax_type";

	/**
	 * BioPax Node Attribute: CHEMICAL_MODIFICATIONS_MAP
	 */
	static final String BIOPAX_CHEMICAL_MODIFICATIONS_MAP = "chemical_modifications_map";

	/**
	 * BioPax Node Attribute: CHEMICAL_MODIFICATIONS_LIST
	 */
	static final String BIOPAX_CHEMICAL_MODIFICATIONS_LIST = "chemical_modifications";

	/**
	 * Node Attribute: UNIFICATION_REFERENCES
	 */
	static final String BIOPAX_UNIFICATION_REFERENCES = "unification_references";

	/**
	 * Node Attribute: RELATIONSHIP_REFERENCES
	 */
	static final String BIOPAX_RELATIONSHIP_REFERENCES = "relationship_references";

	/**
	 * Node Attribute: PUBLICATION_REFERENCES
	 */
	static final String BIOPAX_PUBLICATION_REFERENCES = "publication_references";

	/**
	 * Node Attribute:  XREF_IDs.
	 */
	static final String BIOPAX_XREF_IDS = "identifiers";

	/**
	 * Node Attribute:  BIOPAX_XREF_PREFIX.
	 */
	static final String BIOPAX_XREF_PREFIX = "xref.";

	/**
	 * Node Attribute: IHOP_LINKS
	 */
	static final String BIOPAX_IHOP_LINKS = "ihop_links";

	/**
	 * Node Attribute: AFFYMETRIX_REFERENCES
	 */
	static final String BIOPAX_AFFYMETRIX_REFERENCES_LIST = "affymetrix_references";

	
	/**
	 * Maps a BioPAX model (set internally) to a new CyNetwork.
	 * 
	 * @param networkName
	 * @return
	 */
	CyNetwork createCyNetwork(String networkName);
	
	/**
     * Maps BioPAX element properties to CyNode attributes.
     * @param element a BioPAX Object.
	 * @param node
	 * @param network
     */
	void createAttributesFromProperties(BioPAXElement element, CyNode node, CyNetwork network);
	
	/**
	 * Adds custom node shapes to BioPAX nodes.
	 * 
	 * @param networkView
	 */
	void customNodes(CyNetworkView networkView);
}
