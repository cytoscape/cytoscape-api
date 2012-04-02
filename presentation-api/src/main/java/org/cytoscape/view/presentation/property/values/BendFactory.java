package org.cytoscape.view.presentation.property.values;


/**
 * Factory to create an instance of {@linkplain Bend}.
 */
public interface BendFactory {
	
	/**
	 * Creates an instance of edge bend.
	 * Usually, this creates Bend object without any {@linkplain Handle}s.
	 * 
	 * @return new instance of Bend object.
	 */
	Bend createBend();

	/**
	 * Creates an instance of edge bend from serializable string.
	 * 
	 * @param serializedString string representation of edge bend.
	 * 
	 * @return New instance of Bend (interpretation of the given string).
	 */
	Bend parseSerializableString(final String serializedString);
}
