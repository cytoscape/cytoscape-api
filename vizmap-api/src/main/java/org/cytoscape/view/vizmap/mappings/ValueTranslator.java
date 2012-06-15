package org.cytoscape.view.vizmap.mappings;


/**
 * Translate given table value to a Visual Property value.
 * This will be used by Passthrough Mapping.
 *
 * @param <V> Data type of the column used for mapping
 * @param <T> Type of Visual Property range value.  Such as Color, Number, String.
 */
public interface ValueTranslator<V, T> {
	
	/**
	 * Convert input value to Visual Property value.
	 * For example, if this is a translator from text representation of color to Color object, 
	 * inputValue is a String value and return value is a Color build from the given string.
	 * 
	 * @param inputValue table value of type V.
	 * @return translated Visual Property value.
	 */
	public T translate(final V inputValue);
	
	
	/**
	 * Returns compatible input data type.
	 * 
	 * @return data type of input value.
	 */
	public Class<T> getTranslatedValueType();
}
