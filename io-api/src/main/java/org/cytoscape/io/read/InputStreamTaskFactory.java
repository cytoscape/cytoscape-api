
package org.cytoscape.io.read;

import java.io.InputStream;

import org.cytoscape.io.CyFileFilterProvider;
import org.cytoscape.work.TaskIterator;

/**
 * A super interface that allows the input stream to be set for reader
 * task factories.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface InputStreamTaskFactory extends CyFileFilterProvider {

	/**
	 * Sets the input stream that will be read by the Reader created from
	 * this factory.
	 * @param is The {@link java.io.InputStream} to be read.
	 * @param inputName The name of the input. 
	 */
	TaskIterator createTaskIterator(InputStream is, String inputName);
	
	/**
	 * Returns true if the factory is ready to be produce a TaskIterator and false otherwise.
	 * @param is The {@link java.io.InputStream} to be read.
	 * @param inputName The name of the input.
	 * @return true if the factory is ready to be produce a TaskIterator and false otherwise.
	 */
	boolean isReady(InputStream is, String inputName);
}
