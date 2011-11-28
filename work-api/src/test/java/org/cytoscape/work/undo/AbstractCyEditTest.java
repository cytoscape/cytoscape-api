/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

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
