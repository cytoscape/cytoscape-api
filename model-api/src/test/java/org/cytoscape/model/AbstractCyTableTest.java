package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cytoscape.event.DummyCyEventHelper;
import org.cytoscape.model.CyTable.Mutability;
import org.cytoscape.model.events.ColumnCreatedEvent;
import org.cytoscape.model.events.ColumnDeletedEvent;
import org.cytoscape.model.events.RowSetRecord;
import org.junit.Test;


public abstract class AbstractCyTableTest {
	protected CyTable table;
	protected CyTable table2;
	protected CyRow attrs;
	protected DummyCyEventHelper eventHelper; 
	protected boolean rowSetMicroListenerWasCalled;
	protected boolean rowCreatedMicroListenerWasCalled;
	protected boolean rowAboutToBeDeletedMicroListenerWasCalled;

	protected void assertListEquals(List<?> expected, List<?> actual) {
		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}
	}

	@Test
	public void testAddStringAttr() {
		table.createColumn("someString", String.class, false);
		table.createColumn("someStringElse", String.class, false);

		attrs.set("someString", "apple");
		attrs.set("someStringElse", "orange");

		assertTrue(attrs.isSet("someString"));
		assertTrue(attrs.isSet("someStringElse"));
		assertFalse(attrs.isSet("yetAnotherString"));

		assertEquals("apple", attrs.get("someString", String.class));
		assertEquals("orange", attrs.get("someStringElse", String.class));
	}

	@Test
	public void testAddIntAttr() {
		table.createColumn("someInt", Integer.class, false);
		table.createColumn("someOtherInt", Integer.class, false);

		attrs.set("someInt", 50);
		attrs.set("someOtherInt", 100);

		assertTrue(attrs.isSet("someInt"));
		assertTrue(attrs.isSet("someOtherInt"));
		assertFalse(attrs.isSet("yetAnotherInteger"));

		assertEquals(50, attrs.get("someInt", Integer.class).intValue());
		assertEquals(100, attrs.get("someOtherInt", Integer.class).intValue());
	}

	@Test
	public void testAddLongAttr() {
		table.createColumn("someLong", Long.class, false);
		table.createColumn("someOtherLong", Long.class, false);

		attrs.set("someLong", 50L);
		attrs.set("someOtherLong", 100L);

		assertTrue(attrs.isSet("someLong"));
		assertTrue(attrs.isSet("someOtherLong"));
		assertFalse(attrs.isSet("yetAnotherLong"));

		assertEquals(50, attrs.get("someLong", Long.class).intValue());
		assertEquals(100, attrs.get("someOtherLong", Long.class).intValue());
	}

	@Test
	public void testAddDoubleAttr() {
		table.createColumn("someDouble", Double.class, false);
		table.createColumn("someOtherDouble", Double.class, false);

		attrs.set("someDouble", 3.14);
		attrs.set("someOtherDouble", 2.76);

		assertTrue(attrs.isSet("someDouble"));
		assertTrue(attrs.isSet("someOtherDouble"));
		assertFalse(attrs.isSet("yetAnotherDouble"));

		assertEquals(3.14, attrs.get("someDouble", Double.class).doubleValue(), 0.000001);
		assertEquals(2.76, attrs.get("someOtherDouble", Double.class).doubleValue(), 0.000001);
	}

	@Test
	public void testAddBooleanAttr() {
		table.createColumn("someBoolean", Boolean.class, false);
		table.createColumn("someOtherBoolean", Boolean.class, false);

		attrs.set("someBoolean", true);
		attrs.set("someOtherBoolean", false);

		assertTrue(attrs.isSet("someBoolean"));
		assertTrue(attrs.isSet("someOtherBoolean"));
		assertFalse(attrs.isSet("yetAnotherBoolean"));

		assertTrue(attrs.get("someBoolean", Boolean.class));
		assertFalse(attrs.get("someOtherBoolean", Boolean.class));
	}

	@Test
	public void testAddListAttr() {
		table.createListColumn("someList", String.class, false);

		List<String> l = new LinkedList<String>();
		l.add("orange");
		l.add("banana");

		attrs.set("someList", l);

		assertTrue(attrs.isSet("someList"));

		assertEquals(2, attrs.getList("someList", String.class).size());
	}

	@Test(expected=NullPointerException.class)
	public void testCreateListColumnWithFirstArgNull() {
		table.createListColumn(null, String.class, false);
	}

	@Test(expected=NullPointerException.class)
	public void testCreateListColumnWithSecondArgNull() {
		table.createListColumn("someList", null, false);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreateListColumnWithAlreadyExistingCoulmnName() {
		table.createListColumn("someList", String.class, false);
		table.createListColumn("someList", String.class, false);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddBadAttr() {
		attrs.set("nodeColor", Color.white);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddBadList() {
		List<Color> l = new LinkedList<Color>();
		l.add(Color.white);
		l.add(Color.red);

		attrs.set("someList", l);
	}

	// You can't have an attribute with the same name, but
	// a different type.
	@Test(expected=Exception.class)
	public void testAddDuplicateNameAttr() {
		table.createColumn("something", String.class, false);
		table.createColumn("something", Integer.class, false);
	}

	@Test
	public void testRowSetMicroListener() {
		table.createColumn("someString", String.class, false);
		attrs.set("someString", "apple");

		Object last = eventHelper.getLastPayload();
		assertNotNull(last);
		assertTrue(last instanceof RowSetRecord);
	}

	@Test
	public void testColumnCreatedEvent() {
		table.createColumn("someInt", Integer.class, false);

		Object last = eventHelper.getLastEvent();
		assertNotNull( last );
		assertTrue( last instanceof ColumnCreatedEvent );
	}

	@Test
	public void testColumnDeletedEvent() {
		table.createColumn("someInt", Integer.class, false);
		table.deleteColumn("someInt");

		Object last = eventHelper.getLastEvent();
		assertNotNull( last );
		assertTrue( last instanceof ColumnDeletedEvent );
	}

	@Test
	public void testColumnCreate() {
		table.createColumn("someInt", Integer.class, false);
		assertTrue(collectionContains(table.getColumns(), "someInt") );
		assertEquals(table.getColumn("someInt").getType(), Integer.class );
	}

	public void testColumnDelete() {
		table.createColumn("someInt", Integer.class, false);
		assertTrue(collectionContains(table.getColumns(), "someInt"));
		
		table.deleteColumn("someInt");
		assertFalse(collectionContains(table.getColumns(), "someInt"));
	}

	private static boolean collectionContains(final Collection<CyColumn> columns,
						  final String columnName)
	{
		for (final CyColumn column : columns) {
			if (column.getName().equals(columnName))
				return true;
		}

		return false;
	}

	@Test
	public void testPrimaryKey() {
		assertEquals(table.getPrimaryKey().getType(), Long.class);
	}

	@Test
	public void testUnsetRowBoolean() {
		table.createColumn("someBoolean", Boolean.class, false);
		attrs.set("someBoolean", true);
		assertTrue(attrs.isSet("someBoolean"));
		attrs.set("someBoolean", null);
		assertFalse(attrs.isSet("someBoolean"));
		attrs.set("someBoolean", false);
		assertTrue(attrs.isSet("someBoolean"));
		attrs.set("someBoolean", null);
		assertFalse(attrs.isSet("someBoolean"));
	}

	@Test
	public void testUnsetRowString() {
		table.createColumn("someString", String.class, false);
		attrs.set("someString", "homer");
		assertTrue(attrs.isSet("someString"));
		attrs.set("someString", null);
		assertFalse(attrs.isSet("someString"));
	}

	@Test
	public void testUnsetRowInt() {
		table.createColumn("someInt", Integer.class, false);
		attrs.set("someInt", 5);
		assertTrue(attrs.isSet("someInt"));
		attrs.set("someInt", null);
		assertFalse(attrs.isSet("someInt"));
	}

	@Test
	public void testUnsetRowDouble() {
		table.createColumn("someDouble", Double.class, false);
		attrs.set("someDouble", 5.0);
		assertTrue(attrs.isSet("someDouble"));
		attrs.set("someDouble", null);
		assertFalse(attrs.isSet("someDouble"));
	}

	@Test
	public void testUnsetRowList() {
		List<String> ls = new ArrayList<String>();
		ls.add("asdf");
		table.createListColumn("someList", String.class, false);
		attrs.set("someList", ls);
		assertTrue(attrs.isSet("someList"));
		attrs.set("someList", null);
		assertFalse(attrs.isSet("someList"));
	}

	@Test
	public void testGetListElementType() {
		table.createListColumn("someList2", Boolean.class, false);
		assertEquals(table.getColumn("someList2").getListElementType(), Boolean.class);
	}

	@Test
	public void testGetColumnValues() {
		table.createColumn("someLongs", Long.class, false);
		final CyRow row1 = table.getRow(1L);
		row1.set("someLongs", 15L);
		final CyRow row2 = table.getRow(2L);
		row2.set("someLongs", -27L);
		final List<Long> values = table.getColumn("someLongs").getValues(Long.class);
		assertTrue(values.size() == 2);
		assertTrue(values.contains(15L));
		assertTrue(values.contains(-27L));
	}

	@Test
	public void testGetColumnValues2() {
		table.createColumn("someLongs", Long.class, false);
		final CyRow row1 = table.getRow(1L);
		row1.set("someLongs", 15L);
		final CyRow row2 = table.getRow(2L);
		row2.set("someLongs", -27L);
		final List<Long> values = table.getPrimaryKey().getValues(Long.class);
		assertTrue(values.size() == 2);
		assertTrue(values.contains(1L));
		assertTrue(values.contains(2L));
	}

	@Test(expected=NullPointerException.class)
	public void testGetRowWithNullKey() {
		table.createColumn("someLongs", Long.class, false);
		final CyRow row1 = table.getRow(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetRowWithWrongKeyType() {
		table.createColumn("someLongs", Long.class, false);
		final CyRow row1 = table.getRow("key");
	}

	@Test(expected=NullPointerException.class)
	public void testCreateColumnWithFirstArgNull() {
		table.createColumn(null, Map.class, false);
	}

	@Test(expected=NullPointerException.class)
	public void testCreateColumnWithSecondArgNull() {
		table.createColumn("someMap", null, false);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreateColumnWithListColmnType() {
		table.createColumn("someList", List.class, false);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetForListCoulmnWithInvalidValueType() {
		table.createListColumn("someList", String.class, false);
		attrs.set("someList", 3.5);
	}

	@Test
	public void testGetMatchingColumns() {
		table.createColumn("someLongs", Long.class, false);
		final CyRow row1 = table.getRow(1L);
		row1.set("someLongs", 15L);
		final CyRow row2 = table.getRow(2L);
		row2.set("someLongs", -27L);
		Collection<CyRow> matchingRows = table.getMatchingRows("someLongs", 15L);
		assertTrue(matchingRows.size() == 1);
		matchingRows = table.getMatchingRows("someLongs", -15L);
		assertTrue(matchingRows.isEmpty());
	}

	@Test
	public void testSetAndGetTitle() {
		table.setTitle("my title");
		assertEquals(table.getTitle(), "my title");
	}

	@Test
	public void testGetListElementTypeForANonListColumn() {
		table.createColumn("someList2", Boolean.class, false);
		assertNull(table.getColumn("someList2").getListElementType());
	}

	@Test
	public void testDeleteColumnNoOpWithNonexistingColumn() {
		table.deleteColumn("x");
	}

	@Test(expected=NullPointerException.class)
	public void testGetColumnValuesWithNullSecondArgument() {
		table.createColumn("x", String.class, false);
		table.getColumn("x").getValues(null);
	}

	@Test
	public void testGetAllRows() {
		assertTrue(table.getAllRows().size() == 1);
		final CyRow row1 = table.getRow(11L);
		assertTrue(table.getAllRows().size() == 2);
		final CyRow row2 = table.getRow(22L);
		assertTrue(table.getAllRows().size() == 3);
	}

	@Test
	public void testGetListWithANonExistantColumn() {
		assertNull(attrs.getList("x", String.class));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetListWithAnInvalidListElementType() {
		table.createListColumn("x", Long.class, false);
		attrs.getList("x", String.class);
	}

	@Test
	public void testGetListWithAnMissingRowEntry() {
		table.createListColumn("x", Long.class, false);
		assertNull(attrs.getList("x", Long.class));
	}

	@Test
	public void testSetList() {
		table.createListColumn("l", String.class, false);
		final List<String> strings = new ArrayList<String>();
		strings.add("joe");
		attrs.set("l", strings);
		assertListEquals(attrs.getList("l", String.class), strings);
	}

	@Test(expected=NullPointerException.class)
	public void testSetWithNullColumnName() {
		table.createColumn("l", String.class, false);
		attrs.set(null, "xyz");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetWithInvalidValueType() {
		table.createColumn("l", Long.class, false);
		attrs.set("l", "xyz");
	}

	@Test
	public void testToStringMethodOfCyTable() {
		table.createColumn("l", Long.class, false);
		attrs.set("l", 13L);
		assertTrue(table.toString().length() > 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testUnsetWithNonExistentColumnName() {
		attrs.set("l", null);
	}

	@Test(expected=Exception.class)
	public void testGetWhereGetListShouldHaveBeenUsed() {
		table.createListColumn("l", Long.class, false);
		attrs.set("l", new ArrayList<Long>());
		attrs.get("l", Long.class);
	}

	@Test(expected=Exception.class)
	public void testGetWithAnInvalidType() {
		table.createColumn("l", Long.class, false);
		attrs.set("l", 15L);
		attrs.get("l", CyTable.class);
	}

	@Test
	public void testGetAllValues() {
		table.createColumn("x", Long.class, false);
		table.createColumn("y", Double.class, false);
		attrs.set("x", 15L);
		attrs.set("y", 3.14);
		final Map<String, Object> values = attrs.getAllValues();
		assertTrue(values.keySet().contains("x"));
		assertTrue(values.keySet().contains("y"));
		assertEquals((long)(Long)values.get("x"), 15L);
		assertEquals((double)(Double)values.get("y"), 3.14, 0.00001);
	}

	@Test
	public void testGetColumn() {
		table.createColumn("someInt", Integer.class, false);
		assertEquals(table.getColumn("someInt").getType(), Integer.class);
		assertNull(table.getColumn("nonExistentColumnName"));
	}
	
	@Test
	public void testGetColumnIsCaseInsensitive() {
		final String NAME = "Test";
		table.createColumn(NAME, String.class, false);
		final CyColumn col1 = table.getColumn(NAME);
		assertNotNull(col1);
		assertEquals(col1, table.getColumn("tEsT"));
		assertEquals(col1, table.getColumn(NAME.toLowerCase()));
		assertEquals(col1, table.getColumn(NAME.toUpperCase()));
	}
	
	@Test
	public void testDeleteColumnIsCaseInsensitive() {
		final String NAME = "Test";
		// Simple column
		table.createColumn(NAME, String.class, false);
		assertNotNull(table.getColumn(NAME));
		table.deleteColumn(NAME.toLowerCase());
		assertNull(table.getColumn(NAME));
		// List column
		table.createListColumn(NAME, String.class, false);
		assertNotNull(table.getColumn(NAME));
		table.deleteColumn(NAME.toUpperCase());
		assertNull(table.getColumn(NAME));
	}
	
	@Test
	public void testGetMatchingRowsIsCaseInsensitive() {
		final String NAME = "Test";
		table.createColumn(NAME, String.class, false);
		table.getRow(1L).set(NAME, "A");
		table.getRow(2L).set(NAME, "A");
		table.getRow(3L).set(NAME, "B");
		assertEquals(2, table.getMatchingRows(NAME.toLowerCase(), "A").size());
		assertEquals(1, table.getMatchingRows(NAME.toUpperCase(), "B").size());
	}
	
	@Test
	public void testCountMatchingRowsIsCaseInsensitive() {
		final String NAME = "Test";
		table.createColumn(NAME, String.class, false);
		table.getRow(1L).set(NAME, "A");
		table.getRow(2L).set(NAME, "A");
		table.getRow(3L).set(NAME, "B");
		assertEquals(2, table.countMatchingRows(NAME.toLowerCase(), "A"));
		assertEquals(1, table.countMatchingRows(NAME.toUpperCase(), "B"));
	}
	
	@Test
	public void testGetValueFromRowIsCaseInsensitive() {
		final String NAME = "Test";
		table.createColumn(NAME, String.class, false);
		table.getRow(1L).set(NAME, "A");
		assertEquals("A", table.getRow(1L).get(NAME.toLowerCase(), String.class));
		assertEquals("A", table.getRow(1L).get(NAME.toUpperCase(), String.class));
	}
	
	@Test
	public void testGetListFromRowIsCaseInsensitive() {
		final String NAME = "Test";
		table.createListColumn(NAME, String.class, false);
		final ArrayList<String> list = new ArrayList<String>();
		list.add("A");
		table.getRow(1L).set(NAME, list);
		assertEquals(list, table.getRow(1L).getList(NAME.toLowerCase(), String.class));
		assertEquals(list, table.getRow(1L).getList(NAME.toUpperCase(), String.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddVirtualColumnIsCaseInsensitive() {
		table.createColumn("Col_A", Long.class, false);
		table2.createColumn("Col_B", String.class, false);
		assertEquals("VirtualCol_A", table.addVirtualColumn("VirtualCol_A", "COL_B", table2, "col_a", false));
		assertEquals("VirtualCol_A", table.getColumn("VIRTUALCOL_a").getName());
		table.addVirtualColumn("virtualcol_a", "col_b", table2, "COL_A", false);
	}

	@Test
	public void testGetRowCount() {
		final CyRow row = table.getRow(2L);
		assertEquals(table.getRowCount(), table.getAllRows().size());
	}

	@Test
	public void testHandleRowCreatedMicroListener() {
		final CyRow row = table.getRow(2234L);
		Object last = eventHelper.getLastPayload();
		assertNotNull(last);
	}

	@Test
	public void testGetWithPrimaryKey() {
		final CyRow row = table.getRow(107L);
		final CyColumn primaryKey = table.getPrimaryKey();
		assertEquals(row.get(primaryKey.getName(), primaryKey.getType()), 107L);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testVirtualColumn() {
		table.createColumn("x", Long.class, false);
		table2.createColumn("s", String.class, false);
		assertEquals(table.addVirtualColumn("s1", "s", table2, "x", false), "s1");
		assertEquals("Virtual column type should have been String!",
			     String.class, table.getColumn("s1").getType());
		assertEquals(table.addVirtualColumn("s1", "s", table2, "x", false), "s1-1");
	}

	@Test
	public void testVirtualColumnIsSet() {
		CyRow row1 = table.getRow(1L);
		CyRow row2 =  table2.getRow(1L);
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		assertFalse(row1.isSet("s1"));
		row2.set("s", "abc");
		assertTrue(row1.isSet("s1"));
	}

	@Test
	public void testVirtualColumnGet() {
		CyRow row1 = table.getRow(1L);
		CyRow row2 =  table2.getRow(1L);
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), false);
		assertFalse(row1.isSet("s1"));
		row2.set("s", "abc");
		assertEquals(row1.get("s1", String.class), "abc");
	}

//	@Test  // no longer holds?  TODO
	public void testVirtualColumnGetNoCreate() {
		table2.createColumn("s", String.class, false);

		// make sure we don't start with any rows
		assertEquals(0,table2.getAllRows().size());

		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), false);
		CyRow row1 = table.getRow(1L);

		// Since there is no row (or data) in table2, this 
		// should return null.
		assertNull(row1.get("s1", String.class));

		// Getting data through a virtual column shouldn't create a row in
		// the target table when no matching row exists, so there should
		// still be 0 rows.
		assertEquals(0,table2.getAllRows().size());
	}

	@Test
	public void testVirtualColumnSetWithAReplacementValue() {
		CyRow row1 = table.getRow(1L);
		CyRow row2 =  table2.getRow(1L);
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		assertFalse(row1.isSet("s1"));
		row2.set("s", "abc");
		assertEquals(row1.get("s1", String.class), "abc");
		row1.set("s1", "xyz");
		assertEquals(row1.get("s1", String.class), "xyz");
	}

	@Test
	public void testVirtualColumnUnset() {
		CyRow row1 = table.getRow(1L);
		CyRow row2 =  table2.getRow(1L);
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), false);
		row2.set("s", "abc");
		assertTrue(row1.isSet("s1"));
		row1.set("s1", null);
		assertFalse(row1.isSet("s1"));
	}

	@Test
	public void testVirtualColumnGetMatchingRows() {
		table.createColumn("x", Integer.class, false);
		CyRow row1 = table.getRow(1L);
		row1.set("x", 33);
		CyRow row2 =  table2.getRow(1L);
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		assertFalse(row1.isSet("s1"));
		row2.set("s", "abc");
		Collection<CyRow> matchingRows = table.getMatchingRows("s1", "abc");
		assertEquals(matchingRows.size(), 1);
		CyRow matchingRow = matchingRows.iterator().next();
		assertEquals(matchingRow.get("s1", String.class), "abc");
		assertEquals(matchingRow.get("x", Integer.class), Integer.valueOf(33));
	}

	
	@Test
	public void testVirtualColumnCountMatchingRows() {
		table.createColumn("x", Integer.class, false);
		table.getRow(1L).set("x", 11);
		table.getRow(2L).set("x", 22);
		
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		
		table2.getRow(1L).set("s", "a");
		table2.getRow(2L).set("s", "a");
		table2.getRow(3L).set("s", "a");
		
		assertEquals(2, table.getRowCount()); 
		assertEquals(2, table.countMatchingRows("s1","a")); // Can't return more rows than are in the table!!!
		assertEquals(3, table2.getRowCount()); 
		assertEquals(3, table2.countMatchingRows("s","a"));
	}
	
	
	@Test
	public void testVirtualColumnGetMatchingKeys() {
		table.createColumn("x", Integer.class, false);
		table.getRow(1L).set("x", 11);
		table.getRow(2L).set("x", 22);
		
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		
		table2.getRow(1L).set("s", "a");
		table2.getRow(2L).set("s", "a");
		table2.getRow(3L).set("s", "a");
		
		assertEquals(2, table.getRowCount()); 
		Collection<Long> matchingKeys = table.getMatchingKeys("s1","a", Long.class);
		assertEquals(2, matchingKeys.size()); // Can't return more rows than are in the table!!!
		assertTrue(matchingKeys.contains(1L));
		assertTrue(matchingKeys.contains(2L));
		
		matchingKeys = table2.getMatchingKeys("s","a", Long.class);
		assertEquals(3, matchingKeys.size());
		assertTrue(matchingKeys.contains(1L));
		assertTrue(matchingKeys.contains(2L));
		assertTrue(matchingKeys.contains(3L));
	}
	
	
	@Test
	public void testVirtualColumnDelete() {
		table.createColumn("x", Long.class, false);
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, "x", false);
		assertNotNull(table.getColumn("s1"));
		table.deleteColumn("s1");
		assertNull(table.getColumn("s1"));
	}

	@Test
	public void testVirtualColumnColumnSize() {
		CyRow row1 = table.getRow(1L);
		CyRow row2 =  table2.getRow(1L);
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		assertFalse(row1.isSet("s1"));
		row2.set("s", "abc");
		List<String> columnValues = table.getColumn("s1").getValues(String.class);
		assertEquals(1, columnValues.size());
		assertEquals("abc", columnValues.get(0));
	}

	@Test
	public void testVirtualColumnGetColumnValues() {
		table.createColumn("x", Long.class, false);
		table2.createListColumn("b", Boolean.class, false);
		table.addVirtualColumn("b1", "b", table2, "x", false);
		assertEquals("Virtual column list element type should have been Boolean!",
			     Boolean.class, table.getColumn("b1").getListElementType());
	}

	@Test
	public void testIsVirtual() {
		table.createColumn("x", Long.class, false);
		table2.createListColumn("b", Boolean.class, false);
		table.addVirtualColumn("b1", "b", table2, "x", true);
		assertTrue(table.getColumn("b1").getVirtualColumnInfo().isVirtual());
		assertFalse(table.getColumn("x").getVirtualColumnInfo().isVirtual());
		assertFalse(table2.getColumn("b").getVirtualColumnInfo().isVirtual());
	}

	@Test
	public void testGetTable() {
		table.createColumn("x", Long.class, false);
		assertEquals(table, table.getColumn("x").getTable());
	}

	@Test
	public void testSetColumnName() {
		table.createColumn("x", Long.class, false);
		CyColumn column = table.getColumn("x");
		column.setName("xx");
		assertNotNull(table.getColumn("xx"));
		assertEquals(Long.class, table.getColumn("xx").getType());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetColumnNameWithImmutableColumn() {
		table.createColumn("x", Long.class, true);
		CyColumn column = table.getColumn("x");
		column.setName("xx");
	}

	@Test
	public void testCyColumnGetVirtualTable() {
		table.createColumn("x", Long.class, false);
		CyColumn column = table.getColumn("x");
		assertNull(column.getVirtualColumnInfo().getSourceTable());
		table2.createListColumn("b", Boolean.class, false);
		table.addVirtualColumn("b1", "b", table2, "x", true);
		CyColumn column2 = table.getColumn("b1");
		assertEquals(table2, column2.getVirtualColumnInfo().getSourceTable());
	}

	@Test
	public void testTableMutability() {
		table.createColumn("x", Long.class, false);
		table2.createListColumn("b", Boolean.class, false);
		assertEquals(Mutability.MUTABLE, table2.getMutability());
		table.addVirtualColumn("b1", "b", table2, "x", true);
		assertEquals(Mutability.IMMUTABLE_DUE_TO_VIRT_COLUMN_REFERENCES, table2.getMutability());
	}

    @Test(expected=IllegalArgumentException.class)
    public void testCreateInvalidColumn() {
        table.createColumn("x", Float.class, false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCreateInvalidColumn2() {
        table.createColumn("x", Object.class, false);
    }

    @Test(expected=NullPointerException.class)
    public void testCreateInvalidColumn3() {
        table.createColumn(null, Integer.class, true);
    }

    @Test(expected=NullPointerException.class)
    public void testCreateInvalidColumn4() {
        table.createColumn("homer", null, true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCreateInvalidListColumn() {
        table.createListColumn("x", Float.class, false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCreateInvalidListColumn2() {
        table.createListColumn("x", Object.class, false);
    }

    @Test(expected=NullPointerException.class)
    public void testCreateInvalidListColumn3() {
        table.createListColumn(null, Integer.class, true);
    }

    @Test(expected=NullPointerException.class)
    public void testCreateInvalidListColumn4() {
        table.createListColumn("homer", null, true);
    }

    @Test
    public void testReturnTypesCompileString() {
        List<String> ls = new ArrayList<String>();
        ls.add("homer");
        ls.add("marge");
        table.createListColumn("simpson", String.class, false);
        attrs.set("simpson",ls);
        List<String> res = attrs.getList("simpson",String.class);
        assertEquals(ls,res);
    }

    @Test
    public void testReturnTypesCompileInteger() {
        List<Integer> ls = new ArrayList<Integer>();
        ls.add(1);
        ls.add(2);
        table.createListColumn("simpson", Integer.class, false);
        attrs.set("simpson",ls);
        List<Integer> res = attrs.getList("simpson",Integer.class);
        assertEquals(ls,res);
    }

    @Test
    public void testReturnTypesCompileLong() {
        List<Long> ls = new ArrayList<Long>();
        ls.add(1L);
        ls.add(2L);
        table.createListColumn("simpson", Long.class, false);
        attrs.set("simpson",ls);
        List<Long> res = attrs.getList("simpson",Long.class);
        assertEquals(ls,res);
    }

    @Test
    public void testReturnTypesCompileDouble() {
        List<Double> ls = new ArrayList<Double>();
        ls.add(1.0);
        ls.add(2.0);
        table.createListColumn("simpson", Double.class, false);
        attrs.set("simpson",ls);
        List<Double> res = attrs.getList("simpson",Double.class);
        assertEquals(ls,res);
    }

    @Test
    public void testReturnTypesCompileBoolean() {
        List<Boolean> ls = new ArrayList<Boolean>();
        ls.add(true);
        ls.add(false);
        table.createListColumn("simpson", Boolean.class, false);
        attrs.set("simpson",ls);
        List<Boolean> res = attrs.getList("simpson",Boolean.class);
        assertEquals(ls,res);
    }

    @Test
    public void testRowExistsFalse() {
		assertFalse(table.rowExists(new Object()));
    }

    @Test
    public void testRowExistsTrue() {
		Long l = Long.valueOf(4444L);
		table.getRow(l);
		assertTrue(table.rowExists(l));
    }

    @Test
    public void testRowExistsFalseAndDoesntCreateRow() {
		Long l = Long.valueOf(54444L);
		assertFalse(table.rowExists(l));
		// Make sure it *still* doesn't exist, i.e. that calling rowExists
		// doesn't create a row like getRow does.
		assertFalse(table.rowExists(l));
    }

	// Allow the get() method to work for list types, even if not perfectly type safe.
    @Test
    public void testGetForLists() {
        List<Boolean> ls = new ArrayList<Boolean>();
        ls.add(true);
        ls.add(false);
        table.createListColumn("simpson", Boolean.class, false);
        attrs.set("simpson",ls);
        List res = attrs.get("simpson",List.class);
        assertEquals(ls,res);
	}

	@Test
	public void testSwap() {
		table.createColumn("someInt", Integer.class, false);
		table2.createColumn("someOtherInt", Integer.class, false);
		final String title = table.getTitle();
		final String title2 = table2.getTitle();
		table.swap(table2);
		assertEquals(title, table2.getTitle());
		assertEquals(title2, table.getTitle());
		assertNotNull(table.getColumn("someOtherInt"));
		assertNotNull(table2.getColumn("someInt"));
	}

	@Test
	public void testDefaultCyColumn() {
		table.createColumn("someInt", Integer.class, false, 5);
		assertEquals(Integer.valueOf(5),attrs.get("someInt",Integer.class));
	}

	@Test
	public void testNullDefaultCyColumn() {
		table.createColumn("someInt", Integer.class, false, null);
		assertNull(attrs.get("someInt",Integer.class));
	}

	@Test
	public void testDefaultListCyColumn() {
		List<Integer> l = new ArrayList<Integer>();
		l.add(5);
		table.createListColumn("someInt", Integer.class, false, l);
		assertEquals(l,attrs.getList("someInt",Integer.class));
	}

	@Test
	public void testNullDefaultListCyColumn() {
		table.createListColumn("someInt", Integer.class, false, null);
		assertNull(attrs.getList("someInt",Integer.class));
	}

	@Test
	public void testOverrideDefaultCyColumn() {
		table.createColumn("someInt", Integer.class, false, 5);
		assertEquals(Integer.valueOf(3),attrs.get("someInt",Integer.class,3));
	}

	@Test
	public void testOverrideDefaultListCyColumn() {
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(5);
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(3);
		table.createListColumn("someInt", Integer.class, false, l1);
		assertEquals(l2,attrs.getList("someInt",Integer.class,l2));
	}

	@Test
	public void testNullCantOverrideDefaultCyColumn() {
		table.createColumn("someInt", Integer.class, false, 5);
		assertEquals(Integer.valueOf(5),attrs.get("someInt",Integer.class,null));
	}

	@Test
	public void testNullCantOverrideDefaultListCyColumn() {
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(5);
		table.createListColumn("someInt", Integer.class, false, l1);
		assertEquals(l1,attrs.getList("someInt",Integer.class,null));
	}

	@Test
	public void testGetAttrBeforeColumnExists() {
		assertNull(attrs.get("nonexistentColumnX",Integer.class));
	}

	@Test
	public void testGetAttrBeforeListColumnExists() {
		assertNull(attrs.getList("nonexistentListColumnX",Integer.class));
	}

	@Test
	public void testVirtualColumns() {
		table2.createColumn("s", String.class, false);
		table2.createColumn("t", Integer.class, false);
		table2.createColumn("u", Long.class, false);
		table2.createColumn("v", Boolean.class, false);
		table2.createColumn("w", Double.class, false);
		table2.createListColumn("x", String.class, false);
		table.addVirtualColumns( table2, table.getPrimaryKey().getName(), false);

		CyColumn scol = table.getColumn("s");
		assertNotNull(scol);
		assertEquals(String.class,scol.getType());

		CyColumn tcol = table.getColumn("t");
		assertNotNull(tcol);
		assertEquals(Integer.class,tcol.getType());

		CyColumn ucol = table.getColumn("u");
		assertNotNull(ucol);
		assertEquals(Long.class,ucol.getType());

		CyColumn vcol = table.getColumn("v");
		assertNotNull(vcol);
		assertEquals(Boolean.class,vcol.getType());

		CyColumn wcol = table.getColumn("w");
		assertNotNull(wcol);
		assertEquals(Double.class,wcol.getType());

		CyColumn xcol = table.getColumn("x");
		assertNotNull(xcol);
		assertEquals(List.class,xcol.getType());
		assertEquals(String.class,xcol.getListElementType());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testVirtualColumnWithDupe() {
		table2.createColumn("s", String.class, false);
		table.createColumn("s", String.class, false);
		table.addVirtualColumns( table2, table.getPrimaryKey().getName(), false);
	}

	@Test
	public void testVirtualColumnsNoPrimaryKey() {
		table2.createColumn("s", String.class, false);
		table.addVirtualColumns( table2, table.getPrimaryKey().getName(), false);

		CyColumn scol = table.getColumn("s");
		assertNotNull(scol);

		// If the tables have the same name for the primary key column, then verify that
		// we don't get new column with the expected new column name for source table
		// primary key.  Also make sure that the existing column for the primary key
		// is not virtual.
		if ( table.getPrimaryKey().getName().equals( table2.getPrimaryKey().getName() ) ) {
			String expectedNewName = table2.getPrimaryKey().getName() + "-1";
			CyColumn npkcol = table.getColumn( expectedNewName );
			assertNull(npkcol);
			CyColumn pkcol = table.getColumn( table.getPrimaryKey().getName() );
			assertFalse( pkcol.getVirtualColumnInfo().isVirtual() );
		
		// Otherwise, just make sure that the primary key column of the source table
		// isn't added.
		} else {
			CyColumn npkcol = table.getColumn( table2.getPrimaryKey().getName() );
			assertNull(npkcol);
		}
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testColumnNameMatchFirst(){
		
		table.createColumn("test", String.class, false);
		table.createColumn("Test", Integer.class, false);
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testColumnNameMatchFirst2(){
		
		table.createColumn("Test", String.class, false);
		table.createColumn("test", Integer.class, false);
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testColumnNameMatchMiddle(){
		
		table.createColumn("test", String.class, false);
		table.createColumn("teSt", Integer.class, false);
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testColumnNameMatchEnd(){
		
		table.createColumn("test", String.class, false);
		table.createColumn("tesT", Integer.class, false);
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testColumnNameMatchAllCases(){
		
		table.createColumn("test", String.class, false);
		table.createColumn("TEST", Integer.class, false);
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testColumnNameMatchSomeCases(){
		
		table.createColumn("test", String.class, false);
		table.createColumn("tESt", Integer.class, false);
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCreateColumnDefaultCaseMatch(){
		
		table.createColumn("test", String.class, false);
		table.createColumn("Test", String.class, false, "");
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testCreateListColumnsCaseMatch(){
		
		table.createColumn("test", String.class, false);
		table.createListColumn("Test", String.class, false);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCreateListColumnsCaseMatch2(){
		
		table.createColumn("test", String.class, false);
		table.createListColumn("Test", String.class, false, null );
	}
	
	@Test
	public void testCountMatchingRows() {
		table.createColumn("x", Integer.class, false);
		CyRow row1 = table.getRow(1L);
		row1.set("x", 44);
		CyRow row2 =  table.getRow(2L);
		row2.set("x", 44);
		assertEquals(2, table.countMatchingRows("x", 44) );
		assertEquals(0, table.countMatchingRows("x", 55) );
	}

	@Test
	public void testCountMatchingRowsBooleanFalse() {
		table.createColumn("selection", Boolean.class, false);
		CyRow row1 = table.getRow(1L);
		row1.set("selection", true);
		CyRow row2 =  table.getRow(2L);
		row2.set("selection", false);
		assertEquals(1, table.countMatchingRows("selection", true) );
	}

	@Test
	public void testCountMatchingRowsBooleanTrue() {
		table.createColumn("selection", Boolean.class, false);
		CyRow row1 = table.getRow(1L);
		row1.set("selection", false);
		CyRow row2 =  table.getRow(2L);
		row2.set("selection", false);
		assertEquals(0, table.countMatchingRows("selection", true) );
	}

	@Test
	public void testVirtualColumnHasMatchingRow() {
		table.createColumn("x", Integer.class, false);
		CyRow row1 = table.getRow(1L);
		row1.set("x", 33);
		CyRow row2 =  table2.getRow(1L);
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		assertFalse(row1.isSet("s1"));
		row2.set("s", "abc");
		assertEquals(1, table.countMatchingRows("s1", "abc") );
	}
	
	@Test
	public void testVirtualColumnEvents() {
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		CyRow row1 = table.getRow(1L);
		CyRow row2 = table2.getRow(1L);
		
		List<Object> payloads = eventHelper.getAllPayloads();
		payloads.clear();
		
		// If we set a value through the virtual column, all the parent tables
		// should fire an event
		row1.set("s1", "test");
		assertEquals(2, payloads.size());
		
		payloads.clear();
		
		// If we set a value through the parent table, all the dependent tables
		// should fire an event
		row2.set("s", "test2");
		assertEquals(2, payloads.size());
	}
	
	@Test
	public void testVirtualColumnSourceRename() {
		table2.createColumn("s", String.class, false);
		table.addVirtualColumn("s1", "s", table2, table.getPrimaryKey().getName(), true);
		CyRow row1 = table.getRow(1L);
		CyRow row2 = table2.getRow(1L);
		
		String value = "hello";
		row2.set("s", value);
		assertEquals(value, row1.get("s1", String.class));

		// Rename the source column
		table2.getColumn("s").setName("t");
		
		// Ensure the link didn't break
		assertEquals(value, row1.get("s1", String.class));

		// Ensure the metadata is updated
		VirtualColumnInfo info = table.getColumn("s1").getVirtualColumnInfo();
		assertEquals("t", info.getSourceColumn());
	}
	
	@Test
	public void testVirtualColumnTargetJoinKeyRename() {
		String otherkey = "otherkey";
		
		table2.createColumn("s", String.class, false);
		table.createColumn(otherkey, Long.class, false);
		table.addVirtualColumn("s1", "s", table2, otherkey, true);
		CyRow row1 = table.getRow(1L);
		CyRow row2 = table2.getRow(2L);
		
		row1.set(otherkey, 2L);
		
		String value = "hello";
		row2.set("s", value);
		assertEquals(value, row1.get("s1", String.class));

		// Rename the source column
		table.getColumn(otherkey).setName("newname");
		
		// Ensure the link didn't break
		assertEquals(value, row1.get("s1", String.class));
		
		// Ensure the metadata is updated
		VirtualColumnInfo info = table.getColumn("s1").getVirtualColumnInfo();
		assertEquals("newname", info.getTargetJoinKey());

	}
}

