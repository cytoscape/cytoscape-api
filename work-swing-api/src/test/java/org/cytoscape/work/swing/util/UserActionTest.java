package org.cytoscape.work.swing.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionListener;

import org.junit.Test;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2017 The Cytoscape Consortium
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

public class UserActionTest {

	@Test
	public void testCreate() {
		ActionListener listener = (e -> {});
		UserAction ua = new UserAction(listener);

		assertEquals(listener, ua.getActionListener());
		assertTrue(ua.getEnabled());
	}
	
	@Test
	public void testCreateWithNullListener() {
		UserAction ua = new UserAction(null);

		assertNull(ua.getActionListener());
		assertFalse(ua.getEnabled());
	}
	
	@Test
	public void testSetActionListener() {
		UserAction ua = new UserAction(null);
		ActionListener listener = (e -> {});
		ua.setActionListener(listener);
		
		assertEquals(listener, ua.getActionListener());
		assertTrue(ua.getEnabled());
	}
}
