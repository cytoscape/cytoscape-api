
package org.cytoscape.io.read;

import java.io.InputStream;
import org.cytoscape.io.CyFileFilterProvider;
import org.cytoscape.work.TaskFactory;

/**
 * A super interface that allows the input stream to be set for reader
 * task factories.
 */
public interface InputStreamTaskFactory extends TaskFactory, CyFileFilterProvider {

	/**
	 * Sets the input stream that will be read by the Reader created from
	 * this factory.
	 * @param is The {@link java.io.InputStream} to be read.
	 * @param inputName The name of the input. 
	 */
	void setInputStream(InputStream is, String inputName);
}
