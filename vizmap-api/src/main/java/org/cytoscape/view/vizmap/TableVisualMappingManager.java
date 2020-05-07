package org.cytoscape.view.vizmap;

import java.util.Set;

import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.table.CyTableView;

public interface TableVisualMappingManager {

	/**
	 * Set a {@linkplain VisualStyle} to the target network view model.
	 * 
	 * @param visualStyle Visual Style to be set.
	 * @param networkViewModel The target network view model.
	 */
	void setVisualStyle(VisualStyle visualStyle, CyTableView viewModel);
	
	/**
	 * Returns the {@linkplain VisualStyle} associated with the target network
	 * view model.
	 * 
	 * 
	 * @return VisualStyle associated with the network view model. This is
	 *         always non-null value. If there is no mapping from given view
	 *         model to a style, then default style will be used.
	 */
	VisualStyle getVisualStyle(CyTableView viewModel);

	/**
	 * Returns all available {@linkplain VisualStyle}s managed by this object.
	 * 
	 * @return Set of all registered VisualStyles.
	 * 
	 */
	Set<VisualStyle> getAllVisualStyles();

	/**
	 * Add a new {@link VisualStyle} to this manager.
	 * 
	 * @param visualStyle
	 *            new visual style to be registered.
	 */
	void addVisualStyle(VisualStyle visualStyle);

	/**
	 * Remove a VisualStyle from this manager.
	 * 
	 * @param visualStyle
	 *            VisualStyle to be removed.
	 */
	void removeVisualStyle(VisualStyle visualStyle);

	/**
	 * Return default {@link VisualStyle}.  This is just an empty visual style.
	 * 
	 * @return default Visual Style.
	 */
	VisualStyle getDefaultVisualStyle();
	
	/**
	 * Set the specified {@link VisualStyle} as the current one. 
	 * 
	 * @param visualStyle the {@link VisualStyle} that will become the current style.
	 */
	void setCurrentVisualStyle(VisualStyle visualStyle);
	
	/**
	 * Returns currently selected Visual Style.
	 * 
	 * @return Selected Visual Style.
	 */
	VisualStyle getCurrentVisualStyle();
	
	
	/**
	 * Returns a Set of all {@link VisualLexicon}s.
	 * 
	 * @return a Set of all {@link VisualLexicon}s.
	 */
	Set<VisualLexicon> getAllVisualLexicon();
}
