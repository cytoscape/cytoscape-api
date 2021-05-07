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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JStatusBarTest {
	
	private JStatusBar statusBar;

	@Before
	public void setUp() throws Exception {
		statusBar = new JStatusBar();
	}


	@Test
	public void testJStatusBar() {
		assertNotNull(statusBar);
	}

	@Test
	public void testSetLeftLabel() {
		String text = "left label";
		statusBar.setLeftLabel(text);
	}

	@Test
	public void testSetCenterLabel() {
		String text = "center label";
		statusBar.setLeftLabel(text);
	}

	@Test
	public void testSetRightLabel() {
		String text = "right label";
		statusBar.setLeftLabel(text);
	}

}
