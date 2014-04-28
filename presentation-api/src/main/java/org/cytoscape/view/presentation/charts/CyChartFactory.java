package org.cytoscape.view.presentation.charts;

import javax.swing.Icon;

import org.cytoscape.view.presentation.customgraphics.CustomGraphicLayer;

public interface CyChartFactory<T extends CustomGraphicLayer> {
	
	/**
 	 * Return the prefix to identify this chart factory.
 	 * This is used by the passthrough mapping logic to figure out if a
 	 * given String value should be mapped to this factory.
 	 *
 	 * @return the prefix for this CyChartFactory
 	 */
	String getId();
	
	/**
	 * Return the chart name which is will be displayed to the user.
	 * 
	 * @return display name as String.
	 */
	String getDisplayName();
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	Icon getIcon(int width, int height);
	
	/**
 	 * Get a new instance of a {@link CyChart}.  The string argument may
 	 * be used by some implementations to create the initial chart objects.
 	 * This is the method that will be used to take a String passthrough mapping and create the
 	 * correct {@link CyChart} instance.  Note that the prefix defined
 	 * above will get removed from the string before this method is called.
 	 *
 	 * @param input a possible input string that may be used to create the
 	 *              instance.  Not all implementations will use this.
 	 * @return the new instance
 	 */
	CyChart<T> getInstance(String input);
	
	/**
	 * Get a new instance of a {@link CyChart} that is a clone of the passed argument.
	 * 
	 * @param chart another chart
	 * @return the new instance
	 */
	CyChart<T> getInstance(CyChart<T> chart);
	
	/**
	 * Get a new instance of a {@link CyChart} that has empty or default options.
	 * 
	 * @return the new instance
	 */
	CyChart<T> getInstance();
	
//	/**
// 	 * Create a new {@link CyChart} object by parsing the string
// 	 * resulting from the {@link CyChart#toSerializableString()} method.
// 	 */
//	CyChart<T> parseSerializableString(String string); // TODO why both this and getInstance(String) ?
	
	/**
 	 * Return the class that this factory creates.  This is used by the deserialization
 	 * mechanism to find the factory method that can deserialize a given string.
 	 */
	Class<? extends CyChart<T>> getSupportedClass();
	
}
