package org.cytoscape.property;

/*
 * #%L
 * Cytoscape Property API (property-api)
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

import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;


public class PropertyUpdateEventTest {
	
	CyProperty prop;
	PropertyUpdatedEvent event;
	@Before
	public void init(){
		prop = mock(CyProperty.class);
		event = new PropertyUpdatedEvent(prop);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullConstructor(){
		PropertyUpdatedEvent e = new PropertyUpdatedEvent(null);
	}
	
	@Test
	public void testGetSource(){
		assertEquals(prop, event.getSource());
	}
}
