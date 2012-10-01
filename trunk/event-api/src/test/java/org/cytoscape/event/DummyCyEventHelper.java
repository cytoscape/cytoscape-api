/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.event;

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
}
