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
	
	
//	/**
//	 * Returns a set of all the table views that are currently known to the manager.
//	 */
//	Set<CyTableView> getTableViewSet();
//	
//	/**
//	 * Returns the table view associated with the given table, or null if no table view
//	 * has been registered for the given table. 
//	 * <br><br>
//	 * Note: Apps should typically use {@link CyTableViewManager#getOrCreateTableView(CyTable)} instead.
//	 */
//	CyTableView getTableView(CyTable table);
//	
//	
//	/**
//	 * Adds a tableView to this manager. 
//	 * There can be at most one table view per table, if a table view already exists for the given table it will be
//	 * replaced with the given tabel view.
//	 * @param tableView
//	 * @throws IllegalArgumentException If the table view's underlying data table has not been registered with the table manager.
//	 */
//	void addTableView(CyTableView tableView);
//	
//	/**
//	 * Destroys a table view.
//	 * @param view a non-null table view
//	 */
//	void destroyTableView(CyTableView tableView);
//	
//	/**
//	 * @CyAPI.NoReference.Method Apps should not call this method. Resetting 
//	 * the CyNetworkViewManager at the wrong time will put Cytoscape into 
//	 * an inconsistent internal state.
//	 */
//	void reset();
}