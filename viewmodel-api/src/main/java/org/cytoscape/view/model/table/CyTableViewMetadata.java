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
	private final Map<String,String> bypassValues;
	private final List<CyColumnViewMetadata> columnViews;
	private final List<CyRowViewMetadata> rowViews;
	
	private final Class<?> primaryKeyType;
	private final Class<?> primaryKeyListElementType;

	private CyTableMetadata underlyingTable;
	
	
	public CyTableViewMetadata(
			long savedTableSUID, 
			String tableNamespace, 
			String rendererID, 
			Map<String,String> bypassValues, 
			List<CyColumnViewMetadata> columnViews,
			List<CyRowViewMetadata> rowViews,
			Class<?> primaryKeyType,
			Class<?> primaryKeyListElementType
	) {
		this.savedTableSUID = savedTableSUID;
		this.rendererID = rendererID;
		this.columnViews = new ArrayList<>(columnViews);
		this.bypassValues = bypassValues;
		this.tableNamespace = tableNamespace;
		this.rowViews = rowViews;
		this.primaryKeyType = primaryKeyType;
		this.primaryKeyListElementType = primaryKeyListElementType;
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
	
	public List<CyRowViewMetadata> getRowViews() {
		return Collections.unmodifiableList(rowViews);
	}
	
	public String getNamespace() {
		return tableNamespace;
	}
	
	public Map<String,String> getBypassValues() {
		return bypassValues;
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

	public Class<?> getPrimaryKeyType() {
		return primaryKeyType;
	}

	public Class<?> getPrimaryKeyListElementType() {
		return primaryKeyListElementType;
	}
	
	
}
