package org.cytoscape.view.presentation.gradients;

import java.util.Map;

import org.cytoscape.view.presentation.customgraphics.CustomGraphicLayer;

public interface CyGradientFactory<T extends CustomGraphicLayer> {
	
	/**
 	 * Return the prefix to identify this gradient factory.
 	 * This is used by the passthrough mapping logic to figure out if a
 	 * given String value should be mapped to this factory.
 	 *
 	 * @return the prefix for this CyGradientFactory
 	 */
	String getId();
	
	/**
	 * Return the gradient name which is will be displayed to the user.
	 * 
	 * @return display name as String.
	 */
	String getDisplayName();
	
	/**
 	 * Get a new instance of a {@link CyGradient}.  The string argument may
 	 * be used by some implementations to create the initial gradient objects.
 	 * This is the method that will be used to take a String passthrough mapping and create the
 	 * correct {@link CyGradient} instance.  Note that the prefix defined
 	 * above will get removed from the string before this method is called.
 	 *
 	 * @param input a possible input string that may be used to create the
 	 *              instance.  Not all implementations will use this.
 	 * @return the new instance
 	 */
	CyGradient<T> getInstance(String input);
	
	/**
	 * Get a new instance of a {@link CyGradient} that is a clone of the passed argument.
	 * 
	 * @param gradient another gradient
	 * @return the new instance
	 */
	CyGradient<T> getInstance(CyGradient<T> gradient);
	
	/**
	 * Get a new instance of a {@link CyGradient} with the passed properties.
	 * 
	 * @param properties optional properties to initialize the new gradient.
	 * @return the new instance
	 */
	CyGradient<T> getInstance(Map<String, Object> properties);
	
	/**
 	 * Return the class that this factory creates.  This is used by the deserialization
 	 * mechanism to find the factory method that can deserialize a given string.
 	 */
	Class<? extends CyGradient<T>> getSupportedClass();
	
}
