package org.cytoscape.view.model;

import java.util.function.Function;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyRow;

/**
 * In order to apply a mapping to all the cells within a column without creating a CyCell interface
 * we need a way for the renderer itself to apply the mapping to each cell. 
 * 
 * I propose extending View<CyColumn> with this interface that allows the mapping to be 
 * passed directly to the CyColumnView, that way it can be called by the table renderer. 
 * 
 * This should allow for decent performance because the renderer can apply the mapping lazily.
 * For example if only 10 rows are visible we only need to apply the mapping for those 10 rows.
 * Only the renderer knows which rows are currently visible on the screen.
 * 
 * The second parameter to setVisualProperty(..) is of type java.util.function.Function and not
 * VisualMappingFunction. This is because viewmodel-api does not declare a dependency on vizmap-api.
 * It will be easy to adapt VisualMappingFunction using a method reference, like so...
 * 
 * cyColumnView.setVisualProperty(Lexicon.SOME_VP, mapping::getMappedValue)
 * 
 * CyColumnView is not intended to be used directly by an App. Apps can just
 * use View<CyColumn>. Mapping functions will be set on a VisualStyle and then apply
 * the style. The VisualStyleImpl will actually call this version of setVisualProperty().
 *
 */
public interface CyColumnView extends View<CyColumn> {

	/**
	 * Typically this method would not be called directly by an App. The app would 
	 */
	<T, V extends T> void setVisualProperty(VisualProperty<? extends T> vp, Function<CyRow,T> mapping);
	
	
}
