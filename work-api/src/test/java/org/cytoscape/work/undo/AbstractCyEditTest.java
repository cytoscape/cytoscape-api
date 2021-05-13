package org.cytoscape.work.undo;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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


import static org.junit.Assert.*;
import org.junit.Test;

import org.cytoscape.work.undo.AbstractCyEdit;


public abstract class AbstractCyEditTest {
	protected AbstractCyEdit undoableEdit;

	public abstract void saveState();
	public abstract void changeState();
	public abstract boolean currentStateIsIdenticalToSavedState();

	@Test
	public final void testPresentationName() {
		assertTrue("presentation name is non-empty", !undoableEdit.getPresentationName().isEmpty());
		assertTrue("redo presentation name is non-empty", !undoableEdit.getRedoPresentationName().isEmpty());
		assertTrue("undo presentation name is non-empty", !undoableEdit.getUndoPresentationName().isEmpty());
		assertTrue("redo and undo presentation name are different", undoableEdit.getRedoPresentationName() != undoableEdit.getUndoPresentationName());
	}

	@Test
	public final void testUndo() {
		saveState();
		changeState();
		undoableEdit.undo();
		assertTrue("state is back to orginal after undo", currentStateIsIdenticalToSavedState());
	}

	@Test
	public final void testRedo() {
		saveState();
		changeState();
		undoableEdit.undo();
		undoableEdit.redo();
		assertTrue("state is different from orginal after undo followed by redo", !currentStateIsIdenticalToSavedState());
	}
}
