package org.cytoscape.view.layout;

/**
 * An enum describing different weighting strategies. 
 * @CyAPI.Enum.Class
 */
public enum WeightTypes {
	/** Use a heuristic to guess. */
	GUESS("Heuristic"),
	/** Use the negative log value. */
	LOG("-Log(value)"),
	/** Use 1 minus the normalized value. */
	DISTANCE("1 - normalized value"),
	/** Use the normalized value. */
	WEIGHT("normalized value");

	private String name;
	private WeightTypes(String str) { name=str; }

	/**
	 * Returns the name of the weighting type.
	 * @return the name of the weighting type.
	 */	
	public String toString() { return name; }
}
