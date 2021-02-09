package org.cytoscape.view.model.spacial;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.View;

public interface NodeSpacialIndex2DEnumerator extends SpacialIndex2DEnumerator<Long> {

	public View<CyNode> nextNode();
	
	public View<CyNode> nextNodeExtents(float[] extents);
	
}
