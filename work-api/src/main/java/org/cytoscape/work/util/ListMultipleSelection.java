package org.cytoscape.work.util;

/*
 * #%L
 * Cytoscape Work API (work-api)
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

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



/**
 * List with items of type <code>T</code> : one or more items can be selected.
 *
 * @param <T>  type of items that will be listed.
 * @CyAPI.Final.Class
 * @CyAPI.InModule work-api
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
		selectionChanged((T[])selected.toArray());
	}
}
