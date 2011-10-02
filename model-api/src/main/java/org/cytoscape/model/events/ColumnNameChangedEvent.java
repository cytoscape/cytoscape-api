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
package org.cytoscape.model.events;


import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyTable;


/**
 * This event signals that a columns name has been changed.
 *
 */
public class ColumnNameChangedEvent extends AbstractCyEvent<CyTable> {
	private final String oldColumnName;
	private final String newColumnName;

	/**
	 * Constructs event.
	 * @param source the source table of the column.
	 * @param oldColumnName the columns old name before the change.
	 * @param newColumnName the name the column name was changed to.
	 */
	public ColumnNameChangedEvent(final CyTable source, final String oldColumnName,
				      final String newColumnName)
	{
		super(source, ColumnNameChangedListener.class);

		if (oldColumnName == null)
			throw new NullPointerException("\"oldColumnName\" must not be null!");
		this.oldColumnName = oldColumnName;

		if (newColumnName == null)
			throw new NullPointerException("\"newColumnName\" must not be null!");
		this.newColumnName = newColumnName;
	}

	/**
	 * Returns the old name of the column.
	 * @return the old name of the column
	 */
	public String getOldColumnName() {
		return oldColumnName;
	}

	/**
	 * Returns the new name of the column.
	 * @return the new name of the column
	 */
	public String getNewColumnName() {
		return newColumnName;
	}
}
