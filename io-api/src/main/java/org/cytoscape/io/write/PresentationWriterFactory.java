package org.cytoscape.io.write;


import java.io.OutputStream;

import org.cytoscape.view.presentation.RenderingEngine;

/**
 * A specialization of {@link CyWriterFactory} that allows a View 
 * rendered by the specified {@link RenderingEngine} to
 * be specified and written.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface PresentationWriterFactory extends CyWriterFactory {

	/**
	 * Creates a single Task that will write the contents of the 
	 * specified {@link RenderingEngine} object to the specified OutputStream. 
	 * @param os The stream to which the data will be written. 
	 * @param re The {@link RenderingEngine} used to generate the image of the
	 * View it contains.
	 */
	CyWriter createWriter(OutputStream os, final RenderingEngine<?> re);
}
