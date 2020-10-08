package org.cytoscape.view.model.table;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyTable;

public interface CyTableViewFactory {

	CyTableView createTableView(CyTable table);
	
	CyTableView createTableView(CyTable table, Class<? extends CyIdentifiable> tableType);
	
}