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


/** A base class for Tasks that need to operate on an entire CyTable column. 
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractTableColumnTask extends AbstractTask {
	
	/** The {@link CyColumn} that descendants will operate on. */
	protected final CyColumn column;

	/** Initializes a Task that needs to operate on a CyTable column.
	 *  @param column  a non-null CyColumn
	 */
	public AbstractTableColumnTask(final CyColumn column) {
		if (column == null)
			throw new NullPointerException("\"column\" parameter must *never* be null.");
		this.column = column;
	}
}