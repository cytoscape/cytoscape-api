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



import org.junit.Before;
import org.junit.Test;

import org.cytoscape.work.undo.AbstractCyEdit;


public class SimpleUndoableEditTest extends AbstractCyEditTest {
	private int state = 0;
	private int savedState;

	class SimpleUndoableEdit extends AbstractCyEdit {
		SimpleUndoableEdit(final String presentationName) {
			super(presentationName);
		}

		public void undo() { --state; }
		public void redo() { ++state; }
	}

	@Before
	public void init() {
		undoableEdit = new SimpleUndoableEdit("simple");
	}

	@Override
	public void saveState() {
		savedState = state;
	}

	@Override
	public void changeState() {
		++state;
	}

	@Override
	public boolean currentStateIsIdenticalToSavedState() {
		return state == savedState;
	}

	@Test(expected=NullPointerException.class)
	public final void testNullConstructorArgument() {
		new SimpleUndoableEdit(null);
	}
}
