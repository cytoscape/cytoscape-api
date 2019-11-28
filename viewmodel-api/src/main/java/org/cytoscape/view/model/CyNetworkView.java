package org.cytoscape.view.model;

import java.util.Collection;

import org.cytoscape.model.CyDisposable;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * 
 * Additional methods for CyNetworkView. Network view should implement BOTH View
 * and CyNetworkView.
 * 
 * <p>
 * <i>Warning</i>: if you just added a node or edge in
 * {@link org.cytoscape.model.CyNetwork},
 * {@link #getNodeView} or {@link #getEdgeView}
 * will probably return null for the newly created node or edge.
 * You may have to call
 * {@link org.cytoscape.event.CyEventHelper#flushPayloadEvents}
 * <i>prior</i> to calling {@link #getNodeView} or {@link #getEdgeView},
 * so that the {@code CyNetworkView} gets a chance to create the views.
 * If you are creating a bunch of nodes and edges at once, call {@code flushPayloadEvents}
 * <i>after</i> all the nodes and edges are created.
 * </p>
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyNetworkView extends View<CyNetwork>, CyDisposable {

	
	/**
	 * Returns a View for a specified Node.
	 * @param node Node object
	 * @return View for the given node object.
	 */
	View<CyNode> getNodeView(CyNode node);
	
	/**
	 * Returns a list of Views for all CyNodes in the network.
	 * 
	 * @return Collection of all node views in this network.
	 */
	Collection<View<CyNode>> getNodeViews();
	
	/**
	 * Returns an Iterable for all node views in the network.
	 * This operation may have better performance than 
	 * {@link CyNetworkView#getNodeViews()} but that is not guaranteed.
	 */
	default Iterable<View<CyNode>> getNodeViewsIterable() {
		return getNodeViews();
	}

	/**
	 * Returns a View for a specified Edge.
	 * @param edge the edge to return the view for.
	 * @return View model for the edge data.
	 */
	View<CyEdge> getEdgeView(CyEdge edge);

	/**
	 * Returns a list of Views for all CyEdges in the network.
	 * 
	 * @return All edge views in this network.
	 */
	Collection<View<CyEdge>> getEdgeViews();
	
	/**
	 * Returns an Iterable for all edge views in the network.
	 * This operation may have better performance than 
	 * {@link CyNetworkView#getEdgeViews()} but that is not guaranteed.
	 */
	default Iterable<View<CyEdge>> getEdgeViewsIterable() {
		return getEdgeViews();
	}

	/**
	 * Returns a list of all View including those for Nodes, Edges, and Network.
	 * 
	 * @return All view objects in this network including network view itself.
	 */
	Collection<View<? extends CyIdentifiable>> getAllViews();
	
	/**
	 * Utility method to fit content to the presentation container (usually a Swing Window).
	 * This fires event to the presentation layer for updating presentation.
	 */
	void fitContent();
	
	
	/**
	 * Utility method to fit selected graph objects to the presentation container.
	 * This fires event to the presentation layer for updating presentation.
	 */
	void fitSelected();
	
	
	/**
	 * Cascading event for the presentation layer for updating presentation.
	 */
	void updateView();
	
	/**
	 * Returns the ID of the renderer that must be used to render this view.
	 * 
	 * @see org.cytoscape.application.NetworkViewRenderer#getId()
	 * @see org.cytoscape.application.CyApplicationManager#getNetworkViewRenderer(rendererId)
	 */
	String getRendererId();
	
	/**
	 * Sets the default value to be used for the specified visual property.
	 * @param <T> The type of the visual property value. 
	 * @param <V> The default value for the visual property, which must extend T. 
	 * @param vp The visual property whose default value we're specifying.
	 * @param defaultValue The default value to be used for this visual property for this view. 
	 */
	 <T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);
	
	 
	/**
	 * Returns true if this network view supports creating snapshots,
	 * the createSnapshot() method will not throw an exception.
	 */
	default boolean supportsSnapshots() {
		return false;
	}
	
	/**
	 * Returns an immutable snapshot of this network view (optional operation).
	 * 
	 * @throws UnsupportedOperationException if creating a snapshot 
	 *   is not supported by this network view
	 */
	default CyNetworkViewSnapshot createSnapshot() {
		throw new UnsupportedOperationException();
	}

	/**
	 * If this network view supports creating snapshots using the 
	 * {@link CyNetworkView#createSnapshot()} method, then this method will
	 * return true if the state of the network has changed since last clearing the dirty flag.
	 * 
	 * If this network view does not support creating snapshots then this method will always return false.
	 * @param clear If true then the dirty flag will be set to false.
	 */
	default boolean isDirty(boolean clear) {
		return false;
	}
	
	/**
	 * Adds a CyNetworkViewListener (optional operation).
	 * @throws UnsupportedOperationException if this method
	 *   is not supported by this network view
	 */
	default void addNetworkViewListener(CyNetworkViewListener listener) { 
		 throw new UnsupportedOperationException();
	 }
	
	/**
	 * Removes a CyNetworkViewListener.
	 * @throws UnsupportedOperationException if this method
	 *   is not supported by this network view
	 */
	default void removeNetworkViewListener(CyNetworkViewListener listener) {
		 throw new UnsupportedOperationException();
	 }

}
