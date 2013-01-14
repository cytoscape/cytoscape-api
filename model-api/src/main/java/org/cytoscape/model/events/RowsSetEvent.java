package org.cytoscape.model.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyTable;

/**
 * This event signals that rows have been set.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class RowsSetEvent extends AbstractCyPayloadEvent<CyTable, RowSetRecord> {

	private Map<String,  Collection<RowSetRecord>> columnNameToRecordMap;
	
	/**
	 * Constructs Event.
	 * @param source the table in which the rows have been set.
	 * @param rows the rows that have been set as a Collection of type {@link RowSetRecord}.
	 */
	public RowsSetEvent(CyTable source, Collection<RowSetRecord> rows) {
		super(source, RowsSetListener.class, rows);
	}
	
	/**
	 * Returns true if any of the changed records' column name is colName
	 * @param colName Name of the column.
	 * @return
	 */
    public boolean containsColumn(String colName) {
        return getMap().containsKey(colName);
    }

    /**
     * Returns all of the {@link RowSetRecord}s whose specified column value is set.
     * @param colName Name of the column.
     * @return
     */
    public Collection<RowSetRecord> getColumnRecords(String colName) {
        Collection<RowSetRecord> col = getMap().get(colName);
        if ( col == null )
            return Collections.emptyList();
        else
            return col;
    }
    

    /**
     * Creates a map based on the columns of the {@link RowSetRecord}s in the event payload.
     * This map is not created in the initialization to prevent performance penalties.
     * @return
     */
    private synchronized Map<String, Collection<RowSetRecord>> getMap() {
        if ( columnNameToRecordMap == null ) {
        	columnNameToRecordMap = new HashMap<String, Collection<RowSetRecord>>();
        	for(RowSetRecord record: this.getPayloadCollection()){
        		String columnName = record.getColumn();
        		if (!columnNameToRecordMap.containsKey(columnName))
        			columnNameToRecordMap.put(columnName, new ArrayList<RowSetRecord>());
        		
        		columnNameToRecordMap.get(columnName).add(record);
        	}
        }
        return columnNameToRecordMap;
    }
}
