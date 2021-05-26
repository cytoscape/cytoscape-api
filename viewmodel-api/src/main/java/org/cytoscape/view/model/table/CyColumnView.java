package org.cytoscape.view.model.table;

import java.util.function.Function;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;

/**
 * This interface is meant for internal use, it should not be used by Apps.
 * 
 * @CyAPI.NoReference.Interface
 * @since 3.9
 */
public interface CyColumnView extends View<CyColumn> {
	
	
	/**
	 * Set a table visual property that will be applied to the cells in a column. The second argument
	 * is intended to be a method reference to VisualMappingFunction.getMappedValue(). For example:
	 * 
	 * <p>
	 * <code>
	 * VisualMappingFunction mapping = ...create mapping...
	 * columnView.setCellVisualProperty(BasicTableVisualLexicon.CELL_BACKGROUND_PAINT, mapping::getMappedValue);
	 * </code>
	 * </p>
	 * 
	 * @CyAPI.NoReference.Method
	 */
	<T> void setCellVisualProperty(VisualProperty<? extends T> vp, Function<CyRow,T> mapping);

	
	<T> Function<CyRow,T> getCellVisualProperty(VisualProperty<? extends T> vp);
}
