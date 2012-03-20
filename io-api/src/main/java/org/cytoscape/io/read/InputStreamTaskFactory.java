
package org.cytoscape.io.read;

import java.io.InputStream;

import org.cytoscape.io.CyFileFilterProvider;
import org.cytoscape.work.TaskIterator;

/**
 * A super interface that allows the input stream to be set for reader
 * task factories.
 * @CyAPI.Spi.Interface
 */
public interface InputStreamTaskFactory<T> extends CyFileFilterProvider {

	/**
	 * Sets the input stream that will be read by the Reader created from
	 * this factory.
	 * @param is The {@link java.io.InputStream} to be read.
	 * @param inputName The name of the input. 
	 */
	TaskIterator createTaskIterator(T tunableContext, InputStream is, String inputName);
	
	boolean isReady(T tunableContext, InputStream is, String inputName);
	
	T createTunableContext();
}
