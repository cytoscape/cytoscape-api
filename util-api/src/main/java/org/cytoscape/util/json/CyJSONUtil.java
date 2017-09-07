package org.cytoscape.util.json;

import java.util.Collection;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

/**
 * This interface provides some very basic JSON representations of Cytoscape objects. The implementation of this 
 * interface should be included as part of a core app, to allow updates to the schemas outside of the regular Cytoscape
 * release cycle.
 * 
 * As a guideline, any JSON produced should be compatible with both R (using JSONIO) and python (using pandas).
 * 
 * @author David Otasek (dotasek.dev@gmail.com)
 *
 */
public interface CyJSONUtil {
	
	/**
	 * Returns a reference (via SUID) for a CyIdentifiable.
	 * 
	 * @param cyIdentifiable
	 * @return a reference (via SUID) for a CyIdentifiable.
	 */
	public String toJson(CyIdentifiable cyIdentifiable);

	/**
	 * Returns a list of references (via SUID) for a collection of CyIdentifiable.
	 * 
	 * @param collection
	 * @return a list of references (via SUID) for a collection of CyIdentifiable.
	 */
	public String cyIdentifiablesToJson(Collection<? extends CyIdentifiable> collection);
	
	/**
	 * Returns a JSON representation of a single CyNode and its relevant data. 
	 * 
	 * This JSON will be identical to the relevant row in the node table.
	 * 
	 * @param network
	 * @param cyNode
	 * @return a JSON representation of a single CyNode and its relevant data.
	 */
	public String toJson(CyNetwork network, CyNode cyNode);
	
	/**
	 * Returns a JSON representation of a single CyEdge and its relevant data. 
	 * 
	 * This JSON will be identical to the relevant row in the edge table, with the exception of additional
	 * source and target columns.
	 * 
	 * @param network
	 * @param cyEdge
	 * @return a JSON representation of a single CyEdge and its relevant data. 
	 */
	public String toJson(CyNetwork network, CyEdge cyEdge);
	
	/**
	 * Returns a JSON representation of a network and its relevant data. 
	 * 
	 * This JSON will be identical to the relevant row in the network table.
	 * 
	 * @param cyNetwork
	 * @return a JSON representation of a network and its relevant data. 
	 */
	public String toJson(CyNetwork cyNetwork);
	
	/**
	 * Returns a JSON representation of a CyTable.
	 * 
	 * The output can be customized to provide table definition information, as well as the rows in the table. Note that
	 * setting both includeDefinition and includeRows to false will result in an empty JSON object. 
	 * 
	 * @param cyTable
	 * @param includeDefinition defines whether or not to include definitions like table name in the output.
	 * @param includeRows defines whether or not to include the table's row data in the output.
	 * @return a JSON representation of a CyTable
	 */
	public String toJson(CyTable cyTable, boolean includeDefinition, boolean includeRows);
	
	
	/**
	 * Returns a list of references (via column name) for a collection of CyColumn.
	 * 
	 * @param collection
	 * @return a list of references (via column name) for a collection of CyColumn
	 */
	public String cyColumnsToJson(Collection<CyColumn> collection);
	
	/**
	 * Returns a JSON representation of CyColumn.
	 * 
	 * The output can be customized to provide column definition information, as well as the values in the column. Note 
	 * that setting both includeDefinition and includeValues to false will result in an empty JSON object. 
	 * 
	 * @param cyColumn
	 * @param includeDefinition defines whether or not to include definitions like column name in the output.
	 * @param includeValues defines whether or not to include the column's data in the output.
	 * @return a JSON representation of a CyColumn.
	 */
	public String toJson(CyColumn cyColumn, boolean includeDefinition, boolean includeValues);
	
	/**
	 * Returns a JSON representation of a table row.
	 * 
	 * @param cyRow
	 * @return a JSON representation of a table row.
	 */
	public String toJson(CyRow cyRow);
}
