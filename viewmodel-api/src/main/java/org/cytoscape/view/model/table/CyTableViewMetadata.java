package org.cytoscape.view.model.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.cytoscape.model.CyTableMetadata;

public class CyTableViewMetadata {
	
	private final long savedTableSUID;
	private final String tableNamespace;
	private final String rendererID;
	private final List<CyColumnViewMetadata> columnViews;

	private CyTableMetadata underlyingTable;
	
	
	public CyTableViewMetadata(long savedTableSUID, String tableNamespace, String rendererID, List<CyColumnViewMetadata> columnViews) {
		this.savedTableSUID = savedTableSUID;
		this.rendererID = rendererID;
		this.columnViews = new ArrayList<>(columnViews);
		this.tableNamespace = tableNamespace;
	}

	
	public long getSavedTableSUID() {
		return savedTableSUID;
	}

	public String getRendererID() {
		return rendererID;
	}

	public List<CyColumnViewMetadata> getColumnViews() {
		return Collections.unmodifiableList(columnViews);
	}
	
	public String getNamespace() {
		return tableNamespace;
	}

	
	/**
	 * @CyAPI.NoReference.Method
	 */
	public CyTableMetadata getUnderlyingTable() {
		return underlyingTable;
	}

	/**
	 * @CyAPI.NoReference.Method
	 */
	public void setUnderlyingTable(CyTableMetadata underlyingTable) {
		this.underlyingTable = underlyingTable;
	}
	
	
}
