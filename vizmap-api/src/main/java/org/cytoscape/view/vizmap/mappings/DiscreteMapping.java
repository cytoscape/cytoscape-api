package org.cytoscape.view.vizmap.mappings;

import java.util.Map;

import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * 
 * @param <K>
 * @param <V>
 * @CyAPI.Api.Interface
 */
public interface DiscreteMapping<K, V> extends VisualMappingFunction<K,V>{

	public static final String DISCRETE = "Discrete Mapping";

	/**
	 * Gets Value for Specified Key.
	 * 
	 * @param key
	 *            String Key.
	 * @return Object.
	 */
	public V getMapValue(K key);

	/**
	 * Puts New Key/Value in Map.
	 * 
	 * @param key
	 *            Key Object.
	 * @param value
	 *            Value Object.
	 */
	public <T extends V> void putMapValue(final K key, final T value);

	/**
	 * Adds All Members of Specified Map.
	 * 
	 * @param map
	 *            Map.
	 */
	public <T extends V> void putAll(Map<K, T> map);

	/**
	 * Gets all map values.
	 * @return all map values.
	 */
	public Map<K, V> getAll();

}