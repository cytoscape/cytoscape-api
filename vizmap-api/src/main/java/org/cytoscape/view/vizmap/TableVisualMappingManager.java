package org.cytoscape.view.vizmap;

import java.util.Set;

import org.cytoscape.model.CyColumn;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;

public interface TableVisualMappingManager {

	/**
	 * Set a {@linkplain VisualStyle} to the target column view model.
	 * @param visualStyle Visual Style to be set.
	 * @param networkViewModel The target column view model.
	 */
	void setVisualStyle(VisualStyle visualStyle, View<CyColumn> viewModel);
	
	/**
	 * Returns the {@linkplain VisualStyle} associated with the target network view model.
	 * @return VisualStyle associated with the network view model.
	 *         May return null if the column does not have a style.
	 */
	VisualStyle getVisualStyle(View<CyColumn> viewModel);

	/**
	 * Returns all available {@linkplain VisualStyle}s managed by this object.
	 * @return Set of all registered VisualStyles.
	 */
	Set<VisualStyle> getAllVisualStyles();

	/**
	 * Add a new {@link VisualStyle} to this manager.
	 * @param visualStyle  new visual style to be registered.
	 */
	void addVisualStyle(VisualStyle visualStyle);

	/**
	 * Remove a VisualStyle from this manager.
	 * @param visualStyle  VisualStyle to be removed.
	 */
	void removeVisualStyle(VisualStyle visualStyle);
	
	/**
	 * Returns a Set of all {@link VisualLexicon}s.
	 * @return a Set of all {@link VisualLexicon}s.
	 */
	Set<VisualLexicon> getAllVisualLexicon();
}
