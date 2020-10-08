package org.cytoscape.view.model.table;

import java.util.Set;

import org.cytoscape.model.CyTable;

/**
 * Basic access to table views in an instance of Cytoscape.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyTableViewManager {

	/**
	 * Returns a set of all the table views that are currently known to the manager.
	 */
	Set<CyTableView> getTableViewSet();
	
	/**
	 * Returns the table view associated with the given table, or null if no table view
	 * has been registered for the given table. 
	 * <br><br>
	 * Note: Apps should typically use {@link CyTableViewManager#getOrCreateTableView(CyTable)} instead.
	 */
	CyTableView getTableView(CyTable table);
	
	
	/**
	 * Adds a tableView to this manager. Note there can be at most one table view per table. 
	 * @param tableView
	 * @throws IllegalArgumentException If a table view has already been assigned for the given table.
	 */
	void addTableView(CyTableView tableView);
	
	/**
	 * Destroys a table view.
	 * @param view a non-null table view
	 */
	void destroyTableView(CyTableView tableView);
	
	/**
	 * @CyAPI.NoReference.Method Apps should not call this method. Resetting 
	 * the CyNetworkViewManager at the wrong time will put Cytoscape into 
	 * an inconsistent internal state.
	 */
	void reset();
}