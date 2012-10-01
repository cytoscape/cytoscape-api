package org.cytoscape.view.vizmap.gui.editor;

import javax.swing.ImageIcon;

/**
 * A basic interface used to display continuous mapping editors.
 * @param <K> The numeric type of the attribute.
 * @param <V> The generic type of the visual property the attribute is being mapped to. 
 * @CyAPI.Spi.Interface
 */
public interface ContinuousMappingEditor<K extends Number, V> {

	/**
	 * Returns an icon representing this editor for use in a user interface.
	 * @param width The width of the icon. 
	 * @param height The height of the icon. 
	 * @param detail Whether to include high detail or not.
	 * @return an icon representing this editor for use in a user interface.
	 */
	public abstract ImageIcon drawIcon(int width, int height, boolean detail);

}
