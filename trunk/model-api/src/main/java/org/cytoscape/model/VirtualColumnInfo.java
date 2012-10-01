package org.cytoscape.model;

/**
 * Information object for columns that stores extra Virtual Column information
 * if applicable. See {@link CyTable#addVirtualColumn} for details on
 * what virtual columns are.
 * @CyAPI.Api.Interface
 */
public interface VirtualColumnInfo {
	/**
	 * Returns true if the column is virtual, otherwise false.
	 * @return true if the column is virtual, otherwise false.
	 */
	boolean isVirtual();
	
	/**
	 * Returns the name of the column from the source table which contains the
	 * values this column provides. 
	 * @return the name of the column from the source table which contains the
	 * values this column provides.
	 */
	String getSourceColumn();
	
	/**
	 * Returns the name of the column from the source table used for the join.
	 * @return the name of the column from the source table used for the join.
	 */
	String getSourceJoinKey();
	
	/**
	 * Returns the name of the column from the target table used for the join.
	 * @return the name of the column from the target table used for the join.
	 */
	String getTargetJoinKey();
	
	/**
	 * Returns the originating table for this column if this column is virtual.
	 * @return the originating table for this column if this column is virtual.
	 */
	CyTable getSourceTable();

	/**
	 * Returns true if this virtual column was originally created
	 * immutable.
	 * @return true if this virtual column was originally created immutable.
	 */
	boolean isImmutable();
}
