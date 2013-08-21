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

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * List with items of type <code>T</code> : only 1 item can be selected.
 *
 * @param <T>  type of items that will be listed.
 * @CyAPI.Final.Class 
 * @CyAPI.InModule work-api
 */
public final class ListSingleSelection<T> extends ListSelection<T> {

	private static final Logger logger = LoggerFactory.getLogger(ListSingleSelection.class);
	
	/**
	 * The item that will be selected.
	 */
	private T selected;

	/**
	 * Creates a new ListSingleSelection object.
	 * 
	 * <p><pre>
	 * <b>example</b> :
	 * 
	 * <code>ListSingleSelection<String> lss = new ListSingleSelection<String>("1","2","3","4")</code>
	 * </pre></p>
	 *
	 * @param values An arbitrary number of values of type T that can be selected.
	 */
	public ListSingleSelection(final T ... values) {
		super(Arrays.asList(values));
		if(!this.values.isEmpty())
			selected = this.values.get(0);
	}

	/**
	 * Creates a new ListSingleSelection object.
	 *
	 *<p><pre>
	 *<b>example</b> :
	 *
	 *<code>
	 *	List<String> list = ArrayList<String>();
	 *	list.add("1");
	 *	list.add("2");
	 *	list.add("3");
	 *	list.add("4");
	 *
	 *	ListSingleSelection<String> lss = new ListSingleSelection<String>(list);
	 *</code>
	 *</pre></p>
	 * @param values The list of values of type T that can be selected.  This list
	 * may be empty, but not null.
	 */
	public ListSingleSelection(final List<T> values) {
		super(values);
		if(!this.values.isEmpty())
			selected = this.values.get(0);
	}

	/**
	 * Get the item that is currently selected.
	 *
	 * @return  the selected item.
	 */
	public T getSelectedValue() {
		return selected;
	}

	/**
	 *  Set the <code>val</code> item of type <code>T</code> as selected in the ListSingleSelection.
	 *
	 * @param val the selected item.
	 */
	public void setSelectedValue(T val) {
		if (!values.contains(val))
			logger.warn("value not contained in list of possible values possible items = "
					+ Arrays.toString(getPossibleValues().toArray()));

		selected = val;
		selectionChanged(selected);
	}
}
