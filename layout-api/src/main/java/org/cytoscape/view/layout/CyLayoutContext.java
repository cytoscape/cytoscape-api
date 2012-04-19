package org.cytoscape.view.layout;


/**
 * Define in what situation this layout algorithm can be used.
 *
 */
public interface CyLayoutContext {
	/**
	 * Returns whether this layout algorithm can be applied to selected nodes or not.
	 *  
	 * @return true if this layout algorithm can be used only for selected nodes
	 */
	boolean useOnlySelectedNodes();
}
