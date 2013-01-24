package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cytoscape.event.DummyCyEventHelper;
import org.cytoscape.model.CyTable.Mutability;
import org.cytoscape.model.events.ColumnCreatedEvent;
import org.cytoscape.model.events.RowSetRecord;
import org.junit.Test;


public abstract class AbstractCyColumnTest {
	protected CyTable table;
	
	@Test (expected=NullPointerException.class)
	public void testSetNameNull(){
		table.createColumn("test", String.class, false);
		table.getColumn("test").setName(null);
		
	}
	

	@Test (expected=IllegalArgumentException.class)
	public void testSetNameCaseMatchAll(){
		
		table.createColumn("test", String.class, false);
		table.getColumn("test").setName("TEST1");
		
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetNameCaseMatchAll2(){
		
		table.createColumn("TEST", String.class, false);
		table.getColumn("TEST").setName("test1");
		
		
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetNameCaseMatchFirst(){
		
		table.createColumn("test", String.class, false);
		table.getColumn("test").setName("Test1");
		
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetNameCaseMatchLast(){
		
		table.createColumn("test", String.class, false);
		table.getColumn("test").setName("tesT1");
		
		
	}

	
	@Test (expected=IllegalArgumentException.class)
	public void testSetNameCaseMatchMiddle(){
		
		table.createColumn("test", String.class, false);
		table.getColumn("test").setName("tEst1");
		
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetNameCaseMatchMultiple(){
		
		table.createColumn("test", String.class, false);
		table.getColumn("test").setName("tESt1");
		
	}
	
}