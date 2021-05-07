package org.cytoscape.io.write;

/*
 * #%L
 * Cytoscape IO API (io-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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


import org.cytoscape.io.CyFileFilter;

import java.io.File;
import java.io.OutputStream;
import java.util.List;


/**
 * A {@link CyWriterManager} specific to writing property objects. See 
 * {@link org.cytoscape.property.CyProperty} for information on types of object expected.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule io-api
 */
public interface CyPropertyWriterManager extends CyWriterManager<CyPropertyWriterFactory> {
	/** 
	 * Returns the <code>CyFileFilter</code>s known to this <code>CyWriter</code> manager.
	 * @return the set of known file filters.
	 */
	List<CyFileFilter> getAvailableWriterFilters();

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified property
	 * object to the specified file of the specified file type. 
	 * @param property The property object to be written.
	 * @param filter The {@link CyFileFilter} that defines the type of file to be written.
	 * @param file The file to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified property
	 * object to the specified file of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(Object property, CyFileFilter filter, File file) throws Exception;

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified property
	 * object to the specified output stream of the specified file type. 
	 * @param property The property object to be written.
	 * @param filter The {@link CyFileFilter} that defines the type of file to be written.
	 * @param os The output stream to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified property
	 * object to the specified output stream of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(Object property, CyFileFilter filter, OutputStream os) throws Exception;
}
