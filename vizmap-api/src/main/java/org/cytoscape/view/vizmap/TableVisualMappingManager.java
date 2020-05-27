package org.cytoscape.view.vizmap;

import java.util.Set;

import org.cytoscape.model.CyColumn;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;

public interface TableVisualMappingManager {

	/**
	 * Set a {@linkplain VisualStyle} to the target network view model.
	 * 
	 * @param visualStyle Visual Style to be set.
	 * @param networkViewModel The target network view model.
	 */
	void setVisualStyle(VisualStyle visualStyle, View<CyColumn> viewModel);
	
	/**
	 * Returns the {@linkplain VisualStyle} associated with the target network
	 * view model.
	 * 
	 * 
	 * @return VisualStyle associated with the network view model. This is
	 *         always non-null value. If there is no mapping from given view
	 *         model to a style, then default style will be used.
	 */
	VisualStyle getVisualStyle(View<CyColumn> viewModel);

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
	 * Returns a Set of all {@link VisualLexicon}s.
	 * 
	 * @return a Set of all {@link VisualLexicon}s.
	 */
	Set<VisualLexicon> getAllVisualLexicon();
}
