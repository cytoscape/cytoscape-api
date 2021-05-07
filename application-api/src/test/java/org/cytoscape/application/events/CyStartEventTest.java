package org.cytoscape.application.events;

/*
 * #%L
 * Cytoscape Application API (application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CyStartEventTest {

	@Test
	public void testGetSource() {
		Integer i = new Integer(1);
		CyStartEvent e = new CyStartEvent(i);
		assertEquals( i, e.getSource() ); 
	}

	@Test
	public void testGetListenerClass() {
		Object i = new Object(); 
		CyStartEvent e = new CyStartEvent(i);
		assertEquals( CyStartListener.class, e.getListenerClass() ); 
	}

	@Test(expected=NullPointerException.class)
	public void testNullSource() {
		new CyStartEvent(null);
	}

}
