/*
  File: CyAbstractEdit.java

  Copyright (c) 2006, 2011, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.work.undo;


/**
 * A small class used to create new undoable edits.  All
 * you have to do is implement the undo() and redo() methods.
 * <br/>
 * Cytoscape's undo scheme is much simpler than Swing's and 
 * we may in the future add additional functionality to this
 * class to support more advanced features.  If we do, we 
 * will make sure that the features are fully backwards compatible.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractCyEdit {

	private final String presentationName;
		
	/**
	 * Constructor.
	 * @param presentationName A short, human-readable description of this edit that will be used
	 * in logs and in menu item titles.
	 */
	public AbstractCyEdit(String presentationName) {
        if (presentationName == null)
			throw new NullPointerException("Presentation name must not be null.");

		this.presentationName = presentationName;
	}

	/**
	 * Returns a human-readable description of this edit that will be used in
	 * logs and other descriptions of the edit. 
	 * @return a human-readable description of this edit that will be used in
	 * logs and other descriptions of the edit. 
	 */
	public final String getPresentationName() {
		return presentationName;
	}

	/**
	 * Returns a human-readable description of this edit that will be used in
	 * redo menu items. 
	 * @return a human-readable description of this edit that will be used in
	 * redo menu items. 
	 */
	public final String getRedoPresentationName() {
		return "Redo: " + presentationName;
	}

	/**
	 * Returns a human-readable description of this edit that will be used in
	 * undo menu items. 
	 * @return a human-readable description of this edit that will be used in
	 * undo menu items. 
	 */
	public final String getUndoPresentationName() {
		return "Undo: " + presentationName;
	}

	/**
	 * The method that performs the undo.
	 */
	public abstract void undo();

	/**
	 * The method that performs the redo.
	 */
	public abstract void redo();
}
