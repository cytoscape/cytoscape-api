package org.cytoscape.work.swing.undo;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
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

import org.cytoscape.work.undo.UndoSupport;

import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEditSupport;


/** 
 * In general, developers should NOT use this interface or the classes
 * it provides access to to post edits and should use {@link UndoSupport} instead. 
 * This interface is really only meant for those handling undo/redo requests 
 * in a swing environment and this interface provides access to the necessary
 * Swing Undo services to do so.
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule work-swing-api
 */
public interface SwingUndoSupport extends UndoSupport {
	/** Returns the <code>UndoManager</code> associated with this <code>UndoSupport</code> instance.
	 * @return the <code>UndoManager</code> associated with this <code>UndoSupport</code> instance.
	 */
	UndoManager getUndoManager(); 

	/** Returns the <code>UndoableEditSupport</code> associated with this <code>UndoSupport</code> instance.
	 * @return the <code>UndoableEditSupport</code> associated with this <code>UndoSupport</code> instance.
	 */
	UndoableEditSupport getUndoableEditSupport();
}
