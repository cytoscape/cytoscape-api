package org.cytoscape.view.model.spacial;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.View;

public interface EdgeSpacialIndex2DEnumerator {

	int size();
	
	boolean hasNext();

	View<CyEdge> nextEdge();
	
	View<CyEdge> nextEdgeWithNodeExtents(float[] sourceExtents, float[] targetExtents, View<CyNode>[] nodes);
	
}
