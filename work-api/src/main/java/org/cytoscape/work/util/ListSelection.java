package org.cytoscape.work.util;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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


/**
 * A ListSelection object.
 * 
 * @param <T>  type of item that will be listed.
 */
public class ListSelection<T> {

	
	/**
	 * Declares a List of items of type <code>T</code>.
	 */
	protected List<T> values;

	/**
 	 * The list of listeners to inform if something changes
 	 */
	private List<ListChangeListener<T>> listeners = null;


	/**
	 * Creates a new ListSelection object.
	 *
	 * @param values List of items of type <code>T</code> that contains the one(s) that is(are) going to be selected.
	 * The list of values my be empty.
	 */
	public ListSelection(final List<T> values) {
		if (values == null)
			throw new NullPointerException("values is null.");

		this.values = values;
	}

	/**
	 * Changes the set of possible values in the list
	 *
	 * @param values List of items of type <code>T</code> that contains the one(s) that is(are) going to be selected.
	 * The list of values my be empty.
	 */
	public void setPossibleValues(final List<T> values) {
		if (values == null)
			throw new NullPointerException("values is null.");

		this.values = values;
		listChanged();
	}
	
	/**
	 * To get all the items of the <code>List<T> values</code>.
	 *
	 * @return  an enumeration of all the items.
	 */
	public List<T> getPossibleValues() {
		return new ArrayList<T>(values);
	}

	/**
 	 * Adds a listener that will listen for changes to this object
 	 *
 	 *  @param changeListener listener object
 	 */
	public void addListener(ListChangeListener<T> changeListener) {
		if (listeners == null)
			listeners = new ArrayList<ListChangeListener<T>>();

		synchronized (listeners) {
			listeners.add(changeListener);
		}
	}

	/**
 	 * Removes a listener from the list that will listen for changes to this object
 	 *
 	 *  @param changeListener listener object
 	 */
	public void removeListener(ListChangeListener<T> changeListener) {
		if (listeners != null && listeners.contains(changeListener))
			synchronized (listeners) {
				listeners.remove(changeListener);
			}
	}

	/**
 	 * Returns the list of listeners that will listen for changes to this object
 	 *
 	 *  @return the list of listeners
 	 */
	public List<ListChangeListener<T>> getListeners() {
		if (listeners == null)
			listeners = new ArrayList<ListChangeListener<T>>();
		return listeners;
	}

	/**
 	 * Alert all listeners that the list has changed
 	 */
	protected void listChanged() {
		if (listeners == null) return;
		synchronized (listeners) {
			List<ListChangeListener<T>> l = new ArrayList<ListChangeListener<T>>(listeners);
			for (ListChangeListener<T> listener: l) {
				listener.listChanged(this);
			}
		}
	}

	/**
 	 * Alert all listeners that the selection has changed
 	 */
	protected void selectionChanged() {
		if (listeners == null) return;
		synchronized (listeners) {
			List<ListChangeListener<T>> l = new ArrayList<ListChangeListener<T>>(listeners);
			for (ListChangeListener<T> listener: l) {
				listener.selectionChanged(this);
			}
		}
	}
}
