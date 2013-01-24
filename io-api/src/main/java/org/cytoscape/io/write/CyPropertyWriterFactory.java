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


import java.io.OutputStream;

/**
 * A specialization of {@link CyWriterFactory} that allows a property Object to
 * be specified and written. See {@link org.cytoscape.property.CyProperty} for details on the type of Object.
 * @CyAPI.Spi.Interface
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
