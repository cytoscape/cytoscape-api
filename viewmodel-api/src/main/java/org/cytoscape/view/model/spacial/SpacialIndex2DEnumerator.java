package org.cytoscape.view.model.spacial;

public interface SpacialIndex2DEnumerator {

	int size();
	
	boolean hasNext();
	
	long nextExtents(float[] extents);
	
}
