package org.cytoscape.view.presentation.property;

public abstract class AbstractVisualPropertyValue implements VisualPropertyValue {

	private final String displayName;
	private final String serializableString;
	
	public AbstractVisualPropertyValue(final String displayName, final String serializableString) {
		this.displayName = displayName;
		this.serializableString = serializableString;
	}
	
	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String getSerializableString() {
		return this.serializableString;
	}

}
