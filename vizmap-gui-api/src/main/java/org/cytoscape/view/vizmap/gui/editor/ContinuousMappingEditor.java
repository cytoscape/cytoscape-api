package org.cytoscape.view.vizmap.gui.editor;

import javax.swing.ImageIcon;

public interface ContinuousMappingEditor<K extends Number, V> {

	public abstract ImageIcon drawIcon(int width, int height, boolean detail);

}