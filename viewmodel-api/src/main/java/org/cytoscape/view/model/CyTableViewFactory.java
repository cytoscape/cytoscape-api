package org.cytoscape.view.model;

import org.cytoscape.model.CyTable;

public interface CyTableViewFactory {

	CyTableView createTableView(CyTable table);
	
}
