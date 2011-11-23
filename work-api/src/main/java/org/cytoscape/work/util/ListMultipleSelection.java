
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
import java.util.Arrays;



/**
 * List with items of type <code>T</code> : one or more items can be selected.
 *
 * @param <T>  type of items that will be listed.
 * @CyAPI.Final.Class
 */
public final class ListMultipleSelection<T> extends ListSelection<T> {
	
	/**
	 * The list of items that have been selected.
	 */
	private List<T> selected;

	/**
	 * Creates a new ListMultipleSelection object.
	 *
	 *<p><pre>
	 * <b>example</b> :
	 * 
	 * <code>ListMultipleSelection<String> lms = new ListMultipleSelection<String>("1","2","3","4");</code>
	 * </pre></p>
	 * 
	 * @param values An arbitrary number of values of type T items that can be selected.
	 */
	public ListMultipleSelection(final T ... values) {
		super(Arrays.asList(values));
		selected = new ArrayList<T>();
	}

	/**
	 * Creates a new ListMultipleSelection object.
	 * 
	 * <p><pre>
	 *<b>example</b> :
	 *
	 *<code>
	 *	java.util.List<String> list = new jave.util.ArrayList<String>();
	 *	list.add("1");
	 *	list.add("2");
	 *	list.add("3");
	 *	list.add("4");
	 *
	 *	ListMultipleSelection<String> lss = new ListMultipleSelection<String>(list);
	 *</code>
	 *</pre></p>
	 *
	 * @param values The list of values of type T that can be selected.  The list
	 * may be empty.
	 */
	public ListMultipleSelection(final List<T> values) {
		super(values);
		selected = new ArrayList<T>();
	}

	/**
	 * Get the items that are currently selected.
	 *
	 * @return a list of selected items.
	 */
	public List<T> getSelectedValues() {
		return new ArrayList<T>(selected);
	}

	/**
	 *  Set the <code>T</code> items as selected in the ListMultipleSelection object.
	 *
	 * @param vals the selected items.
	 */
	public void setSelectedValues(final List<T> vals) {
		if (vals == null)
			throw new NullPointerException("value list is null");

		for (T v : vals)
			if (!values.contains(v))
				throw new IllegalArgumentException("value not contained in list of possible values\n possible items = "+this.getPossibleValues());

		selected = new ArrayList<T>(vals);
	}
}
