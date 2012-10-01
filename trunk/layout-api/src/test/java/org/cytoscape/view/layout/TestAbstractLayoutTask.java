package org.cytoscape.view.layout;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.undo.UndoSupport;
import org.cytoscape.work.util.ListSingleSelection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAbstractLayoutTask {
	
	private UndoSupport undo; 
	private TaskMonitor taskMonitor;
	private CyNetworkView networkView; 
	private List<View<CyNode>> nodeViews; 

	@Before
	public void setUp() {
		undo = mock(UndoSupport.class);
		taskMonitor = mock(TaskMonitor.class);
		networkView = mock(CyNetworkView.class);

		nodeViews = new ArrayList<View<CyNode>>();
		nodeViews.add(mock(View.class));
		nodeViews.add(mock(View.class));
		nodeViews.add(mock(View.class));
		
		for (View<?> view : nodeViews) {
			when(view.getVisualProperty(BasicVisualLexicon.NODE_VISIBLE)).thenReturn(true);
		}

		when(networkView.getNodeViews()).thenReturn(nodeViews);
	}
	
	@Test
	public void testConstructWithEmptySet() throws Exception {
		LayoutTask task = new LayoutTask("asdf",networkView,CyLayoutAlgorithm.ALL_NODE_VIEWS,"homer",undo);
		assertEquals(task.nodesToLayOut.size(),nodeViews.size());
	}

	@Test
	public void testConstructWithNonEmptySet() throws Exception {
		Set<View<CyNode>> nvs = new HashSet<View<CyNode>>();
		nvs.add(mock(View.class));
		LayoutTask task = new LayoutTask("asdf",networkView,nvs,"homer",undo);
		assertEquals(task.nodesToLayOut.size(),nvs.size());
	}

//	@Test
	public void testUndoCalled() throws Exception {
		LayoutTask task = new LayoutTask("asdf",networkView,CyLayoutAlgorithm.ALL_NODE_VIEWS,"homer",undo);
		task.run(taskMonitor);
		verify(undo, times(1)).postEdit(any(LayoutEdit.class));
	}	

//	@Test 
	public void testNullUndo() throws Exception {
		LayoutTask task = new LayoutTask("asdf",networkView,CyLayoutAlgorithm.ALL_NODE_VIEWS,"homer",null);
		task.run(taskMonitor);
		// no null pointer exception throw!
	}	

//	@Test 
	public void testDoLayoutCalled() throws Exception {
		LayoutTask task = new LayoutTask("asdf",networkView,CyLayoutAlgorithm.ALL_NODE_VIEWS,"homer",undo);
		task.run(taskMonitor);
		assertTrue(task.called);
	}

//	@Test 
	public void testFitContentCalled() throws Exception {
		LayoutTask task = new LayoutTask("asdf",networkView,CyLayoutAlgorithm.ALL_NODE_VIEWS,"homer",undo);
		task.run(taskMonitor);
		verify(networkView, times(1)).fitContent();
	}

	private class LayoutTask extends AbstractLayoutTask {
		public LayoutTask(String name,
		                  CyNetworkView networkView,
		                  Set<View<CyNode>> nodesToLayOut,
		                  String layoutAttribute,
		                  UndoSupport undo) {
			super(name,networkView,nodesToLayOut,layoutAttribute,undo);
		}

		boolean called = false;

		public void doLayout(TaskMonitor taskMonitor) { called = true; }
	}
}
