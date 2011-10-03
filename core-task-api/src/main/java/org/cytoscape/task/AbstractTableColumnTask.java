/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.task;


import org.cytoscape.model.CyColumn;
import org.cytoscape.work.AbstractTask;


/** A base class for Tasks that need to operate on an entire CyTable column. */
public abstract class AbstractTableColumnTask extends AbstractTask {
	
	/** The {@link CyColumn} that descendants will operate on. */
	protected final CyColumn column;

	/** Initializes a Task that needs to operate on a CyTable column.
	 *  @param column  a non-null CyColumn
	 */
	public AbstractTableColumnTask(final CyColumn column) {
		if (column == null)
			throw new NullPointerException("\"column\" parameter must *never* be null!");
		this.column = column;
	}
}