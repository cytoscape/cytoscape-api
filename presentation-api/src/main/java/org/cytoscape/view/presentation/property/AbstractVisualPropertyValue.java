package org.cytoscape.view.presentation.property;

/**
 * #ASKMIKE class comment
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractVisualPropertyValue implements VisualPropertyValue {

	private final String displayName;
	private final String serializableString;
	
	/**
	 * Constructs an AbstractVisualPropertyValue.
	 * @param displayName the display name of the visual property value.
	 * @param serializableString #ASKMIKE
	 */
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
	
	@Override
	public String toString() {
		return displayName;
	}

}
