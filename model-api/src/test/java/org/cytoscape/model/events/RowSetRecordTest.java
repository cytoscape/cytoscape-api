package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import org.cytoscape.model.CyRow;

import static org.mockito.Mockito.*;


public class RowSetRecordTest {

	@Test
	public void testAllValid() {
		CyRow row = mock(CyRow.class);
		RowSetRecord rsr = new RowSetRecord(row, "column", "value", "rawValue");
		assertEquals(rsr.getRow(),row);
		assertEquals(rsr.getColumn(),"column");
		assertEquals(rsr.getValue(),"value");
		assertEquals(rsr.getRawValue(),"rawValue");
	}

	@Test
	public void testAllNull() {
		RowSetRecord rsr = new RowSetRecord(null,null,null,null);
		assertNull(rsr.getRow());
		assertNull(rsr.getColumn());
		assertNull(rsr.getValue());
		assertNull(rsr.getRawValue());
	}
}
