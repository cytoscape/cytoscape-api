package org.cytoscape.view.model.table;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyDisposable;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;

public interface CyTableView extends View<CyTable>, CyDisposable {

	View<CyColumn> getColumnView(CyColumn column);
	
	View<CyRow> getRowView(CyRow row);
	
	String getRendererId();
	
	<T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);
	
}
