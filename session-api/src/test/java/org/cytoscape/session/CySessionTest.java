package org.cytoscape.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableEntry;
import org.cytoscape.model.CyTableMetadata;
import org.cytoscape.property.CyProperty;
import org.cytoscape.property.SimpleCyProperty;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.vizmap.VisualStyle;
import org.junit.Test;

public class CySessionTest {


	protected CySession session;


	@Test
	public void testDefaultGetNetworkViews() {
		session = new CySession.Builder().build();
		assertNotNull(session);
		assertNotNull(session.getNetworkViews());
	}

	@Test
	public void testSetNullNetworkViews() {
		session = new CySession.Builder().networkViews( null ).build();
		assertNotNull(session);
		assertNotNull(session.getNetworkViews());
		assertEquals(0,session.getNetworkViews().size());
	}

	@Test
	public void testSetNetworkViews() {
		CyNetworkView nv1 = mock(CyNetworkView.class); 
		CyNetworkView nv2 = mock(CyNetworkView.class); 

		Set<CyNetworkView> vs = new HashSet<CyNetworkView>();
		vs.add( nv1 );
		vs.add( nv2 );

		session = new CySession.Builder().networkViews( vs ).build();

		assertNotNull(session);
		assertNotNull(session.getNetworkViews());
		assertEquals(2,session.getNetworkViews().size());
		assertTrue(session.getNetworkViews().contains( nv1 ));
		assertTrue(session.getNetworkViews().contains( nv2 ));
	}

	@Test
	public void testDefaultGetTables() {
		session = new CySession.Builder().build();
		assertNotNull(session);
		assertNotNull(session.getTables());
	}

	@Test
	public void testSetNullTables() {
		session = new CySession.Builder().tables( null ).build();
		assertNotNull(session);
		assertNotNull(session.getTables());
		assertEquals(0,session.getTables().size());
	}

	@Test
	public void testSetTables() {
		CyTable t1 = mock(CyTable.class); 
		CyTable t2 = mock(CyTable.class); 

		Set<CyTableMetadata> ts = new HashSet<CyTableMetadata>();
		CyTableMetadata m1 = new CyTableMetadataImpl(t1);
		CyTableMetadata m2 = new CyTableMetadataImpl(t2);
		ts.add( m1 );
		ts.add( m2 );

		session = new CySession.Builder().tables( ts ).build();

		assertNotNull(session);
		assertNotNull(session.getTables());
		assertEquals(2,session.getTables().size());
		assertTrue(session.getTables().contains( m1 ));
		assertTrue(session.getTables().contains( m2 ));
	}
	
	@Test
	public void testDefaultGetViewVisualStyleMap() {
		session = new CySession.Builder().build();
		assertNotNull(session);
		assertNotNull(session.getViewVisualStyleMap());
	}

	@Test
	public void testSetNullViewVisualStyleMap() {
		session = new CySession.Builder().viewVisualStyleMap(null).build();
		assertNotNull(session);
		assertNotNull(session.getViewVisualStyleMap());
		assertEquals(0,session.getViewVisualStyleMap().size());
	}

	@Test
	public void testSetViewVisualStyleMap() {
		CyNetworkView nv1 = mock(CyNetworkView.class); 
		CyNetworkView nv2 = mock(CyNetworkView.class); 

		Map<CyNetworkView,String> vsm = new HashMap<CyNetworkView,String>();
		vsm.put(nv1,"vs1");
		vsm.put(nv2,"vs2");

		session = new CySession.Builder().viewVisualStyleMap(vsm).build();
		assertNotNull(session);
		assertNotNull(session.getViewVisualStyleMap());
		assertEquals(2,session.getViewVisualStyleMap().size());
		assertTrue(session.getViewVisualStyleMap().containsKey(nv1));
		assertTrue(session.getViewVisualStyleMap().containsKey(nv2));
		assertEquals("vs1",session.getViewVisualStyleMap().get(nv1));
		assertEquals("vs2",session.getViewVisualStyleMap().get(nv2));
	}
	
	@Test
	public void testDefaultGetCytoscapeProperties() {
		session = new CySession.Builder().build();
		assertNotNull(session);
		assertNotNull(session.getProperties());
	}

	@Test
	public void testSetNullCytoscapeProperties() {
		session = new CySession.Builder().properties(null).build();
		assertNotNull(session);
		assertNotNull(session.getProperties());
		assertEquals(0,session.getProperties().size());
	}

	@Test
	public void testSetCytoscapeProperties() {
		session = new CySession.Builder().properties(getFakeProps()).build();
		assertNotNull(session);
		checkProps(session.getProperties());
	}

	@Test
	public void testDefaultGetVizmap() {
		session = new CySession.Builder().build();
		assertNotNull(session);
		assertNotNull(session.getVisualStyles());
	}

	@Test
	public void testSetNullVisualStyles() {
		session = new CySession.Builder().visualStyles(null).build();
		assertNotNull(session);
		assertNotNull(session.getVisualStyles());
	}

	@Test
	public void testSetVisualStyles() {
		VisualStyle v1 = mock(VisualStyle.class);
		Set<VisualStyle> set = new HashSet<VisualStyle>();
		set.add(v1);
		session = new CySession.Builder().visualStyles(set).build();
		assertNotNull(session);
		assertNotNull(session.getVisualStyles());
		assertEquals(set, session.getVisualStyles());
	}

	@Test
	public void testDefaultGetAppFileListMap() {
		session = new CySession.Builder().build();
		assertNotNull(session);
		assertNotNull(session.getAppFileListMap());
	}

	@Test
	public void testSetNullAppFileListMap() {
		session = new CySession.Builder().appFileListMap(null).build();
		assertNotNull(session);
		assertNotNull(session.getAppFileListMap());
		assertEquals(0,session.getAppFileListMap().size());
	}

	@Test
	public void testSetAppFileListMap() {
		File f1 = new File("f1");
		File f2 = new File("f2");

		List<File> l1 = new ArrayList<File>();
		l1.add(f1);
		l1.add(f2);

		File f3 = new File("f3");
		File f4 = new File("f4");

		List<File> l2 = new ArrayList<File>();
		l1.add(f3);
		l1.add(f4);

		Map<String,List<File>> pflm = new HashMap<String,List<File>>();
		pflm.put("app1",l1);
		pflm.put("app2",l2);

		session = new CySession.Builder().appFileListMap(pflm).build();
		assertNotNull(session);
		assertNotNull(session.getAppFileListMap());
		assertEquals(2,session.getAppFileListMap().size());
		assertEquals(l1,session.getAppFileListMap().get("app1"));
		assertEquals(l2,session.getAppFileListMap().get("app2"));
	}

	@Test
	public void testSetNullObjectMap() {
		session = new CySession.Builder().objectMap(null).build();
		assertNotNull(session);
		assertNull(session.getObject(1, CyNode.class));
		assertNull(session.getObject(1, CyEdge.class));
		assertNull(session.getObject(1, CyNetwork.class));
		assertNull(session.getObject(1, CyNetworkView.class));
		assertNull(session.getObject(1, View.class));
	}
	
	@Test
	public void testSetObjectMap() {
		Map<Object, CyNetwork> netMap = new HashMap<Object, CyNetwork>();
		CyNetwork net1 = mock(CyNetwork.class);
		netMap.put("A", net1);
		CyNetwork net2 = mock(CyNetwork.class);
		netMap.put("B", net2);
		
		Map<Object, CyNetworkView> viewMap = new HashMap<Object, CyNetworkView>();
		CyNetworkView view1 = mock(CyNetworkView.class);
		viewMap.put("A", view1);
		
		Map<Object, CyNode> nodeMap = new HashMap<Object, CyNode>();
		CyNode n1 = mock(CyNode.class);
		nodeMap.put(new Long(1), n1);
		CyNode n2 = mock(CyNode.class);
		nodeMap.put(new Long(2), n2);
		
		Map<Object, CyEdge> edgeMap = new HashMap<Object, CyEdge>();
		CyEdge e1 = mock(CyEdge.class);
		edgeMap.put(new Long(3), e1);
				
		Map<Class<? extends CyTableEntry>, Map<Object, ? extends CyTableEntry>> objMap = new HashMap<Class<? extends CyTableEntry>, Map<Object, ? extends CyTableEntry>>();
		objMap.put(CyNetwork.class, netMap);
		objMap.put(CyNetworkView.class, viewMap);
		objMap.put(CyNode.class, nodeMap);
		objMap.put(CyEdge.class, edgeMap);
		
		session = new CySession.Builder().objectMap(objMap).build();
		assertNotNull(session);
		assertSame(net1, session.getObject("A", CyNetwork.class));
		assertSame(net2, session.getObject("B", CyNetwork.class));
		assertSame(view1, session.getObject("A", CyNetworkView.class));
		assertSame(n1, session.getObject(new Long(1), CyNode.class));
		assertSame(n2, session.getObject(new Long(2), CyNode.class));
		assertSame(e1, session.getObject(new Long(3), CyEdge.class));
		assertNull(session.getObject("A", View.class));
		assertNull(session.getObject("B", CyNetworkView.class));
	}

	private void checkProps(Set<CyProperty<?>> set) {
		assertNotNull(set);
		assertEquals(1,set.size());
		
		CyProperty<?> cyProps = set.toArray(new CyProperty[set.size()])[0];
		assertNotNull(cyProps);
		assertEquals("test",cyProps.getName());
		assertEquals(CyProperty.SavePolicy.SESSION_FILE,cyProps.getSavePolicy());
		assertTrue(Properties.class.isAssignableFrom(cyProps.getPropertyType()));
		
		Properties p = (Properties) cyProps.getProperties();
		assertEquals(2,p.size());
		assertEquals("value1",p.getProperty("key1"));
		assertEquals("value2",p.getProperty("key2"));
	}

	private Set<CyProperty<?>> getFakeProps() {
		Set<CyProperty<?>> set = new HashSet<CyProperty<?>>();
		
		Properties p = new Properties();
		p.setProperty("key1","value1");
		p.setProperty("key2","value2");
		
		CyProperty<Properties> cyProps = new SimpleCyProperty<Properties>("test", p, Properties.class,
				CyProperty.SavePolicy.SESSION_FILE);
		set.add(cyProps);
		
		return set;
	}
}
