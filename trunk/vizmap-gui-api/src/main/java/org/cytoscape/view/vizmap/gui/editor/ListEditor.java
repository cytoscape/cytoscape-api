package org.cytoscape.view.vizmap.gui.editor;


/**
 * Generic editor for discrete values, such as Shape, Line Type, etc.
 * 
 * @CyAPI.Spi.Interface
 *
 */
public interface ListEditor {
	
	/**
	 * Type of object managed by this editor.
	 * 
	 * @return type of value in this list editor.
	 * 
	 */
	Class<?> getTargetObjectType();
}
