package org.cytoscape.view.model.spacial;

import java.util.function.BooleanSupplier;

/**
 * Stores the location and rectangular boundary of nodes in a 2D representation of a network. 
 * Also provides methods for querying edges that are adjacent to visible nodes.
 * 
 * All methods support cancellation.
 * 
 * @see BasicVisualLexicon.NODE_Z_LOCATION
 * @see BasicVisualLexicon.EDGE_Z_ORDER
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface NetworkSpacialIndex2D extends SpacialIndex2D<Long> {

	/**
	 * Returns an enumerator of all the nodes in the network. 
	 * The enumerator will return nodes in the order given by the {@link BasicVisualLexicon.NODE_Z_LOCATION} visual property.
	 * 
	 * @see BasicVisualLexicon.NODE_Z_LOCATION
	 * @param isCancelled (May be null). An optional boolean returning function that will cancel a long running query if it returns true.
	 * The isCancelled function will typically be called several times depending on how many nodes are in the network.
	 * If isCancelled returns true then this method will return null.
	 * @return null if isCancelled returns null during the query
	 */
	public NodeSpacialIndex2DEnumerator queryAllNodes(BooleanSupplier isCancelled);
	
	
	/**
	 * Returns an enumerator of all the nodes that are contained within or intersecting the given rectangle. 
	 * The enumerator will return nodes in the order given by the {@link BasicVisualLexicon.NODE_Z_LOCATION} visual property.
	 * 
	 * @see BasicVisualLexicon.NODE_Z_LOCATION
	 * @param isCancelled (May be null). An optional boolean returning function that will cancel a long running query if it returns true.
	 * The isCancelled function will typically be called several times depending on how many nodes are in the network.
	 * If isCancelled returns true then this method will return null.
	 * @return null if isCancelled returns null during the query
	 */
	public NodeSpacialIndex2DEnumerator queryOverlapNodes(float xMin, float yMin, float xMax, float yMax, BooleanSupplier isCancelled);
	
	
	/**
	 * Returns an enumerator of all edges in the network. 
	 * The enumerator will return edges in the order given by the {@link BasicVisualLexicon.EDGE_Z_ORDER} visual property.
	 * 
	 * @see BasicVisualLexicon.EDGE_Z_ORDER
	 * @param isCancelled (May be null). An optional boolean returning function that will cancel a long running query if it returns true.
	 * The isCancelled function will typically be called several times depending on how many edges are in the network.
	 * If isCancelled returns true then this method will return null.
	 * @return null if isCancelled returns null during the query
	 */ 
	public EdgeSpacialIndex2DEnumerator queryAllEdges(BooleanSupplier isCancelled);
	
	
	/**
	 * Returns an enumerator of all edges that are adjacent to nodes that are contained within or intersecting the given rectangle.
	 * The enumerator will return edges in the order given by the {@link BasicVisualLexicon.EDGE_Z_ORDER} visual property.
	 * 
	 * @see BasicVisualLexicon.EDGE_Z_ORDER
	 * @param isCancelled (May be null). An optional boolean returning function that will cancel a long running query if it returns true.
	 * The isCancelled function will typically be called several times depending on how many edges are in the network.
	 * If isCancelled returns true then this method will return null.
	 * @return null if isCancelled returns null during the query
	 */ 
	public EdgeSpacialIndex2DEnumerator queryOverlapEdges(float xMin, float yMin, float xMax, float yMax, BooleanSupplier isCancelled);
	
}
