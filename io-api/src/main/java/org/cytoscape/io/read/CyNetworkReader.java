package org.cytoscape.io.read;

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

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.Task;

/** 
 * An extension of the Task interface that returns an array of
 * {@link org.cytoscape.model.CyNetwork} objects that are read as part
 * of the Task. An additional method provides an option for readers
 * to create {@link CyNetworkView}s from the networks it has read as
 * well. Instances of this interface are created by
 * InputStreamTaskFactory objects registered as OSGi services, which are in turn
 * processed by associated reader manager objects that distinguish
 * InputStreamTaskFactories based on the DataCategory associated with the
 * {@link org.cytoscape.io.CyFileFilter}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CyNetworkReader extends Task {

	/**
	 * Returns an array of networks read after executing the run() method of {@link Task}.
	 * @return an array of networks read after executing the run() method of {@link Task}.
	 */
	CyNetwork[] getNetworks();

	/**
	 * A method to build a {@link CyNetworkView} from one of the networks just read.
	 * @param network A network just read by the task and part of the getCyNetworks() array.
	 * @return A {@link CyNetworkView} created by the reader for the network specified.
	 */
	CyNetworkView buildCyNetworkView(final CyNetwork network);

}
