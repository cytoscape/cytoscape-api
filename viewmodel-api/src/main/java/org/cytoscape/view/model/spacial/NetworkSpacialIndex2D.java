package org.cytoscape.view.model.spacial;

/**
 * A spacial index used to query for locations of Nodes and Edges in a 2D representation of a network.
 */
public interface NetworkSpacialIndex2D extends SpacialIndex2D<Long> {

	
	public NodeSpacialIndex2DEnumerator queryAllNodes();
	
	public NodeSpacialIndex2DEnumerator queryOverlapNodes(float xMin, float yMin, float xMax, float yMax);
	
	
	public EdgeSpacialIndex2DEnumerator queryAllEdges();
	
	public EdgeSpacialIndex2DEnumerator queryOverlapEdges(float xMin, float yMin, float xMax, float yMax);
	
}
