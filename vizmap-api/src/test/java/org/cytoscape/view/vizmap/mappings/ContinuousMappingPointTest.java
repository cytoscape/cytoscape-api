package org.cytoscape.view.vizmap.mappings;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Paint;

import org.cytoscape.event.CyEventHelper;
import org.cytoscape.view.vizmap.events.VisualMappingFunctionChangeRecord;
import org.cytoscape.view.vizmap.events.VisualMappingFunctionChangedEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContinuousMappingPointTest {

	private ContinuousMappingPoint<Double, Paint> point;
	private BoundaryRangeValues<Paint> brv1;
	private BoundaryRangeValues<Paint> brv2;

	private Double val1 = 100d;
	private Double val2 = 0d;

	@Mock
	CyEventHelper eventHelper;

	@Mock
	ContinuousMapping<Double, Paint> continuousMapping;

	@Mock
	ContinuousMapping<String, Paint> continuousMapping2;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.brv1 = new BoundaryRangeValues<>(Color.RED, Color.WHITE, Color.GREEN);
		this.brv2 = new BoundaryRangeValues<>(Color.BLACK, Color.YELLOW, Color.PINK);
		point = new ContinuousMappingPoint<>(val1, brv1, continuousMapping, eventHelper);
		reset(eventHelper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContinuousMappingPoint() {
		assertNotNull(point);
		// This throws exception
		new ContinuousMappingPoint<>("Test", brv1, continuousMapping2, eventHelper);
	}

	@Test
	public void testGetValue() {
		final Double value = point.getValue();
		assertEquals(val1, value);
	}

	@Test
	public void testSetValue() {
		point.setValue(val2);
		assertEquals(val2, point.getValue());
		verify(eventHelper, times(1))
			.addEventPayload(
				eq(continuousMapping),
				any(VisualMappingFunctionChangeRecord.class),
				eq(VisualMappingFunctionChangedEvent.class));
	}
	
	@Test
	public void testSetSameValueDoesNotFireEvent() { // When equal value set again...
		point.setValue(new Double(val1));
		assertEquals(val1, point.getValue());
		verify(eventHelper, times(0)) // No event payload is added!
			.addEventPayload(
				eq(continuousMapping),
				any(VisualMappingFunctionChangeRecord.class),
				eq(VisualMappingFunctionChangedEvent.class));
	}

	@Test
	public void testGetRange() {
		assertEquals(brv1, point.getRange());
	}

	@Test
	public void testSetRange() {
		point.setRange(brv2);
		assertEquals(brv2, point.getRange());
		verify(eventHelper, times(1))
			.addEventPayload(
				eq(continuousMapping),
				any(VisualMappingFunctionChangeRecord.class),
				eq(VisualMappingFunctionChangedEvent.class));
	}
	
	@Test
	public void testSetSameRangeDoesNotFireEvent() { // When equal range set again...
		point.setRange(new BoundaryRangeValues<>(Color.RED, Color.WHITE, Color.GREEN));
		assertEquals(brv1, point.getRange());
		verify(eventHelper, times(0)) // No event payload is added!
			.addEventPayload(
				eq(continuousMapping),
				any(VisualMappingFunctionChangeRecord.class),
				eq(VisualMappingFunctionChangedEvent.class));
	}
}
