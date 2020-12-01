package org.cytoscape.view.model.table;

import java.util.Set;

import org.cytoscape.model.CyTable;

/**
 * Basic access to table views in an instance of Cytoscape.
 * There can only be one table view per table, and the table view
 * is created automatically on demand.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyTableViewManager {

	Set<CyTableView> getTableViewSet();

	/**
	 * Returns the table view for the table.
	 */
	CyTableView getTableView(CyTable table);
	
	/**
	 * Sets the table view for the given table.
	 * Note: you cannot replace the table view for the default node/edge/network tables.
	 */
	void setTableView(CyTableView tableView);
	
	/**
	 * Destroys the given table view.
	 */
	void destroyTableView(CyTableView view);
	
	/**
	 * @CyAPI.NoReference.Method Apps should not call this method. Resetting 
	 * the CyNetworkViewManager at the wrong time will put Cytoscape into 
	 * an inconsistent internal state.
	 */
	void reset();
}