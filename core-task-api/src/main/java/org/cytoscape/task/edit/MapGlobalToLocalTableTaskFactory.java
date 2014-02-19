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

import java.util.Collection;

import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for mapping a global to a local table.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface MapGlobalToLocalTableTaskFactory extends TableTaskFactory{

	/**
	 * Creates a task iterator for mapping a selected global table to a selected local table.
	 * The created task will run synchronously in the current thread and will not create a 
	 * new task monitor.
	 * @param globalTable The global table to map from. 
	 * @param localTable The local table to map to. 
	 * @return A task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final CyTable globalTable, final Collection<CyTable> localTables);

}
