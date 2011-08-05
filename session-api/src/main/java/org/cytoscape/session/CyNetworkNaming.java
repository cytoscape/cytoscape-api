package org.cytoscape.session;


import org.cytoscape.model.CyNetwork;


/**
 * A utility that helps with the creation of unique network names.
 */
public interface CyNetworkNaming {
	/**
	 *  Generates a unique title for a subnetwork based on a parent network
	 * @param parentNetwork a non-null reference to a parent network
	 * @return a unique title somehow based on the title of the parent network
	 */
	String getSuggestedSubnetworkTitle(CyNetwork parentNetwork);

	/**
	 *  Generates a unique network title based on a provided suggestion.
	 * @param desiredTitle  the "ideal" title that we would like
	 * @return "desiredTitle" if this title is not yet in use, or 
	 * with a title that is unique and uses "desiredTitle" as a prefix
	 */
	String getSuggestedNetworkTitle(String desiredTitle);
}
