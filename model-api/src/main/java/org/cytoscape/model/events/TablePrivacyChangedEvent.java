package org.cytoscape.model.events;

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

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyTable;

/**
 * This event is fired when a table privacy is changed from public
 * to private or vice versa.  A public CyTable is a table that is accessible to the user through the user
 * interface.  Private or non-public CyTables will not be visible to the user from the
 * normal user interface, although they will be accessible to app writers through the API.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class TablePrivacyChangedEvent extends AbstractCyEvent<CyTable> {

	/**
	 * Constructor method for TablePrivacyChangedEvent.
	 * @param source the CyTable that the privacy is updated for.
	 */
	public TablePrivacyChangedEvent(CyTable source) {
		super(source, TablePrivacyChangedListener.class);
	}

}
