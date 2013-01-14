package org.cytoscape.view.vizmap.mappings;

import java.util.List;

import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * An interface describing a continuous mapping from attribute value
 * to visual property.
 * @param <K> Generic type of the attribute mapped.
 * @param <V> Generic type of the VisualProperty used in this mapping.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface ContinuousMapping<K, V> extends VisualMappingFunction<K,V>{

	/** A label string for this mapping. */
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
	 * @param index The index of the Point to remove.
	 */
	public void removePoint(int index);

	/**
	 * Gets Total Point Count.
	 * @return the total point count.
	 */
	public int getPointCount();

	/**
	 * Gets Specified Point.
	 *  
	 * @param index Index Value.
	 * @return ContinuousMappingPoint.
	 */
	public ContinuousMappingPoint<K, V> getPoint(int index);

}
