package org.cytoscape.application.swing;


/**
 * #ASKMIKE needs class descript and method comments.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractToolBarComponent implements ToolBarComponent{

	protected float toolbarGravity = 100.0f;
	protected boolean isEnabled = true;
	
	public void setToolBarGravity(float gravity) {
		toolbarGravity = gravity;
	}

	public float getToolBarGravity() {
		return toolbarGravity;
	}
}
