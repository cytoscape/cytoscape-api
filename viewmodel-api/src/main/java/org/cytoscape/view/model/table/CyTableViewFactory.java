package org.cytoscape.view.model.table;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyTable;

public interface CyTableViewFactory {

	default CyTableView createTableView(CyTable table) {
		return createTableView(table, null);
	}
	
	CyTableView createTableView(CyTable table, Class<? extends CyIdentifiable> tableType);
	
}