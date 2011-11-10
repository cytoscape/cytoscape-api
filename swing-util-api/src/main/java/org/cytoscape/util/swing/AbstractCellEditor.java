/*
 * The contents of this file are subject to the Sapient Public License
 * Version 1.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://carbon.sf.net/License.html.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Original Code is The Carbon Component Framework.
 *
 * The Initial Developer of the Original Code is Sapient Corporation
 *
 * Copyright (C) 2003 Sapient Corporation. All Rights Reserved.
 */
package org.cytoscape.util.swing;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import java.util.EventObject;


/**
 * #ASKMIKE Document me's
 * @CyAPI.Abstract.Class
 */
public class AbstractCellEditor implements CellEditor {
	protected EventListenerList listenerList = new EventListenerList();

	/**
	 *  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public Object getCellEditorValue() {
		return null;
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public boolean isCellEditable(EventObject e) {
		return true;
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @param anEvent DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public boolean shouldSelectCell(EventObject anEvent) {
		return false;
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public boolean stopCellEditing() {
		return true;
	}

	/**
	 *  DOCUMENT ME!
	 */
	public void cancelCellEditing() {
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @param l The CellEditorListener to add.
	 */
	public void addCellEditorListener(CellEditorListener l) {
		listenerList.add(CellEditorListener.class, l);
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @param l The CellEditorListener to remove.
	 */
	public void removeCellEditorListener(CellEditorListener l) {
		listenerList.remove(CellEditorListener.class, l);
	}

	/**
	 * Notify all listeners that have registered interest for
	 * notification on this event type.
	 * @see EventListenerList
	 */
	protected void fireEditingStopped() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == CellEditorListener.class) {
				((CellEditorListener) listeners[i + 1]).editingStopped(new ChangeEvent(this));
			}
		}
	}

	/**
	 * Notify all listeners that have registered interest for
	 * notification on this event type.
	 * @see EventListenerList
	 */
	protected void fireEditingCanceled() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == CellEditorListener.class) {
				((CellEditorListener) listeners[i + 1]).editingCanceled(new ChangeEvent(this));
			}
		}
	}
}
