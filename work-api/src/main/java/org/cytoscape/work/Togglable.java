package org.cytoscape.work;

/**
 * This interface is meant to be implemented by task factories that provides tasks which switches something on and off.
 * Useful when you want your task factory to be displayed as a toggle button or a check box menu item, for instance.
 */
public interface Togglable {
	
	/**
	 * Returns <code>true</code> if the function provided by a {@link TaskFactory} is on
	 * and <code>false</code> if it is off.
	 */
	boolean isOn();

}
