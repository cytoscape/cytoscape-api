package org.cytoscape.view.vizmap.events.table;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.vizmap.TableVisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;

public class ColumnAssociatedVisualStyleSetEvent extends AbstractCyEvent<TableVisualMappingManager> {

	private final VisualStyle networkVisualStyle;
	private final VisualStyle columnVisualStyle; // may be null if VS was removed
	private final String columnName;
	private final Class<? extends CyIdentifiable> tableType;
	
	
	public ColumnAssociatedVisualStyleSetEvent(
			TableVisualMappingManager source, 
			VisualStyle networkVisualStyle, 
			VisualStyle columnVisualStyle, 
			String columnName, Class<? extends CyIdentifiable> tableType
	) {
		super(source, ColumnAssociatedVisualStyleSetListener.class);
		this.networkVisualStyle = networkVisualStyle;
		this.columnVisualStyle = columnVisualStyle;
		this.columnName = columnName;
		this.tableType = tableType;
	}


	public VisualStyle getNetworkVisualStyle() {
		return networkVisualStyle;
	}

	public VisualStyle getColumnVisualStyle() {
		return columnVisualStyle;
	}

	public String getColumnName() {
		return columnName;
	}

	public Class<? extends CyIdentifiable> getTableType() {
		return tableType;
	}
	
}
