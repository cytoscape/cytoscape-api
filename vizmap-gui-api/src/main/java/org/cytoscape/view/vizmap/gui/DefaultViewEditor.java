package org.cytoscape.view.vizmap.gui;

import java.awt.Component;

import org.cytoscape.view.vizmap.VisualStyle;

/**
 * Default View Editor is the component to display available visual properties
 * and its default view for given Visual Style.
 * 
 * This component will be provided as a service.
 * 
 * @author kono
 * @since Cytoscape 3.0
 * 
 */
public interface DefaultViewEditor {

	/**
	 * For a given visual style name, returns a graphics of default.
	 * 
	 * @param vs The {@link VisualStyle} to get the default view of. 
	 * @return the default graphics Component. #ASKMIKE
	 */
	public Component getDefaultView(VisualStyle vs);
	
	public void showEditor(Component parent);
}