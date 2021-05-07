package org.cytoscape.task;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import org.cytoscape.model.CyRow;
import org.cytoscape.work.AbstractTask;


/** Base class for all tasks that need to operate on a {@link CyRow}. 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractRowTask extends AbstractTask {
	/** The row that descendants will operate on. */
	protected final CyRow row;

	/** Base class constructor for all tasks that need to be provisioned with a CyRow.
	 *  @param row  a non-null CyRow that descendants will operate on
	 */
	public AbstractRowTask(final CyRow row) {
		if (row == null)
			throw new NullPointerException("CyRow is null");

		this.row = row;	
	}
}
