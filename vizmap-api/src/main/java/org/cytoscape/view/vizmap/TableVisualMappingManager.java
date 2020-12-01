package org.cytoscape.view.vizmap;

import java.util.Set;

import org.cytoscape.model.CyColumn;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;

public interface TableVisualMappingManager {

	/**
	 * Set a {@linkplain VisualStyle} to the target column view model.
	 * @param visualStyle Visual Style to be set.  May be null.
	 * @param viewModel The target column view model.
	 */
	void setVisualStyle(View<CyColumn> viewModel, VisualStyle visualStyle);
	
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
	 * Returns a Set of all {@link VisualLexicon}s.
	 * @return a Set of all {@link VisualLexicon}s.
	 */
	Set<VisualLexicon> getAllVisualLexicon();
}
