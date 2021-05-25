package org.cytoscape.view.model.spacial;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.View;

/**
 * Enumerator returned when querying a {@link NetworkSpacialIndex2D}, used to iterate over the results of the query.
 * <br><br>
 * Example usage:
 * 
 * <pre>
 * NetworkSpacialIndex2D spacialIndex = networkViewSnapshot.getSpacialIndex2D();
 * EdgeSpacialIndex2DEnumerator enumerator = spacialIndex.queryAllEdges(null);
 * while(enumerator.hasNext()) {
 *     View&lt;CyEdge&gt; nodeView = enumerator.nextEdge();
 * }
 * </pre>
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface EdgeSpacialIndex2DEnumerator {

	/**
	 * Number of edges in the enumerator.
	 */
	int size();

	/**
	 * Returns true if this enumerator contains more edges.
	 */
	boolean hasNext();

	/**
	 * Returns the next edge.
	 */
	View<CyEdge> nextEdge();
	
	/**
	 * Returns the next edge.
	 * 
	 * @param sourceExtents Output parameter that will be loaded with the extents of the edge's source node. 
	 * Must have size at least 4. May be null.
	 * @param targetExtents Output parameter that will be loaded with the extents of the edge's target node. 
	 * Must have size at least 4. May be null.
	 * @param nodes Output parameter that will be loaded with the edge's source and target nodes. 
	 * Must have size at least 2. May be null.
	 * 
	 * @see SpacialIndex2D#X_MIN
	 * @see SpacialIndex2D#X_MAX
	 * @see SpacialIndex2D#Y_MIN
	 * @see SpacialIndex2D#Y_MAX
	 */
	View<CyEdge> nextEdgeWithNodeExtents(float[] sourceExtents, float[] targetExtents, View<CyNode>[] nodes);
	
}
