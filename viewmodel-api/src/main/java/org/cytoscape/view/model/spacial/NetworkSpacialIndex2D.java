package org.cytoscape.view.model.spacial;

import java.util.function.BooleanSupplier;

/**
 * A spacial index used to query for locations of Nodes and Edges in a 2D representation of a network.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface NetworkSpacialIndex2D extends SpacialIndex2D<Long> {

	
	public NodeSpacialIndex2DEnumerator queryAllNodes(BooleanSupplier isCancelled);
	
	public NodeSpacialIndex2DEnumerator queryOverlapNodes(float xMin, float yMin, float xMax, float yMax, BooleanSupplier isCancelled);
	
	
	public EdgeSpacialIndex2DEnumerator queryAllEdges(BooleanSupplier isCancelled);
	
	public EdgeSpacialIndex2DEnumerator queryOverlapEdges(float xMin, float yMin, float xMax, float yMax, BooleanSupplier isCancelled);
	
}
