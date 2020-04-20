package org.cytoscape.view.model.table;

import java.util.Collection;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyDisposable;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;

public interface CyTableView extends View<CyTable>, CyDisposable {

	View<CyColumn> getColumnView(CyColumn column);
	
	Collection<View<CyColumn>> getColumnViews();
	
	View<CyRow> getRowView(CyRow row);
	
	// MKTODO this seems like something that is used a lot by the table browser. It could be a VP or it could be a method.
//	View<CyRow> getCurrentRow();
	
	String getRendererId();
	
	<T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);
	
}
