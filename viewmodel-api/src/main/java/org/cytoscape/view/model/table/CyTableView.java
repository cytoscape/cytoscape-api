package org.cytoscape.view.model.table;

import java.util.Collection;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyDisposable;
import org.cytoscape.model.CyIdentifiable;
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
	
	Collection<View<CyColumn>> getColumnViews();
	
	View<CyRow> getRowView(CyRow row);
	
	
	/**
	 * Returns CyNode, CyEdge or CyNetwork typically. May return null for unassigned tables.
	 * @return
	 */
	Class<? extends CyIdentifiable> getTableType();
	
	// MKTODO this seems like something that is used a lot by the table browser. It could be a VP or it could be a method.
//	View<CyRow> getCurrentRow();
	
	String getRendererId();
	
	<T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);
	
}
