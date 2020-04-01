package org.cytoscape.view.model;

import java.util.Collection;
import java.util.List;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

public interface CyTableView extends View<CyTable> {

	
	CyColumnView getColumnView(CyColumn column);
	
	// Convenience method... makes it a bit easier to work with namespaces
	Collection<CyColumnView> getColumnViews(String namespace);
	
	View<CyRow> getRowView(CyRow row);
	
	List<CyColumnView> getColumnViews();
	
	List<CyRow> getRowViews();
	
	
	String getRendererId();
	
}
