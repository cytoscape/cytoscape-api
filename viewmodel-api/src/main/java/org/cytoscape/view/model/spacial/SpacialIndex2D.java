package org.cytoscape.view.model.spacial;

public interface SpacialIndex2D {

	final int X_MIN = 0, Y_MIN = 1, X_MAX = 2, Y_MAX = 3;
	
	
	void getMBR(float[] extentsArr);

	boolean exists(long suid);

	SpacialIndex2DEnumerator queryOverlap(float xMin, float yMin, float xMax, float yMax);
	
}
