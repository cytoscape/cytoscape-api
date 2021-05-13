package org.cytoscape.service.util.internal.utils;

/*
 * #%L
 * Cytoscape Service API (service-api)
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
import java.util.List;

import org.junit.Test;

public class RegisterUtilTest {

	private interface DummyInterface {
	}
	private class DummyClass implements DummyInterface{
		
	}
	
	private interface DummyInterface2 extends DummyInterface{
		
	}

	private class DummyClass2 implements DummyInterface2{
		
	}
	
	@Test
	public void TestGetAllInterfaces1 (){
		
		List<Class<?>> interfaces = RegisterUtil.getAllInterfaces(DummyClass.class);
		assertEquals(DummyInterface.class, interfaces.get(0));

	}
	
	@Test
	public void TestGetAllInterfaces2 (){
		
		List<Class<?>> interfaces = RegisterUtil.getAllInterfaces(DummyClass2.class);
		assertTrue(interfaces.contains(DummyInterface.class));
		assertTrue(interfaces.contains(DummyInterface2.class));

	}
}
