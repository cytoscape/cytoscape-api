package org.cytoscape.io.write;

import java.io.OutputStream;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

/**
 * A specialization of {@link CyWriterFactory} that allows a 
 * {@link CyNetworkView} or {@link CyNetwork} to be specified 
 * and written to an OutputStream.
 * @CyAPI.Spi.Interface
 */
public interface CyNetworkViewWriterFactory extends CyWriterFactory {

	/**
	 * Creates a single Task that will write the specified network to the
	 * specified OutputStream. 
	 * @param os The stream to write to. 
	 * @param view The {@link CyNetworkView} to be written.
	 */
	CyWriter getWriterTask(OutputStream os, CyNetworkView view);

	/**
	 * Creates a single Task that will write the specified network to the
	 * specified OutputStream. 
	 * @param os The stream to write to. 
	 * @param network The {@link CyNetwork} to be written.
	 */
	CyWriter getWriterTask(OutputStream os, CyNetwork network);
}
