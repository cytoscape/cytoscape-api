package org.cytoscape.view.vizmap.gui.editor;

/**
 * An enum describing the possible types of continuous editors.
 * @CyAPI.Enum.Class
 * @CyAPI.InModule vizmap-gui-api
 */
public enum ContinuousEditorType {
	/**
	 * Used for mapping continuous attribute values to
	 * discrete visual property types such as Node Shape.
	 */
	DISCRETE, 

	/**
	 * Used for mapping continuous attribute values to
	 * continuous visual property types such as Node Size.
	 */
	CONTINUOUS, 

	/**
	 * Used for mapping continuous attribute values to
	 * color visual property types such as Node Color.
	 */
	COLOR,
	;
}
