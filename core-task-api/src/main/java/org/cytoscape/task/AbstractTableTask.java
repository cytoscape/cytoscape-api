package org.cytoscape.task;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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
import org.cytoscape.work.AbstractTask;


/** A base class for Tasks that need to operate on an entire {@link CyTable}. 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractTableTask extends AbstractTask {
	
	/** The table that descendants of this class will operate on. */
	protected final CyTable table;
	
	/** Initializes a Task that needs to operate on a {@link CyTable}.
	 *  @param table  a non-null CyTable
	 */
	public AbstractTableTask(CyTable table) {
		if (table == null)
			throw new NullPointerException("\"table\" parameter must *never* be null.");
		this.table = table;
	}
}