package org.cytoscape.io.write;

import java.io.OutputStream;

import org.cytoscape.io.CyFileFilterProvider;

/**
 * A marker interface used to identify factories that
 * create CyWriter instances.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CyWriterFactory extends CyFileFilterProvider {

}
