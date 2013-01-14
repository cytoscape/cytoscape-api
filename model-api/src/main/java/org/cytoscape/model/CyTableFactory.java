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
 * An interface describing a factory used for creating 
 * {@link CyTable} objects.  This factory will be
 * provided as a service through Spring/OSGi.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyTableFactory {
	/** 
	 * A description of the initial size of the table.
	 * Used only for the initial construction of the table
	 * and does not in any way limit the eventual size
	 * of the table!
	 */
	enum InitialTableSize {
		/** Small - about 100 entries. */
		SMALL(100),
		/** Medium - about 1000 entries. */
		MEDIUM(1000),
		/** Large - about 10,000 entries. */
		LARGE(10000),
		;

		private final int size; 
		private InitialTableSize(int size) {
			this.size = size;
		}

		/**
		 * Returns the actual size value for the given enum.
		 * @return the actual size value for the given enum.
		 */
		public int getSize() {
			return size;
		}
	}

	/**
	 * Creates a CyTable object with the specified name, primary key, visibility, and mutability.
	 * @param title The name of the CyTable.
	 * @param primaryKey The name primaryKey column for this table. 
	 * @param primaryKeyType The type of the primaryKey column for this table. 
	 * @param pub Whether or not the CyTable should be public.
	 * @param isMutable if true, the table can be deleted later on, otherwise it can't
	 * @param initialSize a rough guess as to the expected size of the table 
	 * @return A new {@link CyTable} with the specified name that is either public or not (see
	 *         {@link CyTable#isPublic}.
	 */
	CyTable createTable(String title, String primaryKey, Class<?> primaryKeyType, boolean pub,
			    boolean isMutable, InitialTableSize initialSize);
	/**
	 * Creates a CyTable object with the specified name, primary key, visibility, and mutability.
	 * @param title The name of the CyTable.
	 * @param primaryKey The name primaryKey column for this table. 
	 * @param primaryKeyType The type of the primaryKey column for this table. 
	 * @param pub Whether or not the CyTable should be public.
	 * @param isMutable if true, the table can be deleted later on, otherwise it can't
	 * @return A new {@link CyTable} with the specified name that is either public or not (see
	 *         {@link CyTable#isPublic}.
	 */
	CyTable createTable(String title, String primaryKey, Class<?> primaryKeyType, boolean pub,
			    boolean isMutable);
}
