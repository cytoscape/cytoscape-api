package org.cytoscape.command.util;

/*
 * #%L
 * Cytoscape Command Executor API (command-executor-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

/**
 * This class implements a wrapper for a List of table rows that
 * can be used by the Tunables mechanism.  Currently, it is only
 * implemented by command tunables so it will be ignored in
 * GUI (Swing) contexts.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule command-executor-api
 */
public class RowList {
	private CyTable table;
	private List<CyRow> rowlist;

	/**
 	 * Create a new RowList object with no initial table
 	 */
	public RowList() {
		table = null;
		rowlist = null;
	}

	/**
 	 * Create a new RowList object with an initial table
 	 *
 	 * @param targetTable the table this rowlist will be in
 	 */
	public RowList(CyTable targetTable) {
		this.table = targetTable;
		rowlist = null;
	}

	/**
 	 * Set the table for this rowlist
 	 *
 	 * @param table the table for this rowlist
 	 */
	public synchronized void setTable(CyTable table) {
		this.table = table;
	}

	/**
 	 * Get the table for this rowlist
 	 *
 	 * @return the table we're looking through
 	 */
	public CyTable getTable() {
		return this.table;
	}

	/**
 	 * Set the list of rows
 	 *
 	 * @param rowList the list of rows
 	 */
	public synchronized void setValue(List<CyRow> rowList) {
		this.rowlist = rowList;
	}

	/**
 	 * Get the list of rows
 	 *
 	 * @return the list of rows
 	 */
	public List<CyRow> getValue() {
		return rowlist;
	}

}
