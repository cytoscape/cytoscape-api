package org.cytoscape.view.model.table;

import org.cytoscape.model.CyTable;

/**
 * Factory for {@link CyTableView} objects.
 * Modules which need to create view models should import this as a service.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface CyTableViewFactory {

	/** 
	 * Create a {@link CyTableView} from a {@link CyTable} object. 
	 * This method only creates a CyTableView instance and does nothing with respect to visual
	 * style, layout, or {@link CyTableViewManager}. 
	 * @param table CyTable for which the CyTableView is to be created
	 * @return the view model for the table data model
	 */
	CyTableView createTableView(CyTable table);
	
}