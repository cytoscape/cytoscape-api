package org.cytoscape.view.model.spacial;

public interface SpacialIndex2DEnumerator<T> {

	int size();
	
	boolean hasNext();
	
	T nextExtents(float[] extents);
	
	default T next() {
		return nextExtents(null);
	}
	
}
