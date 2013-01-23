package org.cytoscape.event;

/*
 * #%L
 * Cytoscape Event API (event-api)
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

/**
 * DOCUMENT ME!
 */
public class StubCyPayloadListenerImpl implements StubCyPayloadListener {
	int called = 0;
	CyEventHelper eh = null;

	/**
	 *  DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void handleEvent(StubCyPayloadEvent e) {
		called++;
		if ( eh != null ) {
			for ( int i = 0; i < 5; i++ ) {
				try { Thread.sleep(100); } catch (Exception ex) {}
				eh.addEventPayload("listener","payload"+i,StubCyPayloadEvent.class);
			}
			eh = null; // so that we only call spew extra events once!
		}
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public int getNumCalls() {
		return called;
	}

	public String toString() {
		return "StubCyPayloadListenerImpl: " + Integer.toString(called);
	}
	
	public void setEventHelper(CyEventHelper eh) {
		this.eh = eh;
	}
}
