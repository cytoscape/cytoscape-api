package org.cytoscape.view.model.table;

import java.util.Collection;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyDisposable;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;


/**
 * Additional methods for table views.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface CyTableView extends View<CyTable>, CyDisposable {

	/**
	 * Returns the View for a specified column.
	 * @param column CyColumn object representing the column
	 * @return The column View, or null if the column isn't in this table.
	 */
	View<CyColumn> getColumnView(CyColumn column);
	
	/**
	 * Returns the View for a specified column.
	 * @param fullyQualifiedName The name of the column (including the namespace if present)
	 * @return The column View, or null if the column isn't in this table.
	 */
	default View<CyColumn> getColumnView(String fullyQualifiedName) {
		CyColumn col = getModel().getColumn(fullyQualifiedName);
		if(col != null) {
			return getColumnView(col);
		}
		return null;
	}
	
	/**
	 * Returns the View for the specified column using its view SUID as key.
	 * 
	 * @param viewSuid The SUID of the column view. Note this is not the SUID of the underlying CyColumn object, its the SUID of the View&lt;CyColumn&gt;
	 * @return The column View, or null if the column isn't in this table.
	 */
	View<CyColumn> getColumnView(long viewSuid);
	
	/**
	 * Returns all column views in this table view, in the same order as {@link CyTable#getColumns()}.
	 * @see CyTable#getColumns()
	 */
	Collection<View<CyColumn>> getColumnViews();
	
	/**
	 * Returns the View for the specified row.
	 * @param row CyRow object representing the column.
	 * @return The row View, or null if the row isn't in this table.
	 */
	View<CyRow> getRowView(CyRow row);
	
	/**
	 * Returns all row views in the table view.
	 */
	Collection<View<CyRow>> getRowViews();
	
	/**
	 * Returns the ID of the renderer that must be used to render this view.
	 * 
	 * @see org.cytoscape.application.TableViewRenderer#getId()
	 */
	String getRendererId();
	
	/**
	 * Sets the default value to be used for the specified visual property.
	 * @param <T> The type of the visual property value. 
	 * @param <V> The default value for the visual property, which must extend T. 
	 * @param vp The visual property whose default value we're specifying.
	 * @param defaultValue The default value to be used for this visual property for this view. 
	 */
	<T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);
	
}
