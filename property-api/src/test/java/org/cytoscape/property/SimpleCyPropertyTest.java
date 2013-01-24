package org.cytoscape.property;

/*
 * #%L
 * Cytoscape Property API (property-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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



import static org.junit.Assert.assertTrue;
import java.util.Properties;
import org.junit.Test;


public class SimpleCyPropertyTest {

	@Test(expected=NullPointerException.class)
	public void testNullName() throws Exception {
		new SimpleCyProperty<Properties>(null, new Properties(), Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullProp() throws Exception {
		new SimpleCyProperty<Properties>("test", null, Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullType() throws Exception {
		new SimpleCyProperty<Properties>("test", new Properties(), null, CyProperty.SavePolicy.DO_NOT_SAVE);
	}

	@Test(expected=NullPointerException.class)
	public void testNullSavePol() throws Exception {
		new SimpleCyProperty<Properties>("test", new Properties(), Properties.class, null);
	}
	
	@Test
	public void testGetProp(){
		SimpleCyProperty<Properties> p = new SimpleCyProperty<Properties>("test", new Properties(), Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(p.getProperties() != null);
	}

	@Test
	public void testGetSavePol(){
		SimpleCyProperty<Properties> p = new SimpleCyProperty<Properties>("test", new Properties(), Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(p.getSavePolicy() == CyProperty.SavePolicy.DO_NOT_SAVE);
	}
	
	@Test
	public void testGetPropertyType(){
		SimpleCyProperty<Properties> p = new SimpleCyProperty<Properties>("test", new Properties(), Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(Properties.class.isAssignableFrom(p.getPropertyType()));
	}
}
