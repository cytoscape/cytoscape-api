package org.cytoscape.io;


/**
 * An interface extended by various reader and writer factories.
 * It provides basic information about the types of supported files.
 * @CyAPI.Spi.Interface
 */
public interface CyFileFilterProvider {
	/**
	 * Return the file filter associated with the IOFactory.
	 * @return the {@link CyFileFilter} associated with the IOFactory.
	 */
	CyFileFilter getFileFilter();

}
