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
	
	default View<CyColumn> getColumnView(String fullyQualifiedName) {
		CyColumn col = getModel().getColumn(fullyQualifiedName);
		if(col != null) {
			return getColumnView(col);
		}
		return null;
	}
	
	View<CyColumn> getColumnView(long viewSuid);
	
	/**
	 * Returns all column views, in the same order as {@link CyTable#getColumns()}.
	 * @see CyTable#getColumns()
	 */
	Collection<View<CyColumn>> getColumnViews();
	
	View<CyRow> getRowView(CyRow row);
	
	
	String getRendererId();
	
	<T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);

	Collection<View<CyRow>> getRowViews();
	
}
