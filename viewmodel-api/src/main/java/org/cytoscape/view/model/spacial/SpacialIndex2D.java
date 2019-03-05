package org.cytoscape.view.model.spacial;

public interface SpacialIndex2D<T> {

	final int X_MIN = 0, Y_MIN = 1, X_MAX = 2, Y_MAX = 3;
	
	
	void getMBR(float[] extents);
	
	/**
	 * This data structure stores floats, this is just a convenience method that takes a double[] instead of a float[].
	 */
	void getMBR(double[] extents);

	boolean exists(T suid);
	
	boolean get(T suid, float[] extents);
	
	/**
	 * This data structure stores floats, this is just a convenience method that takes a double[] instead of a float[].
	 */
	boolean get(T suid, double[] extents);
	
	int size();
	
	
	SpacialIndex2DEnumerator<T> queryOverlap(float xMin, float yMin, float xMax, float yMax);
	
	SpacialIndex2DEnumerator<T> queryAll();
	
	
	void put(T suid, float xMin, float yMin, float xMax, float yMax);
	
	void delete(T suid);
	
}
