package org.cytoscape.util.swing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CheckBoxJListTest {
	private CheckBoxJList list;

	@Before
	public void setUp() throws Exception {
		list = new CheckBoxJList();
	}


	@Test
	public void testCheckBoxJList() {
		assertNotNull(list);
	}

	@Test
	public void testSetSelectedItems() {
		
		final List<String> selected = new ArrayList<String>();
		DefaultListModel model = new DefaultListModel();
		final String[] listItemStrings = new String[] {"test1", "test2", "test3"};
		for(String item: listItemStrings)
			model.addElement(item);
		list.setModel(model);
		
		selected.add("test2");
		list.setSelectedItems(selected);
		Object[] selectedVals = list.getSelectedValues();
		assertEquals(1, selectedVals.length);
		
		selected.clear();
		for(String item: listItemStrings)
			selected.add(item);
		
		list.setSelectedItems(selected);
		selectedVals = list.getSelectedValues();
		assertEquals(3, selectedVals.length);
		
		selected.clear();
		selected.add("invalid");
		
		list.setSelectedItems(selected);
		selectedVals = list.getSelectedValues();
		assertEquals(0, selectedVals.length);
	}

	@Test
	public void testValueChanged() {
		
		final List<String> selected = new ArrayList<String>();
		DefaultListModel model = new DefaultListModel();
		final String[] listItemStrings = new String[] {"test1", "test2", "test3"};
		for(String item: listItemStrings)
			model.addElement(item);
		list.setModel(model);
		for(String item: listItemStrings)
			selected.add(item);
		
		list.setSelectedItems(selected);
		
		ListSelectionEvent lse = mock(ListSelectionEvent.class);
		when(lse.getValueIsAdjusting()).thenReturn(false);
		list.valueChanged(lse);
		
		verify(lse, times(1)).getValueIsAdjusting();
	}

}
