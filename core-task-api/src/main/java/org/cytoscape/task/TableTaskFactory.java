package org.cytoscape.task;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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

import org.cytoscape.model.CyTable;
import org.cytoscape.work.TaskIterator;


/**
 * A task factory that creates one or more tasks that operate on the specified CyTable.
 * @CyAPI.Spi.Interface
 */
public interface TableTaskFactory {

	/** 
	 * Used to provision this factory with a {@link CyTable} that will be used to create tasks.
	 * @param table a non-null CyTable
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute.
	 */
	TaskIterator createTaskIterator(CyTable table);

    /**
     * Returns true if this task factory is ready to produce a TaskIterator.
	 * @param table a non-null CyTable
     * @return true if this task factory is ready to produce a TaskIterator.
     */
	boolean isReady(CyTable table);
}
