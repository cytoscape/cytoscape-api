
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

import java.util.Arrays;
import java.util.List;


/**
 * List with items of type <code>T</code> : only 1 item can be selected
 *
 * @param <T>  type of items that will be listed
 */
public class ListSingleSelection<T> extends ListSelection<T> {
	
	/**
	 * The item that will be selected
	 */
	private T selected;

	/**
	 * Creates a new ListSingleSelection object
	 * 
	 * <p><pre>
	 * <b>example</b> :
	 * 
	 * <code>ListSingleSelection<String> lss = new ListSingleSelection<String>("1","2","3","4")</code>
	 * </pre></p>
	 *
	 * @param values array of <code>T</code> items that can be selected
	 */
	public ListSingleSelection(final T ... values) {
		super(Arrays.asList(values));
	}

	/**
	 * Creates a new ListSingleSelection object
	 *
	 *<p><pre>
	 *<b>example</b> :
	 *
	 *<code>
	 *	java.util.List<String> list = new jave.util.ArrayList<String>();
	 *	list.add("1");
	 *	list.add("2");
	 *	list.add("3");
	 *	list.add("4");
	 *
	 *	ListSingleSelection<String> lss = new ListSingleSelection<String>(list);
	 *</code>
	 *</pre></p>
	 * @param values  a list of <code>values</code> of type<code>T</code> that can be selected
	 */
	public ListSingleSelection(final List<T> values) {
		super(values);
	}

	/**
	 * Get the item that is currently selected
	 *
	 * @return  the selected item
	 */
	public T getSelectedValue() {
		return selected;
	}

	/**
	 *  Set the <code>val</code> item of type <code>T</code> as selected in the ListSingleSelection
	 *
	 * @param val the selected item
	 */
	public void setSelectedValue(T val) {
		if (!values.contains(val))
			throw new IllegalArgumentException("value not contained in list of possible values\n possible items = "+this.getPossibleValues());

		selected = val;
	}
}
