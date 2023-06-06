package org.cytoscape.view.vizmap;

import java.util.Map;
import java.util.Set;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;


/**
 * <p>Manager for {@link VisualStyle}s applied to table columns.</p> 
 * <p>There are two types of VisualStyle "associations" managed by this service.
 * <ul>
 * <li>
 * Direct Associations: This is when an VisualStyle is directly applied to a View&lt;CyColumn&gt;.
 * These column VisualStyles are not allowed for the default node/edge tables from a network, and are intended
 * for programmatic use by Apps to apply styles to unassigned tables.
 * To create styles for network tables use Network Style Associations instead.
 * </li>
 * <li>
 * Network Style Associations: This is when a column VisualStyle is associated with a network VisualStyle. 
 * These column VisualStyles are then applied to the default node/edge tables for the same networks that
 * have the network VisualStyle applied.
 * Note: network VisualStyles are managed by the {@link VisualMappingManager}. 
 * </li>
 * </ul>
 * </p>
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
 * 
 * @since 3.9
 */
public interface TableVisualMappingManager {

	/**
	 * <p>Set a {@link VisualStyle} directly to the target column view model.</p>
	 * 
	 * 
	 * <p>Note: As of Cytoscape 3.10 the preferred way to create a visual style for use in the node or edge table
	 * is to create a visual style and associate it with a network style using the setAssociatedVisualStyle(...) method.
	 * The setVisualStyle(...) method should only be used by Apps to programmatically set styles on tables that are not 
	 * registered with the CyNetworkTableManager.
	 * </p>
	 * 
	 * <p>Note: If the target column is from a network node/edge table, then it will automatically
	 * be associated with the network's current VisualStyle instead of being directly associated.</p>
	 * 
	 * @param visualStyle Visual Style to be set.  May be null.
	 * @param columnView The target column view model.
	 * @throws IllegalStateException If the columnView is for a network table, and the network does not have one style applied to it.
	 * 
	 * @since 3.9
	 */
	void setVisualStyle(View<CyColumn> columnView, VisualStyle visualStyle);
	
	/**
	 * Returns the {@link VisualStyle} directly associated with the target column view model.
	 * 
	 * @return VisualStyle associated with the column view model. May return null if the column does not have a style.
	 * @throws IllegalStateException If the columnView is for a network table, and the network does not have one style applied to it.
	 * 
	 * @since 3.9
	 */
	VisualStyle getVisualStyle(View<CyColumn> viewModel);

	/**
	 * Returns all directly associated {@link VisualStyle}s managed by this object.
	 * 
	 * @return Set of all directly associated VisualStyles. Note, the default VisualStyle returned 
	 * by {@link TableVisualMappingManager#getDefaultVisualStyle()} is not included in the returned set.
	 * 
	 * @since 3.9
	 */
	Set<VisualStyle> getAllVisualStyles();
	
	/**
	 * Returns all directly associated {@link VisualStyle}s managed by this object.
	 * @return Map of all directly associated VisualStyles.
	 * 
	 * @since 3.9
	 */
	Map<View<CyColumn>, VisualStyle> getAllVisualStylesMap();
	
	/**
	 * Returns a Set of all {@link VisualLexicon}s.
	 * @return a Set of all {@link VisualLexicon}s.
	 * 
	 * @since 3.9
	 */
	Set<VisualLexicon> getAllVisualLexicon();
	
	/**
	 * Returns the default column {@link VisualStyle}. This is just an empty visual style that can be used to avoid dealing with null.
	 * 
	 * @since 3.10
	 */
	VisualStyle getDefaultVisualStyle();
	
	/**
	 * Associates a column VisualStyle to a network VisualStyle, and specifies the table type and column name for the column VisualStyle
	 * to be applied to. Note this method does not check if a column with the given name actually exists.
	 * 
	 * @param networkVisualStyle The network VisualStyle that the given columnVisualStyle will be associated with.
	 * @param tableType CyNode.class or CyEdge.class
	 * @param colName The name of the column.
	 * @param columnVisualStyle The VisualStyle that will be applied to the column. May be null to remove a style from the given column.
	 * 
	 * @throws NullPointerException If networkVisualStyle, tableType or colName are null.
	 * 
	 * @since 3.10
	 */
	void setAssociatedVisualStyle(VisualStyle networkVisualStyle, Class<? extends CyIdentifiable> tableType, String colName, VisualStyle columnVisualStyle);
	
	/**
	 * Returns all column VisualStyles that is associated with the given networkVisualStyle and table type.
	 * 
	 * @param networkVisualStyle  The network VisualStyle that the returned columnVisualStyles are associated with.
	 * @param tableType CyNode.class or CyEdge.class
	 * 
	 * @since 3.10
	 */
	Map<String,VisualStyle> getAssociatedColumnVisualStyles(VisualStyle networkVisualStyle, Class<? extends CyIdentifiable> tableType);
	
	/**
	 * Returns the column VisualStyle that is associated with the given networkVisualStyle, table type and column name.
	 *  
	 * @param networkVisualStyle The network VisualStyle that the returned column VisualStyle is associated with.
	 * @param tableType CyNode.class or CyEdge.class
	 * @param colName The name of the column.
	 * 
	 * @since 3.10
	 */
	default VisualStyle getAssociatedColumnVisualStyle(VisualStyle networkVisualStyle, Class<? extends CyIdentifiable> tableType, String colName) {
		// MKTODO this could be optimized by implementing directly in TableVisualMappingManagerImpl
		var map = getAssociatedColumnVisualStyles(networkVisualStyle, tableType);
		return (map == null) ? null : map.get(colName);
	}
	
	/**
	 * Returns the network VisualStyles that are associated with the given column VisualStyle.
	 * 
	 * @since 3.10
	 */
	Set<VisualStyle> getAssociatedNetworkVisualStyles(VisualStyle columnVisualStyle);
	
	/**
	 * Returns a set of StyleAssociations for the given columnVisualStyle.
	 * The StyleAssociations contain the network VisualStyles, table types and column names that the given columnVisualStyle
	 * is associated with.
	 * 
	 * @since 3.10
	 */
	Set<StyleAssociation> getAssociations(VisualStyle columnVisualStyle);
	
	/**
	 * Returns a set of all StyleAssociations that are managed by this service.
	 * The StyleAssociations contain the network VisualStyles, table types and column names that the given columnVisualStyle
	 * is associated with.
	 * 
	 * @since 3.10
	 */
	Set<StyleAssociation> getAllStyleAssociations();
}
