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

import org.cytoscape.event.CyListener;

/**
 * Listener interface for handling {@link TablePrivacyChangedEvent}.
 * This interface can be implemented by the classes effected by setting
 * a table public or private.
 * @author rozagh
 *
 */
public interface TablePrivacyChangedListener extends CyListener{
	
	/**
	 * Handler method for {@link TablePrivacyChangedListener}.
	 * @param e the fired event of type {@link TablePrivacyChangedEvent}. 
	 */
	void handleEvent(TablePrivacyChangedEvent e);
}
