package org.cytoscape.view.presentation.property.values;

/**
 * Object used as a Visual Property value.
 * In most cases, Visual Property uses basic objects such as Number or Color.
 * If developers want to use custom objects as a Visual Property value, they should implement this.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
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
	
}
