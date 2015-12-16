package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2015 The Cytoscape Consortium
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

/**
 * Implementations of CyJobMarshaller will marshall the data required
 * for the particular middleware.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */

import java.util.List;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;

/**
 * CyJobMarshallers are responsible for converting Cytoscape input data into the
 * format appropriate to send to the external job.  The resulting {@link CyJobData}
 * objects returned from the marshaller are assumed to have the correct symantics
 * for the backend service.
 */
public interface CyJobMarshaller {
	
	/**
	 * Create a {@link CyJobData} object for the list of networks, nodes, and edges
	 * provided in the arguments.  Note that it is assumed that the networks, nodes,
	 * and edges are all in the same {@link CyRootNetwork} as the network argument.
	 *
	 * @param network The network that contains the nodes and edges passed in data.
	 * If data contains any networks, they are assumed to be in the {@link CyRootNetwork}
	 * as this.
	 * @param data The list of nodes, edges, and networks to be included
	 * @return a {@link CyJobData} object with all of the data ready for serialization
	 * and transmission to the external execution environment.
	 */
	public CyJobData marshalModel(CyNetwork network, List<? extends CyIdentifiable> data);

	/**
	 * Create a {@link CyJobData} object for the list of node and edge views
	 * provided in the arguments.
	 *
	 * @param view The {@link CyNetworkView} that contains the node and edge views passed in data.
	 * @param data The list of node and edge views.
	 * @return a {@link CyJobData} object with all of the data ready for serialization
	 * and transmission to the external execution environment.
	 */
	public CyJobData marshalView(CyNetworkView view, List<? extends CyIdentifiable> data);

	/**
	 * Create a {@link CyJobData} object for the table or slice of the table (as specified
	 * by the list of rows and columns) specified in the arguments.
	 *
	 * @param table The {@link CyTable} to marshal into the {@link CyJobData} object.
	 * @param rows The list of {@link CyRow}s to include.  If this is empty or null, include all rows
	 * @param columns The list of columns to include.  If this is empty or null, include all columns
	 * @return a {@link CyJobData} object with all of the data ready for serialization
	 * and transmission to the external execution environment.
	 */
	public CyJobData marshalSubTable(CyTable table, List<CyRow> rows, List<String> columns);
}
