package org.cytoscape.application.swing;

import static org.junit.Assert.*;

import java.awt.Paint;
import java.util.HashMap;
import java.util.Map;

import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class ViewUpdaterTest extends AbstractViewUpdaterTest {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		String columnName ="test";
		Map<CyRow, View<?>> rowViewMap = new HashMap<CyRow, View<?>>();
		updater = new DummyViewUpdater<Paint>(BasicVisualLexicon.NODE_FILL_COLOR, columnName, rowViewMap);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Override
	public void testAbstractViewUpdater() {
		assertNotNull(updater);
	}
	
	private static final class DummyViewUpdater<T> extends AbstractViewUpdater<T> {

		public DummyViewUpdater(VisualProperty<T> vp, String columnName, Map<CyRow, View<?>> rowViewMap) {
			super(vp, columnName, rowViewMap);
		}
	}
}
