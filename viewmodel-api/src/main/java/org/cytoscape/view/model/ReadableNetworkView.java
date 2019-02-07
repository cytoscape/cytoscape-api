package org.cytoscape.view.model;

import java.util.Collection;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNode;

public interface ReadableNetworkView {

	/**
	 * Returns a View for a specified Node.
	 *
	 * 
	 * @param node Node object
	 * 
	 * @return View for the given node object.
	 */
	ReadableView<CyNode> getNodeView(CyNode node);

	/**
	 * Returns a list of Views for all CyNodes in the network.
	 * 
	 * @return Collection of all node views in this network.
	 */
	Collection<? extends ReadableView<CyNode>> getNodeViews();

	/**
	 * Returns a View for a specified Edge.
	 * @param edge the edge to return the view for.
	 * 
	 * @return View model for the edge data.
	 */
	ReadableView<CyEdge> getEdgeView(CyEdge edge);

	/**
	 * Returns a list of Views for all CyEdges in the network.
	 * 
	 * @return All edge views in this network.
	 */
	Collection<? extends ReadableView<CyEdge>> getEdgeViews();

	/**
	 * Returns a list of all View including those for Nodes, Edges, and Network.
	 * 
	 * @return All view objects in this network including network view itself.
	 */
	Collection<? extends ReadableView<? extends CyIdentifiable>> getAllViews();

	/**
	  * Returns the ID of the renderer that must be used to render this view.
	  * 
	  * @see org.cytoscape.application.NetworkViewRenderer#getId()
	  * @see org.cytoscape.application.CyApplicationManager#getNetworkViewRenderer(rendererId)
	  */
	String getRendererId();

}