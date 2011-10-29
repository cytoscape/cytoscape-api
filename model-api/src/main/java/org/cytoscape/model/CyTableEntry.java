/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.model;


/**
 * CyTableEntry is an interface that indicates that the implementing
 * object can be considered a key into a table. In general, table
 * entries will be things like nodes, edges, and networks..
 * @CyAPI.Api.Interface
 */
public interface CyTableEntry extends Identifiable {
	/**
	 * A String column created by default for every CyTableEntry that
	 * holds the name of the entry.
	 * 
	 *  FIXME: create new enum for default attribute names?
	 */
	String NAME = "name";
	
	
	/**
	 * Returns the row for the specified table name for this object.
	 * @param tableName the name of the table from which to extract the row..
	 * @return the row in the table of the specified name for this object. 
	 */
	CyRow getCyRow(String tableName);

	/**
	 * A convenience method that returns the row in the default table 
	 * for this object. This method is equivalent to calling 
	 * getCyRow({@link CyNetwork#DEFAULT_ATTRS}).
	 * @return the row in the default table for this object. 
	 */
	CyRow getCyRow();
}
