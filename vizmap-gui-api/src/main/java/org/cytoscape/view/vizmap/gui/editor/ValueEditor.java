package org.cytoscape.view.vizmap.gui.editor;

import java.awt.Component;

/**
 * Provides a GUI Editor for a data type.
 *
 * @param <V> target data type for this editor.
 * 
 */
public interface ValueEditor<V> {
	
	/**
	 * Display the editor and get a new value.
	 * @param parent the parent Component of this editor.
	 * @param initialValue the initial value for the value editor dialogue.
	 * 
	 * @return the value generated from the value editor dialogue or null.
	 */
	<S extends V> V showEditor(Component parent, S initialValue);
	
	/**
	 * @return the type of value returned by the value editor.
	 */
	Class<V> getType();

}
