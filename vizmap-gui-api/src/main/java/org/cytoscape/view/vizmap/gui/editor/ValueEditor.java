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
	 * 
	 * @return
	 */
	<S extends V> V showEditor(Component parent, S initialValue);
	
	Class<V> getType();

}
