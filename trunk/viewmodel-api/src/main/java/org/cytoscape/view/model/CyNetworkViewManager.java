package org.cytoscape.view.model;

import java.util.Collection;
import java.util.Set;

import org.cytoscape.model.CyNetwork;

/**
 * Basic access to network views in an instance of Cytoscape.
 * 
 * @CyAPI.Api.Interface
 */
public interface CyNetworkViewManager {

	/**
	 * Provides the set of network views that are currently known to the network
	 * manager.
	 * 
	 * @return the set of all network views maintained by the network view manager
	 */
	Set<CyNetworkView> getNetworkViewSet();

	/**
	 * Returns a collection of network views corresponding to the specified network, if found.
	 * 
	 * @param network
	 *            The network we're requesting a view of.
	 * 
	 * @return Empty set if network view was found corresponding to "networkId",
	 *         else collection of network views
	 */
	Collection<CyNetworkView> getNetworkViews(final CyNetwork network);

	/**
	 * Determines whether a network view for the specified network is known to
	 * the network view manager.
	 * 
	 * @param network
	 *            The network for which we want to know whether a view exists.
	 * 
	 * @return true if a view was found that corresponds to "networkId", else
	 *         false
	 */
	boolean viewExists(final CyNetwork network);

	
	/**
	 * Destroys a network view.
	 * 
	 * @param view
	 *            a non-null network view
	 */
	void destroyNetworkView(final CyNetworkView view);


	/**
	 * Registers a network view with the network view manager.
	 * 
	 * @param view
	 *            a non-null network view
	 */
	void addNetworkView(final CyNetworkView view);


	/**
	 * Releases all currently held references and resources.
	 * 
	 * Note: make sure all references are released after this method call.
	 * Otherwise, it can be a potential memory leak problem.
	 * 
	 * */
	void reset();
}