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

import org.cytoscape.model.CyColumn;
import org.cytoscape.work.AbstractTask;


/** A base class for Tasks that need to operate on a single CyTable cell. 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractTableCellTask extends AbstractTask {
	/** The {@link CyColumn} of the {@link org.cytoscape.model.CyTable} cell */
	protected final CyColumn column;
	/** The primary key of the {@link org.cytoscape.model.CyTable} cell */
	protected final Object primaryKeyValue;

	/** Initializes a Task that needs to operate on a CyTable cell entry.
	 *  @param column  a non-null CyColumn
	 *  @param primaryKeyValue  a non-null primary key value
	 */
	public AbstractTableCellTask(final CyColumn column, final Object primaryKeyValue) {
		if (column == null)
			throw new NullPointerException("\"column\" parameter must *never* be null.");
		this.column = column;
		if (primaryKeyValue == null)
			throw new NullPointerException("\"primaryKeyValue\" parameter must *never* be null.");
		this.primaryKeyValue = primaryKeyValue;
	}
}