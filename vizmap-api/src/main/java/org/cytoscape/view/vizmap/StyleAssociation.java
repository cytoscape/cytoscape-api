package org.cytoscape.view.vizmap;

import org.cytoscape.model.CyIdentifiable;

public record StyleAssociation(
		VisualStyle networkVisualStyle, 
		Class<? extends CyIdentifiable> tableType, 
		String colName, 
		VisualStyle columnVisualStyle
	) {
}
