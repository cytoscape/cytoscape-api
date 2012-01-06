package org.cytoscape.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableMetadata;
import org.cytoscape.property.bookmark.Bookmarks;
import org.cytoscape.property.session.Cysession;
import org.cytoscape.view.model.CyNetworkView;
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
	public void testDefaultGetBookmarks() {
		session = new CySession.Builder().build();
		assertNotNull(session);
		assertNotNull(session.getBookmarks());
	}

	@Test
	public void testSetNullBookmarks() {
		session = new CySession.Builder().bookmarks(null).build();
		assertNotNull(session);
		assertNotNull(session.getBookmarks());
	}

	@Test
	public void testSetBookmarks() {
		Bookmarks b = mock(Bookmarks.class);
		session = new CySession.Builder().bookmarks(b).build();
		assertNotNull(session);
		assertNotNull(session.getBookmarks());
		assertEquals(b, session.getBookmarks());
	}

	@Test
	public void testDefaultGetSession() {
		session = new CySession.Builder().build();
		assertNotNull(session);
		assertNotNull(session.getCysession());
	}

	@Test
	public void testSetNullSession() {
		session = new CySession.Builder().cysession(null).build();
		assertNotNull(session);
		assertNotNull(session.getCysession());
	}

	@Test
	public void testSetSession() {
		Cysession b = mock(Cysession.class);
		session = new CySession.Builder().cysession(b).build();
		assertNotNull(session);
		assertNotNull(session.getCysession());
		assertEquals(b, session.getCysession());
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


	private void checkProps(Map<String, Properties> map) {
		assertNotNull(map);
		Properties p = map.get("Session");
		assertNotNull(p);
		assertEquals(2,p.size());
		assertEquals("value1",p.getProperty("key1"));
		assertEquals("value2",p.getProperty("key2"));
	}

	private Map<String, Properties> getFakeProps() {
		Map<String, Properties> map = new HashMap<String, Properties>();
		
		Properties p = new Properties();
		p.setProperty("key1","value1");
		p.setProperty("key2","value2");
		map.put("Session", p);
		
		return map;
	}
}
