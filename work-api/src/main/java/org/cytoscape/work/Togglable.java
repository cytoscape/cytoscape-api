package org.cytoscape.work;

/**
 * This interface is meant to be implemented by task factories that provides tasks which switches something on and off.
 * Useful when you want your task factory to be displayed as a toggle button or a check box menu item, for instance.
 * Notice that this is just a marker interface, which means you still have to implement the <code>isOn()</code> method
 * in your Task Factory implementation (e.g. {@link TaskFactory#isOn()}).
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 * 
 * @since 3.9
 */
public interface Togglable {
	
}
