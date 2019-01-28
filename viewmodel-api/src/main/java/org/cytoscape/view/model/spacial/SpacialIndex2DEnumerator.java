package org.cytoscape.view.model.spacial;

public interface SpacialIndex2DEnumerator {

	boolean hasNext();
	
	long getNextExtents(float[] extents);
	
}
