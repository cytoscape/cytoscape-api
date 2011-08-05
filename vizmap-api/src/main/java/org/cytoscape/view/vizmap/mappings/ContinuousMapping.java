package org.cytoscape.view.vizmap.mappings;

import java.util.List;

import org.cytoscape.model.CyTableEntry;
import org.cytoscape.view.model.View;
import org.cytoscape.view.vizmap.VisualMappingFunction;

public interface ContinuousMapping<K, V> extends VisualMappingFunction<K,V>{

	public static final String CONTINUOUS = "Continuous Mapping";

	/**
	 * Gets all Data Points.
	 * 
	 * @return List of ContinuousMappingPoint objects.
	 */
	public List<ContinuousMappingPoint<K, V>> getAllPoints();

	/**
	 * Adds a New Data Point.
	 */
	public void addPoint(K value, BoundaryRangeValues<V> brv);

	/**
	 * Removes a Point from the List.
	 */
	public void removePoint(int index);

	/**
	 * Gets Total Point Count.
	 */
	public int getPointCount();

	/**
	 * Gets Specified Point.
	 *  
	 * @param index
	 *            Index Value.
	 * @return ContinuousMappingPoint.
	 */
	public ContinuousMappingPoint<K, V> getPoint(int index);

}