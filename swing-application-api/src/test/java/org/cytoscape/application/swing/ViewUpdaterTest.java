package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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
