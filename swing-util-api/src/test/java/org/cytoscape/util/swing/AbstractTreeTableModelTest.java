package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.objenesis.instantiator.basic.NewInstanceInstantiator;

public class AbstractTreeTableModelTest {
	
	AbstractTreeTableModel model;
	@Mock Object root;
	@Mock Object child1;
	@Mock Object child2;
	
	TreePath path;
	TreePath path2;
	
	@Mock TreeModelListener tListener;
	
	@Mock Object source;
	
	int[] childIndices = new int[]{1, 2};
	Object[] children = new Object[]{child1, child2};
	
	Object[] pathList = new Object[]{path, path2};

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		path=new TreePath(new Object[] {root, child1});
		path2=new TreePath(new Object[] {root, child2});
		pathList = new Object[]{path, path2};
		childIndices = new int[]{1, 2};
		children = new Object[]{child1, child2};
		model = new DummyTreeTableModel(root);
	}


	@Test
	public void testAbstractTreeTableModel() {
		assertNotNull(model);
	}

	@Test
	public void testGetRoot() {
		assertEquals(root, model.getRoot());
	}

	@Test
	public void testIsLeaf() {
		assertTrue(model.isLeaf(root));
	}

	@Test
	public void testValueForPathChanged() {
		model.valueForPathChanged(path, child1);
	}

	@Test
	public void testGetIndexOfChild() {
		assertEquals(-1, model.getIndexOfChild(root, child1));
	}

	@Test
	public void testAddTreeModelListener() {
		model.addTreeModelListener(tListener);
		assertEquals(1, model.listenerList.getListenerCount());
		model.removeTreeModelListener(tListener);
		assertEquals(0, model.listenerList.getListenerCount());
	}


	@Test
	public void testFireTreeNodesChanged() {
		model.addTreeModelListener(tListener);
		model.fireTreeNodesChanged(source, pathList, childIndices, children);
		
		verify(tListener, times(1)).treeNodesChanged(any(TreeModelEvent.class));
	}

	@Test
	public void testFireTreeNodesInserted() {
		model.addTreeModelListener(tListener);
		model.fireTreeNodesInserted(source, pathList, childIndices, children);
		
		verify(tListener, times(1)).treeNodesInserted(any(TreeModelEvent.class));
	}

	@Test
	public void testFireTreeNodesRemoved() {
		model.addTreeModelListener(tListener);
		model.fireTreeNodesRemoved(source, pathList, childIndices, children);
		
		verify(tListener, times(1)).treeNodesRemoved(any(TreeModelEvent.class));
	}

	@Test
	public void testFireTreeStructureChanged() {
		model.addTreeModelListener(tListener);
		model.fireTreeStructureChanged(source, pathList, childIndices, children);
		
		verify(tListener, times(1)).treeStructureChanged(any(TreeModelEvent.class));
	}

	@Test
	public void testGetColumnClass() {
		assertEquals(Object.class, model.getColumnClass(0));
	}

	@Test
	public void testIsCellEditable() {
		assertFalse(model.isCellEditable(root, 0));
	}

	@Test
	public void testSetValueAt() {
		Object aValue = null;
		model.setValueAt(aValue, root, 0);
	}
	
	private static final class DummyTreeTableModel extends AbstractTreeTableModel {

		private static final String DUMMY = "DUMMY";
		
		public DummyTreeTableModel(Object root) {
			super(root);
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getValueAt(Object node, int column) {
			return DUMMY;
		}

		@Override
		public Object getChild(Object arg0, int arg1) {
			return DUMMY;
		}

		@Override
		public int getChildCount(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

}
