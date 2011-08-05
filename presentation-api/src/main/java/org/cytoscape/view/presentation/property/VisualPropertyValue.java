package org.cytoscape.view.presentation.property;


public interface VisualPropertyValue {
	String getDisplayName();
	
	String getSerializableString();
	VisualPropertyValue parseSerializableString(final String serializableString);
}
