package org.cytoscape.io.write;

import java.io.OutputStream;

import org.cytoscape.io.CyFileFilterProvider;

/**
 * CyWriterFactory defines the base methods for specifying output and
 * for generating a Task to write the actual output.  Instantiations
 * of CyWriterFactories are meant to be singleton objects registered
 * as OSGi services.
 * @CyAPI.Spi.Interface
 */
public interface CyWriterFactory extends CyFileFilterProvider {

	/**
	 * This method defines where the generated {@link CyWriter} Task should
	 * write its data to. This method is meant to be called prior
	 * to calling getWriter().
	 * @param os The {@link java.io.OutputStream} to be written to.
	 */
	void setOutputStream(OutputStream os);

	/**
	 * Returns a {@link CyWriter} Task suitable for writing to the specified
	 * output stream.
	 * @return A {@link CyWriter} Task suitable for writing to the specified
	 * output stream.
	 */
	CyWriter getWriterTask();
}
