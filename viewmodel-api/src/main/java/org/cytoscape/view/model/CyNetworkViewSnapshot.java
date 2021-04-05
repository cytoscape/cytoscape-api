package org.cytoscape.view.model;

import java.util.Collection;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.spacial.NetworkSpacialIndex2D;


/**
 * <p>
 * An immutable snapshot of a {@link CyNetworkView} created using the 
 * {@link CyNetworkView#createSnapshot()} method. The snapshot represents
 * the state of the CyNetworkView at the moment that createSnapshot() was called.
 * Any subsequent updates to the mutable CyNetworkView will not be reflected
 * in the snapshot, making the snapshot safe to read without locks.
 * The intention is that a renderer can create a snapshot at the start of
 * rendering a frame and then safely read from the snapshot without the threat of
 * other threads updating the CyNetworkView while the frame is being rendered.
 * </p> 
 * <p>
 * Creating a snapshot is intended to be relatively fast and not use a significant
 * amount of memory. CyNetworkViewSnapshot also provides several methods for querying the state of the
 * snapshot that are not available on mutable CyNetworkView objects.
 * </p>
 * <p>
 * The snapshot cannot be updated in any way. Calling any 'set' method such as
 * {@link CyNetworkView#setVisualProperty(VisualProperty, Object)} will cause
 * an UnsupportedOperationException to be thrown. Also it is not possible to directly
 * access the underlying model objects using the getModel() method because the underlying
 * model objects are mutable. To acquire the mutable version of a View object use the following
 * example:
 * </p>
 * 
 * <pre>
 * CyNetworkView mutableNetworkView = snapshot.getMutableNetworkView();
 * View&lt;CyNode&gt; mutableNodeView = mutableNetworkView.getNodeView(snapshotNodeView.getSUID());
 * if(mutableNodeView != null) {
 *     doSomethingWith(mutableNodeView);
 * }
 * </pre>
 * 
 * @see CyNetworkView#createSnapshot()
 * @see CyNetworkView#isDirty() 
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.8
 */
public interface CyNetworkViewSnapshot extends CyNetworkView {
	
	/**
	 * Returns the mutable CyNetworkView that was used to create this snapshot.
	 */
	CyNetworkView getMutableNetworkView();
	
	/**
	 * Returns an immutable NetworkSpacialIndex2D object that can be used to query the bounds
	 * of nodes in the network view, or null if the SpacialIndex has been turned off.
	 * @see CyNetworkViewFactoryConfig#setEnableSpacialIndex2D(boolean)
	 */
	NetworkSpacialIndex2D getSpacialIndex2D();
	
	/**
	 * Returns the immutable node View for the given view SUID.
	 * @param suid SUID of the node view
	 * @return View for the given node object.
	 */
	View<CyNode> getNodeView(long suid);
	
	/**
	 * Returns the mutable node View for the given view SUID, or null if the node
	 * view no longer exists in the mutable network view.
	 * @param suid SUID of the node view
	 * @return View for the given node object, or null
	 */
	View<CyNode> getMutableNodeView(long suid);
	
	/**
	 * Returns the immutable edge View for the given view SUID.
	 * @param suid SUID of the edge view
	 * @return View for the given edge object.
	 */
	View<CyEdge> getEdgeView(long suid);

	/**
	 * Returns the mutable edge View for the given view SUID, or null if the edge
	 * view no longer exists in the mutable network view.
	 * @param suid SUID of the edge view
	 * @return View for the given edge object.
	 */
	View<CyEdge> getMutableEdgeView(long suid);

	
	/**
	 * Returns the number of nodes in the network view.
	 */
	int getNodeCount();

	/**
	 * Returns the number of edges in the network view.
	 */
	int getEdgeCount();
	
	/**
	 * Returns nodes that were configured to have their VisualProperties tracked using 
	 * {@link CyNetworkViewFactoryConfig#addTrackedVisualProperty(Object, VisualProperty, java.util.function.Predicate)}
	 * and pass the predicate.
	 * <br><br>
	 * If using the default configuration then selected nodes can be retrieved like in this example:
	 * <pre>
	 * Collection&lt;View&lt;CyNode&gt;&gt; selectedNodes = netViewSnapshot.getTrackedNodes(CyNetworkViewConfig.SELECTED_NODES);
	 * </pre>
	 * 
	 * @param key The same key object that was passed to CyNetworkViewConfig.addTrackedVisualProperty()
	 */
	Collection<View<CyNode>> getTrackedNodes(Object key);
	
	/**
	 * Returns the number of nodes that are being tracked using the given key. This is the number of nodes that pass
	 * the predicate that was given to  
	 * {@link CyNetworkViewFactoryConfig#addTrackedVisualProperty(Object, VisualProperty, java.util.function.Predicate)}.
	 */
	int getTrackedNodeCount(Object key);
	
	/**
	 * Returns edges that were configured to have their VisualProperties tracked using 
	 * {@link CyNetworkViewFactoryConfig#addTrackedVisualProperty(Object, VisualProperty, java.util.function.Predicate)}
	 * and pass the predicate.
	 * <br><br>
	 * If using the default configuration then selected edges can be retrieved like in this example:
	 * <pre>
	 * Collection&lt;View&lt;CyEdge&gt;&gt; selectedEdges = netViewSnapshot.getTrackedEdges(CyNetworkViewConfig.SELECTED_EDGES);
	 * </pre>
	 * 
	 * @param key The same key object that was passed to CyNetworkViewConfig.addTrackedVisualProperty()
	 */
	Collection<View<CyEdge>> getTrackedEdges(Object key);
	
	/**
	 * Returns the number of edges that are being tracked using the given key. This is the number of edges that pass
	 * the predicate that was given to  
	 * {@link CyNetworkViewFactoryConfig#addTrackedVisualProperty(Object, VisualProperty, java.util.function.Predicate)}.
	 */
	int getTrackedEdgeCount(Object key);
	
	/**
	 * Returns an Iterable that contains the edges that are adjacent (connected) to the given node.
	 */
	Iterable<View<CyEdge>> getAdjacentEdgeIterable(View<CyNode> node);
	
	/**
	 * Returns an Iterable that contains the edges that are adjacent (connected) to the given node.
	 */
	Iterable<View<CyEdge>> getAdjacentEdgeIterable(long nodeSuid);
	
	/**
	 * Returns an object that provides more information about the given edge.
	 */
	SnapshotEdgeInfo getEdgeInfo(View<CyEdge> edge);
	
	/**
	 * Returns an object that provides more information about the given node.
	 */
	SnapshotNodeInfo getNodeInfo(View<CyNode> node);
	
	/**
	 * Returns true if the only thing that changed since the last time 
	 * {@link CyNetworkView#createSnapshot()} was called was the selection increased.
	 */
	boolean isSelectionIncreased();
	
	/**
	 * Returns the default value of the VisualProperty that was set 
	 * using {@link CyNetworkView#setViewDefault(VisualProperty, Object)}.
	 */
	<T> T getViewDefault(VisualProperty<T> vp);

	/**
	 * Returns true if nodes were configured to have their VisualProperties tracked using the given key.
	 * 
	 * @param key The same key object that was passed to CyNetworkViewConfig.addTrackedVisualProperty()
	 */
	boolean isTrackedNodeKey(Object key);
	
	/**
	 * Returns true if edges were configured to have their VisualProperties tracked using the given key.
	 * 
	 * @param key The same key object that was passed to CyNetworkViewConfig.addTrackedVisualProperty()
	 */
	boolean isTrackedEdgeKey(Object key);
	
}
