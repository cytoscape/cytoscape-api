package org.cytoscape.view.layout;

/**
 * An enum describing different weighting strategies. 
 * @CyAPI.Enum.Class
 */
public enum WeightTypes {
	GUESS("Heuristic"),
	LOG("-Log(value)"),
	DISTANCE("1 - normalized value"),
	WEIGHT("normalized value");

	private String name;
	private WeightTypes(String str) { name=str; }

	/**
	 * Returns the name of the weighting type.
	 * @return the name of the weighting type.
	 */	
	public String toString() { return name; }
}