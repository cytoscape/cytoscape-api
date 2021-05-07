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

import java.io.OutputStream;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

/**
 * A specialization of {@link CyWriterFactory} that allows a 
 * {@link CyNetworkView} or {@link CyNetwork} to be specified 
 * and written to an OutputStream.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CyNetworkViewWriterFactory extends CyWriterFactory {

	/**
	 * Creates a single Task that will write the specified network to the
	 * specified OutputStream. 
	 * @param os The stream to write to. 
	 * @param view The {@link CyNetworkView} to be written.
	 */
	CyWriter createWriter(OutputStream os, CyNetworkView view);

	/**
	 * Creates a single Task that will write the specified network to the
	 * specified OutputStream. 
	 * @param os The stream to write to. 
	 * @param network The {@link CyNetwork} to be written.
	 */
	CyWriter createWriter(OutputStream os, CyNetwork network);
}
