package org.cytoscape.io.write;

import java.io.File;
import java.io.OutputStream;
import java.util.Set;

import org.cytoscape.io.CyFileFilter;
import org.cytoscape.view.vizmap.VisualStyle;

/**
 * A {@link CyWriterManager} specific to writing {@link org.cytoscape.view.vizmap.VisualStyle} objects. 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule io-api
 */
public interface VizmapWriterManager extends CyWriterManager<VizmapWriterFactory> {

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified file of the specified file type. 
	 * @param styles The list of {@link org.cytoscape.view.vizmap.VisualStyle} objects to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param file The file to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified file of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(Set<VisualStyle> styles, CyFileFilter filter, File file) throws Exception;

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified output stream of the specified file type. 
	 * @param styles The list of {@link org.cytoscape.view.vizmap.VisualStyle} objects to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param os The output stream to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified output stream of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(Set<VisualStyle> styles, CyFileFilter filter, OutputStream os) throws Exception;
}
