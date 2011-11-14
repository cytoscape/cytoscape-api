package org.cytoscape.view.vizmap.gui;

import java.awt.Component;

import org.cytoscape.view.vizmap.VisualStyle;

/**
 * Default View Editor is the component to display available visual properties
 * and its default view for given Visual Style.
 * This component will be provided as a service.
 */
public interface DefaultViewEditor {

	/**
	 * For a given visual style name, return a component containing
	 * a graphical view of the specified visual style.
	 * 
	 * @param vs The {@link VisualStyle} to get the default view of. 
	 * @return a component containing a graphical view of the specified visual style.
	 */
	Component getDefaultView(VisualStyle vs);

	/**
	 * Show the default view editor in the specified parent component.
	 * 
	 * @param parent The component in which the default view should be shown.
	 */
	void showEditor(Component parent);
}
