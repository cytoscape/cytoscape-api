package org.cytoscape.application.swing;


/**
 * An abstract, convenience implementation of ToolBarComponent.  
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractToolBarComponent implements ToolBarComponent{

	/**
	 * The gravity for this toolbar component.
	 */
	protected float toolbarGravity = 100.0f;

	/**
	 * Whether the toolbar component is enabled or not.
	 */
	protected boolean isEnabled = true;
	
	/**
	 * Sets the gravity for this toolbar component.
	 * @param gravity The gravity for this toolbar component.
	 */
	public void setToolBarGravity(float gravity) {
		toolbarGravity = gravity;
	}

	/**
	 * Returns the gravity value for this toolbar component.
	 * @return the gravity value for this toolbar component.
	 */
	public float getToolBarGravity() {
		return toolbarGravity;
	}
}
