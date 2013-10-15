package org.cytoscape.task.edit;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
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

import java.util.List;

import org.cytoscape.io.read.CyTableReader;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for importing a table data in a network data table.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface ImportDataTableTaskFactory extends TableTaskFactory{
	
	/**
	 * Creates a task iterator for importing a table data in a network data table.
	 * @param globalTable The table where the data to be imported is stored
	 * @param selectedNetworksOnly Tells if the import is going to be on selected networks(true) or to a network collection (false)
	 * @param networkList The list of networks to import the data if the selectedNetworksOnly flag is true
	 * @param rootNetwork The root network of the chosen network collection to import the data 
	 * @param targetJoinColumn The key column in the network collection table data to import the data
	 * @param tableType The type of the table where the data will be imported
	 * @return a task iterator of type {@link TaskIterator}.
	 */

	TaskIterator createTaskIterator(final CyTable globalTable, boolean selectedNetworksOnly,  List<CyNetwork> networkList, CyRootNetwork rootNetwork, CyColumn targetJoinColumn, Class<? extends CyIdentifiable> tableType);

	/**
	 * Creates a task iterator for importing a table data in a network data table.
	 * @param reader The table reader where the data to be imported is stored
	 * @return a task iterator of type {@link TaskIterator}.
	 */

	TaskIterator createTaskIterator(CyTableReader reader);
}
