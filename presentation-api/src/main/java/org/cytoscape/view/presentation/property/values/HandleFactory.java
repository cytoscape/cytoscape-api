package org.cytoscape.view.presentation.property.values;


/**
 * Factory of the handle object.
 *
 */
public interface HandleFactory {
	
	/**
	 * Creates a new instance of the Handle.
	 * 
	 * @param x x position of the handle (absolute position)
	 * @param y y position of the handle (absolute position)
	 * 
	 * @return instance of new handle.
	 */
	Handle createHandle(double x, double y);
}
