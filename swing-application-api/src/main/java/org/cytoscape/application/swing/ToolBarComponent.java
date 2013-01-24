package org.cytoscape.application.swing;

import java.awt.Component;
import javax.swing.Icon;


/**
 * An interface that allows a component to be registered as a service
 * that will then be added to the ToolBar.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface ToolBarComponent {

	/**
	 * Returns the gravity used to place this component in the toolbar.
	 * @return The gravity used to place this component in the toolbar.
	 */
	public float getToolBarGravity();
	
	/**
	 * Returns the Component to be added to the ToolBar. 
	 * @return The Component to be added to the ToolBar. 
	 */
	public Component getComponent();
}