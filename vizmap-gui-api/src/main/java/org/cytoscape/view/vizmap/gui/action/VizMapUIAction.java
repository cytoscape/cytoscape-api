package org.cytoscape.view.vizmap.gui.action;

import javax.swing.JMenuItem;

/**
 * @CyAPI.Spi.Interface
 */
public interface VizMapUIAction {
	public JMenuItem getMenu();
	public void execute();
}
