package org.cytoscape.io.write;

/*
 * #%L
 * Cytoscape IO API (io-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

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
	 * @param networkStyles The list of network {@link org.cytoscape.view.vizmap.VisualStyle} objects to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param file The file to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified file of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(Set<VisualStyle> networkStyles, CyFileFilter filter, File file) throws Exception;

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified output stream of the specified file type. 
	 * @param networkStyles The list of {@link org.cytoscape.view.vizmap.VisualStyle} objects to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param os The output stream to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified output stream of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(Set<VisualStyle> networkStyles, CyFileFilter filter, OutputStream os) throws Exception;
	
	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified file of the specified file type. 
	 * @param networkStyles The list of network {@link org.cytoscape.view.vizmap.VisualStyle} objects to be written.
	 * @param tableStyles The list of table {@link org.cytoscape.view.vizmap.VisualStyle} objects to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param file The file to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified file of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(Set<VisualStyle> networkStyles, Set<VisualStyle> tableStyles, CyFileFilter filter, File file) throws Exception;
	
	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified output stream of the specified file type. 
	 * @param networkStyles The list of {@link org.cytoscape.view.vizmap.VisualStyle} objects to be written.
	 * @param tableStyles The list of {@link org.cytoscape.view.vizmap.VisualStyle} objects to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param os The output stream to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified vizmap to the
	 * specified output stream of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(Set<VisualStyle> networkStyles, Set<VisualStyle> tableStyles, CyFileFilter filter, OutputStream os) throws Exception;
}
