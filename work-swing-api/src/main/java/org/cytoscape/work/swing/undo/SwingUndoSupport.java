

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
package org.cytoscape.work.swing.undo;

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
