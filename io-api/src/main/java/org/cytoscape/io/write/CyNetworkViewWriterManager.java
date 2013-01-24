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

import org.cytoscape.io.CyFileFilter;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

/**
 * A {@link org.cytoscape.io.write.CyWriterManager} specific to writing 
 * {@link org.cytoscape.view.model.CyNetworkView} objects. 
 * @CyAPI.Api.Interface
 */
public interface CyNetworkViewWriterManager extends CyWriterManager<CyNetworkViewWriterFactory> {

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified network to the
	 * specified file of the specified file type.
	 * @param network The {@link org.cytoscape.model.CyNetwork} to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param file The file to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified network to the
	 * specified file of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(CyNetwork network, CyFileFilter filter, File file) throws Exception;

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified network to the
	 * specified output steam of the specified file type.
	 * @param network The {@link org.cytoscape.model.CyNetwork} to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param os The output steam to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified network to the
	 * specified output steam of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(CyNetwork network, CyFileFilter filter, OutputStream os) throws Exception;
	
	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified view to the
	 * specified file of the specified file type. 
	 * @param view The {@link org.cytoscape.view.model.CyNetworkView} to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param file The file to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified view to the
	 * specified file of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(CyNetworkView view, CyFileFilter filter, File file) throws Exception;

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified view to the
	 * specified output steam of the specified file type. 
	 * @param view The {@link org.cytoscape.view.model.CyNetworkView} to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param os The output steam to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified view to the
	 * specified output steam of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(CyNetworkView view, CyFileFilter filter, OutputStream os) throws Exception;
}
