package org.cytoscape.task.write;

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

import java.io.File;

import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This task factory provides a task iterator for writing a specified
 * table to a specified file.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface ExportTableTaskFactory extends TableTaskFactory {
	
	/**
	 * Returns a task factory that write the specified table to the specified file.
	 * @param table The table to be written.
	 * @param file The file the table will be written to.
	 * @return a task factory that write the specified table to the specified file.
	 */
	public TaskIterator createTaskIterator(CyTable table, File file);
}
