package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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

import java.util.ArrayList;
import java.util.Collection;

import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.events.RowSetRecord;
import org.cytoscape.model.events.RowsSetEvent;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public abstract class AbstractViewUpdaterTest {

	protected AbstractViewUpdater<?> updater;
	@Mock private CyTable source;
	
	private Collection<RowSetRecord> rows;
	
	@Mock private CyRow row;
	@Mock private Object value;
	@Mock private Object rawValue;
	
	@Test
	public abstract void testAbstractViewUpdater();

	@Test
	public void testHandleEvent() {
		rows = new ArrayList<RowSetRecord>();
		RowSetRecord rec1 = new RowSetRecord(row, "test", value, rawValue);
		rows.add(rec1);
		RowsSetEvent event = new RowsSetEvent(source, rows);
		updater.handleEvent(event);
		
		verify(row, times(0)).getAllValues();

	}

}
