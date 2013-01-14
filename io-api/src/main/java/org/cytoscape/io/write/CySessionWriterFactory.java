package org.cytoscape.io.write;


import java.io.OutputStream;

import org.cytoscape.session.CySession;

/**
 * A specialization of {@link CyWriterFactory} that allows a {@link org.cytoscape.session.CySession} to
 * be specified and written.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CySessionWriterFactory extends CyWriterFactory {

	/**
	 * Creates a single Task that will write the specified {@link CySession} object to the
	 * specified OutputStream. 
	 * @param os The stream to which the data will be written. 
	 * @param session The {@link org.cytoscape.session.CySession} to be written.
	 */
	CyWriter createWriter(OutputStream os, CySession session);
}
