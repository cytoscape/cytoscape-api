package org.cytoscape.view.model.table;

import java.util.function.Function;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;

/**
 * @CyAPI.NoReference.Interface
 */
public interface CyColumnView extends View<CyColumn> {
	
	
	/**
	 * @CyAPI.NoReference.Method
	 */
	<T, V extends T> void setVisualProperty(VisualProperty<? extends T> vp, Function<CyRow,T> mapping);

}
