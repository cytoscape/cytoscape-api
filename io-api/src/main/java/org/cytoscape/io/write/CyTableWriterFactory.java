package org.cytoscape.io.write;

import java.io.OutputStream;

import org.cytoscape.model.CyTable;

/**
 * A specialization of {@link CyWriterFactory} that allows a {@link org.cytoscape.model.CyTable} to
 * be specified and written.
 * @CyAPI.Spi.Interface
 */
public interface CyTableWriterFactory extends CyWriterFactory {

	/**
	 * Creates a single Task that will write the specified {@link CyTable} object to the
	 * specified OutputStream. 
	 * @param os The stream to which the data will be written. 
	 * @param table The {@link CyTable} to be written.
	 */
	CyWriter getWriterTask(OutputStream os, CyTable table);
}
