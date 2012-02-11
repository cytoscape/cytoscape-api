package org.cytoscape.model;


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