package org.cytoscape.view.model.events;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.model.events.ViewChangeRecord;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ViewChangeRecordTest {
	View<String> view;
	VisualProperty<?> vp;
	
	@Before
	public void setup() {
		view = mock(View.class);
		vp = mock(VisualProperty.class);
	}
	
	@Test
	public void testGoodRecord() {
		ViewChangeRecord<String> record = new ViewChangeRecord(view, vp, "hello" );
		assertEquals(record.getValue(), "hello");
		assertEquals(record.getView(), view);
		assertEquals(record.getVisualProperty(), vp);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullView() {
		ViewChangeRecord<String> record = new ViewChangeRecord(null, vp, "hello" );
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullVisualProperty() {
		ViewChangeRecord<String> record = new ViewChangeRecord(view, null, "hello" );
	}
	
	@Test
	public void testNullValue() {
		ViewChangeRecord<String> record = new ViewChangeRecord(view, vp, null );
		assertNull(record.getValue());
		assertEquals(record.getView(), view);
		assertEquals(record.getVisualProperty(), vp);
	}
}
