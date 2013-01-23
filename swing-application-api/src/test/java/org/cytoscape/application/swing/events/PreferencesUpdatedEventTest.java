package org.cytoscape.application.swing.events;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PreferencesUpdatedEventTest {

	private PreferencesUpdatedEvent event;

	@Mock
	private Object source;
	@Mock
	private Properties oldProps;
	@Mock
	private Properties newProps;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		event = new PreferencesUpdatedEvent(source, oldProps, newProps);
	}

	@Test
	public void testPreferencesUpdatedEvent() {
		assertNotNull(event);
	}

	@Test
	public void testGetOldProperties() {
		assertEquals(oldProps, event.getOldProperties());
	}

	@Test
	public void testGetNewProperties() {
		assertEquals(newProps, event.getNewProperties());
	}
}
