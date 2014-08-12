package org.cytoscape.view.presentation.customgraphics;

import java.util.Map;

import org.cytoscape.view.presentation.property.values.VisualPropertyValue;

public interface CyCustomGraphics2<T extends CustomGraphicLayer> extends CyCustomGraphics<T>, VisualPropertyValue {

	Map<String, Object> getProperties();
	
}
