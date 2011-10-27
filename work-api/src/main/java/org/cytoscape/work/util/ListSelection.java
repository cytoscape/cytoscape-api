
/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

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

package org.cytoscape.work.util;

import java.util.ArrayList;
import java.util.List;


/**
 * A ListSelection object
 * 
 * @param <T>  type of item that will be listed
 */
class ListSelection<T> {

	
	/**
	 * declares a List of items of type <code>T</code>
	 */
	protected final List<T> values;

	
	/**
	 * Creates a new ListSelection object.
	 *
	 * @param values List of items of type <code>T</code> that contains the one(s) that is(are) going to be selected.
	 * The list of values my be empty.
	 */
	public ListSelection(final List<T> values) {
		if (values == null)
			throw new NullPointerException("values is null!");

		this.values = values;
	}

	
	/**
	 * To get all the items of the <code>List<T> values</code>
	 *
	 * @return  an enumeration of all the items
	 */
	public List<T> getPossibleValues() {
		return new ArrayList<T>(values);
	}
}
