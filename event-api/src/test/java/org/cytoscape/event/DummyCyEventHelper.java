package org.cytoscape.event;

/*
 * #%L
 * Cytoscape Event API (event-api)
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DummyCyEventHelper implements CyEventHelper {
	private List<Object> lastEvents; 
	private LinkedList<Object> payloads;
	private final boolean keepAllEvents;

	public DummyCyEventHelper(boolean keepAllEvents) {
		this.keepAllEvents = keepAllEvents;
		lastEvents = new ArrayList<Object>();
		payloads = new LinkedList<Object>();
	}

	public DummyCyEventHelper() {
		this(false);
	}
	
	public synchronized <E extends CyEvent<?>> void fireEvent(final E event) {
		if ( keepAllEvents || lastEvents.size() == 0 )
			lastEvents.add(event);
		else 
			lastEvents.set(0,event);
	}

	public <S,P,E extends CyPayloadEvent<S,P>> void addEventPayload(S source, P p, Class<E> e) {
		payloads.addLast(p);
	}

	public Object getLastEvent() {
		return lastEvents.get(lastEvents.size()-1);
	}

	public List<Object> getAllLastEvents() {
		return lastEvents;
	}
	
	public Object getLastPayload() {
		if (payloads.size() == 0)
			return null;
		return payloads.getLast();
	}
	
	public List<Object> getAllPayloads() {
		return payloads;
	}

	public void silenceEventSource(Object o) {
	}

	public void unsilenceEventSource(Object o) {
	}

	public void flushPayloadEvents() {
	}

	public void flushPayloadEvents(Object eventSource) {
	}
}
