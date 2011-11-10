package org.cytoscape.view.presentation.property;

/**
 * #ASKMIKE need class/method comments, check tag
 * @CyAPI.Api.Interface
 */
public interface VisualPropertyValue {
	String getDisplayName();
	
	String getSerializableString();
	VisualPropertyValue parseSerializableString(final String serializableString);
}
