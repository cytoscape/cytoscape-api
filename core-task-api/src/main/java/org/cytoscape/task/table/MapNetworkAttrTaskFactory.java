package org.cytoscape.task.table;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyTable;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface MapNetworkAttrTaskFactory extends TaskFactory {
	
	enum MappingType { 
		CURRENT_LOCAL("Only to the current network"),
		CURRENT_SHARED("All networks related to current network"),
		ALL_SHARED("All networks"),
		INDEPENDENT("None (create Global Table w/o mapping)"),
		;
		
		private final String desc;
		private MappingType(String desc) {
			this.desc = desc;
		}
		public String getDescription(){
			return desc;
		}
		public static MappingType fromDescription(String d) {
			for (MappingType m : values())
				if (m.getDescription().equals(d))
					return m;
			throw new IllegalArgumentException("String " + d + " doesn't match a known description in MappingType");
		}
	}
	/** 
	 * This creates a task iterator that will take a specified table and ask whether
	 * the columns in the specified table should become virtual columns in the node or
	 * edge table of the current network, all networks, or no networks.
	 * @param type The type of table to map to, either CyNode.class or CyEdge.class.
	 * @param newGlobalTable The table to be mapped. 
	 * @param mappingKey The column name in the existing table used to join with the primary key in the new table.
	 * @param mappingType The type of mapping to be performed.
	 */
	TaskIterator createTaskIterator(final Class<? extends CyIdentifiable> type, 
	                          final CyTable newGlobalTable,
	                          String mappingKey, 
	                          MappingType mappingType);
	/** 
	 * This creates a task iterator that will take a specified table and ask whether
	 * the columns in the specified table should become virtual columns in the node or
	 * edge table of the current network, all networks, or no networks.  The resulting
	 * Task will prompt the user to determine which mapping type to choose via the Tunable 
	 * user interface.
	 * @param type The type of table to map to, either CyNode.class or CyEdge.class.
	 * @param newGlobalTable The table to be mapped. 
	 * @param mappingKey The column name in the existing table used to join with the primary key in the new table.
	 */
	TaskIterator createTaskIterator(final Class<? extends CyIdentifiable> type, 
	                          final CyTable newGlobalTable,
	                          String mappingKey);
}
