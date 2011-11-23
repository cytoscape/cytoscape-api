package org.cytoscape.view.presentation.property;

/**
 * Object used as a Visual Property value.
 * In most cases, Visual Property uses basic objects such as Number or Color.
 * If developers want to use custom objects as a Visual Property value, they should implement this.
 * 
 * @CyAPI.Api.Interface
 */
public interface VisualPropertyValue {
	
	/**
	 * Name of this value type.
	 * For example, Arrow Shape or Node Shape.
	 * 
	 * @return human readable name of this object type.
	 */
	String getDisplayName();
	
	
	/**
	 * Returns serializable representation of this value.
	 * 
	 * @return Value in serializable string form.
	 */
	String getSerializableString();
	
	/**
	 * Create actual value from serializable String.
	 * 
	 * @param serializableString serialized form of this value
	 * @return Value created from given text representation.
	 */
	VisualPropertyValue parseSerializableString(final String serializableString);
}
