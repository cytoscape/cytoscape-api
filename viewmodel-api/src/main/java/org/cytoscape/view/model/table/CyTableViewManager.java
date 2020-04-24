package org.cytoscape.view.model.table;

import java.util.Set;

import org.cytoscape.model.CyTable;

// MKTODO make sure these are getting called in the right places
public interface CyTableViewManager {

	
	Set<CyTableView> getTableViewSet();
	
	// NO support for multiple table views!
	CyTableView getTableView(CyTable table);
	
	
	void addTableView(CyTableView tableView);
	
	
	void destroyTableView(CyTableView tableView);

	
	/**
	 * @CyAPI.NoReference.Method Apps should not call this method. Resetting 
	 * the CyNetworkViewManager at the wrong time will put Cytoscape into 
	 * an inconsistent internal state.
	 */
	void reset();
}