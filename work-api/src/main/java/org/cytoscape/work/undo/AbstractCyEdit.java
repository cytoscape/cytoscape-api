package org.cytoscape.work.undo;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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


/**
 * A small class used to create new undoable edits.  All
 * you have to do is implement the undo() and redo() methods.
 * <br/>
 * Cytoscape's undo scheme is much simpler than Swing's and 
 * we may in the future add additional functionality to this
 * class to support more advanced features.  If we do, we 
 * will make sure that the features are fully backwards compatible.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule work-api
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
