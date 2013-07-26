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

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskFactory;

/**
 * This interface provides a task iterator for merging two data tables.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface MergeDataTableTaskFactory extends TaskFactory {

	/**
	 * Creates a task iterator for merging two data tables.
	 * @param sourceTable The source table where the data to be be merged comes from
	 * @param targetTable The target table where the data will be merged to
	 * @param sourceColumnsList The list of columns in the source table that will be merged to the target table
	 * @param sourceKeyColumn The key column in the source table that will be used to merge the columns in that table to the target table
	 * @param mergeColumnVirtual Tells whether the new merged column will be virtual (true) or not
	 * @param mapToNetworks Tells if the target table will a network data table(true) or unassigned table(false)
	 * @param selectedNetworksOnly Tells if the merge is going to be on selected networks(true) or to a network collection (false)
	 * @param networkList The list of networks to merge the data if the selectedNetworksOnly and the mapToNetworks flags are true
	 * @param rootNetwork The root network of the chosen network collection to merge the data 
	 * @param targetJoinColumn The key column in the target table data to be used for merging the data
	 * @param tableType The type of the table where the data will be merged, in case the source table is merged to a network data table
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final CyTable sourceTable,final CyTable targetTable,List<String> sourceColumnsList, String sourceKeyColumn,boolean mergeColumnVirtual, boolean mapToNetworks,
			boolean selectedNetworksOnly,  List<CyNetwork> networkList, CyRootNetwork rootNetwork, CyColumn targetJoinColumn, Class<? extends CyIdentifiable> tableType);
	
}
