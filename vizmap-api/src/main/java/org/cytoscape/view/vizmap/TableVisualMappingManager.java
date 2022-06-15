package org.cytoscape.view.vizmap;

import java.util.Map;
import java.util.Set;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.vizmap.events.VisualStyleAboutToBeRemovedEvent;
import org.cytoscape.view.vizmap.events.VisualStyleAddedEvent;


/**
 * Manager for {@link VisualStyle}s. This object manages mapping from column view
 * models to VisualStyle. 
 * <p>
 * Add/Remove operations will be done through events. For more information, read
 * JavaDoc for {@linkplain VisualStyleAddedEvent} and
 * {@linkplain VisualStyleAboutToBeRemovedEvent}.
 * </p>
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
 * @since 3.9
 */
public interface TableVisualMappingManager {

	/**
	 * Set a {@link VisualStyle} to the target column view model.
	 * @param visualStyle Visual Style to be set.  May be null.
	 * @param viewModel The target column view model.
	 */
	void setVisualStyle(View<CyColumn> viewModel, VisualStyle visualStyle);
	
	/**
	 * Returns the {@link VisualStyle} associated with the target column view model.
	 * @return VisualStyle associated with the column view model.
	 *         May return null if the column does not have a style.
	 */
	VisualStyle getVisualStyle(View<CyColumn> viewModel);

	/**
	 * Returns all available {@link VisualStyle}s managed by this object.
	 * @return Set of all registered VisualStyles.
	 */
	Set<VisualStyle> getAllVisualStyles();
	
	/**
	 * Returns all available {@link VisualStyle}s managed by this object.
	 * @return Set of all registered VisualStyles.
	 */
	Map<View<CyColumn>, VisualStyle> getAllVisualStylesMap();
	

	/**
	 * Returns a Set of all {@link VisualLexicon}s.
	 * @return a Set of all {@link VisualLexicon}s.
	 */
	Set<VisualLexicon> getAllVisualLexicon();
	
	
	/**
	 * Return default {@link VisualStyle}.  This is just an empty visual style.
	 * @return default Visual Style.
	 */
	VisualStyle getDefaultVisualStyle();
	
	
	void setAssociatedVisualStyle(VisualStyle networkVisualStyle, Class<? extends CyIdentifiable> tableType, String colName, VisualStyle columnVisualStyle);
	
	
	Map<String,VisualStyle> getAssociatedColumnVisualStyles(VisualStyle networkVisualStyle, Class<? extends CyIdentifiable> tableType);
	
	
	default VisualStyle getAssociatedColumnVisualStyle(VisualStyle networkVisualStyle, Class<? extends CyIdentifiable> tableType, String colName) {
		var map = getAssociatedColumnVisualStyles(networkVisualStyle, tableType);
		if(map == null)
			return null;
		return map.get(colName);
	}
	
	
	Set<VisualStyle> getAssociatedNetworkVisualStyles(VisualStyle columnVisualStyle);
	
	Set<StyleAssociation> getAssociations(VisualStyle columnVisualStyle);
	
	Set<StyleAssociation> getAllStyleAssociations();
}
