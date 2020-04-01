package org.cytoscape.view.model;

import java.util.Set;

import org.cytoscape.model.CyTable;

public interface CyTableViewManager {

	Set<CyTableView> getTableViewSet();
	
	// NO support for multiple table views!
	CyTableView getTableView(CyTable table);
	
	void addTableView(CyTableView tableView);

	
	// ?
	void destroyTableView(CyTableView tableView);
}
