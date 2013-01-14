package org.cytoscape.io.write;


import java.io.OutputStream;

/**
 * A specialization of {@link CyWriterFactory} that allows a property Object to
 * be specified and written. See {@link org.cytoscape.property.CyProperty} for details on the type of Object.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CyPropertyWriterFactory extends CyWriterFactory {

	/**
	 * Creates a single Task that will write the specified property object to the
	 * specified OutputStream. 
	 * @param os The stream to which the data will be written.
	 * @param property The property object to be written. In general 
	 * this object should be of types described in {@link org.cytoscape.property.CyProperty}.
	 */
	CyWriter createWriter(OutputStream os, Object property);
}
