package org.cytoscape.view.vizmap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkTableManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.view.model.table.CyColumnView;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractTableVisualMappingManagerTest {

	protected TableVisualMappingManager tvmm;

	@Before
	public void setUp() throws Exception {
		VisualStyleFactory factory = mock(VisualStyleFactory.class);
		VisualStyle dummyDefaultStyle = mock(VisualStyle.class);
		when(factory.createVisualStyle("default")).thenReturn(dummyDefaultStyle);
		
		CyEventHelper eventHelper = mock(CyEventHelper.class);
		CyNetworkTableManager networkTableManager = mock(CyNetworkTableManager.class);
		CyServiceRegistrar registrar = mock(CyServiceRegistrar.class);
		when(registrar.getService(CyEventHelper.class)).thenReturn(eventHelper);
		when(registrar.getService(CyNetworkTableManager.class)).thenReturn(networkTableManager);
		
		tvmm = createTableVisualMappingManager(factory, registrar);
	}

	
	protected abstract TableVisualMappingManager createTableVisualMappingManager(VisualStyleFactory factory, CyServiceRegistrar serviceRegistrar);
	
	
	private static CyColumnView createColView() {
		CyColumnView view = mock(CyColumnView.class);
		CyColumn model = mock(CyColumn.class);
		CyTable table = mock(CyTable.class);
		when(view.getModel()).thenReturn(model);
		when(model.getTable()).thenReturn(table);
		return view;
	}
	
	
	
	@Test
	public void testTableVisualMappingManager() {
		assertNotNull(tvmm);
		assertNotNull(tvmm.getDefaultVisualStyle());
	}
	
	
	@Test
	public void testGetAndSetDirectVisualStyle() {
		VisualStyle style1 = mock(VisualStyle.class);
		VisualStyle style2 = mock(VisualStyle.class);
		VisualStyle style3 = mock(VisualStyle.class);
		
		CyColumnView view1 = createColView();
		CyColumnView view2 = createColView();
		CyColumnView view3 = createColView();
		
		assertNotNull(tvmm.getAllVisualStyles());
		assertNotNull(tvmm.getAllVisualStylesMap());
		assertTrue(tvmm.getAllVisualStyles().isEmpty());
		assertTrue(tvmm.getAllVisualStylesMap().isEmpty());
		
		tvmm.setVisualStyle(view1, style1);
		tvmm.setVisualStyle(view2, style2);

		assertEquals(2, tvmm.getAllVisualStyles().size());
		assertEquals(2, tvmm.getAllVisualStylesMap().size());
		assertSame(style1, tvmm.getVisualStyle(view1));
		assertSame(style2, tvmm.getVisualStyle(view2));
		assertNull(tvmm.getVisualStyle(view3));
		
		tvmm.setVisualStyle(view3, style3);
		assertEquals(3, tvmm.getAllVisualStyles().size());
		assertEquals(3, tvmm.getAllVisualStylesMap().size());
		assertEquals(style3, tvmm.getVisualStyle(view3));
	}
	
	
	@Test
	public void testNetworkAssociations() {
		VisualStyle networkStyle = mock(VisualStyle.class);
		VisualStyle colStyle1 = mock(VisualStyle.class);
		VisualStyle colStyle2 = mock(VisualStyle.class);
		VisualStyle colStyle3 = mock(VisualStyle.class);
		
		assertNotNull(tvmm.getAssociations(networkStyle));
		assertTrue(tvmm.getAssociations(networkStyle).isEmpty());
		
		tvmm.setAssociatedVisualStyle(networkStyle, CyNode.class, "col1", colStyle1);
		tvmm.setAssociatedVisualStyle(networkStyle, CyNode.class, "col2", colStyle2);
		tvmm.setAssociatedVisualStyle(networkStyle, CyNode.class, "col3", colStyle3);
		tvmm.setAssociatedVisualStyle(networkStyle, CyEdge.class, "col3", colStyle3); // Map colStyle3 to more than one column
		
		assertSame(colStyle1, tvmm.getAssociatedColumnVisualStyle(networkStyle, CyNode.class, "col1"));
		assertSame(colStyle2, tvmm.getAssociatedColumnVisualStyle(networkStyle, CyNode.class, "col2"));
		assertSame(colStyle3, tvmm.getAssociatedColumnVisualStyle(networkStyle, CyNode.class, "col3"));
		assertSame(colStyle3, tvmm.getAssociatedColumnVisualStyle(networkStyle, CyEdge.class, "col3"));
		
		assertEquals(1, tvmm.getAssociations(colStyle1).size());
		assertTrue(tvmm.getAssociations(colStyle1).contains(new StyleAssociation(networkStyle, CyNode.class, "col1", colStyle1)));
		
		assertEquals(1, tvmm.getAssociations(colStyle2).size());
		assertTrue(tvmm.getAssociations(colStyle2).contains(new StyleAssociation(networkStyle, CyNode.class, "col2", colStyle2)));
		
		assertEquals(2, tvmm.getAssociations(colStyle3).size());
		assertTrue(tvmm.getAssociations(colStyle3).contains(new StyleAssociation(networkStyle, CyNode.class, "col3", colStyle3)));
		assertTrue(tvmm.getAssociations(colStyle3).contains(new StyleAssociation(networkStyle, CyEdge.class, "col3", colStyle3)));
		
		Set<StyleAssociation> allStyleAssociations = tvmm.getAllStyleAssociations();
		assertEquals(4, allStyleAssociations.size());
		assertTrue(allStyleAssociations.contains(new StyleAssociation(networkStyle, CyNode.class, "col1", colStyle1)));
		assertTrue(allStyleAssociations.contains(new StyleAssociation(networkStyle, CyNode.class, "col2", colStyle2)));
		assertTrue(allStyleAssociations.contains(new StyleAssociation(networkStyle, CyNode.class, "col3", colStyle3)));
		assertTrue(allStyleAssociations.contains(new StyleAssociation(networkStyle, CyEdge.class, "col3", colStyle3)));
		
		var set = tvmm.getAssociatedNetworkVisualStyles(colStyle1);
		assertEquals(1, set.size());
		assertSame(networkStyle, set.iterator().next());
		
		assertNull(tvmm.getAssociatedColumnVisualStyle(networkStyle, CyEdge.class, "blah"));
		
		var styles = tvmm.getAssociatedColumnVisualStyles(networkStyle, CyNode.class);
		assertEquals(3, styles.size());
		assertSame(colStyle1, styles.get("col1"));
		assertSame(colStyle2, styles.get("col2"));
		assertSame(colStyle3, styles.get("col3"));
	}
	
	
	@Test
	public void testRemoveNetworkAssociations() {
		VisualStyle networkStyle = mock(VisualStyle.class);
		VisualStyle colStyle1 = mock(VisualStyle.class);
		VisualStyle colStyle2 = mock(VisualStyle.class);
		VisualStyle colStyle3 = mock(VisualStyle.class);
		
		assertNotNull(tvmm.getAssociations(networkStyle));
		assertTrue(tvmm.getAssociations(networkStyle).isEmpty());
		
		tvmm.setAssociatedVisualStyle(networkStyle, CyNode.class, "col1", colStyle1);
		tvmm.setAssociatedVisualStyle(networkStyle, CyNode.class, "col2", colStyle2);
		tvmm.setAssociatedVisualStyle(networkStyle, CyNode.class, "col3", colStyle3);
		tvmm.setAssociatedVisualStyle(networkStyle, CyEdge.class, "col3", colStyle3);
		
		// Remove a style
		assertEquals(4, tvmm.getAllStyleAssociations().size());
		tvmm.setAssociatedVisualStyle(networkStyle, CyNode.class, "col1", null);
		assertEquals(3, tvmm.getAllStyleAssociations().size());
		
		// Replace a style
		VisualStyle colStyle4 = mock(VisualStyle.class);
		tvmm.setAssociatedVisualStyle(networkStyle, CyNode.class, "col2", colStyle4);
		assertSame(colStyle4, tvmm.getAssociatedColumnVisualStyle(networkStyle, CyNode.class, "col2"));
		assertEquals(3, tvmm.getAllStyleAssociations().size());
	}
	
	
	@Test
	public void testAssociationTableType() {
		VisualStyle networkStyle = mock(VisualStyle.class);
		VisualStyle colStyle1 = mock(VisualStyle.class);
		
		try {
			tvmm.setAssociatedVisualStyle(networkStyle, CyNetwork.class, "col1", colStyle1);
			fail();
		} catch(IllegalArgumentException e) {}
		
		try {
			tvmm.setAssociatedVisualStyle(networkStyle, CyTable.class, "col1", colStyle1);
			fail();
		} catch(IllegalArgumentException e) {}
	}
	
}



