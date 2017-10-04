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
 * The basic event handling interface for Cytoscape.  All Cytoscape events
 * should be fired using these methods.  All listeners should be registered
 * as CyListener services.
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule event-api
 */
public interface CyEventHelper {
	/**
	 * The default number of milliseconds to wait before the next
	 * time that we will check for payload events to fire. 
	 */
	int DEFAULT_PAYLOAD_INTERVAL_MILLIS = 100;
	
	/**
	 * Calls each listener found in the Service Registry identified by the listenerClass
	 * interface by the supplied CyEvent.
	 *
	 * @param <E> The type of event fired. 
	 * @param event The event to be fired. 
	 */
	<E extends CyEvent<?>> void fireEvent(final E event);

	/**
	 * Adds a payload object to be accumulated in a CyPayloadEvent. The event is guaranteed
	 * to be fired after a certain duration where the single event contains all payload
	 * objects added to the event within that window of time.  Payload objects added
	 * after an event has fired simply trigger a new event to fire at the next time
	 * point. All accumulated CyPayloadEvents are guaranteed to be fired before any
	 * normal CyEvents are fired.
	 * @param <S> the generic type of the object firing the event.
	 * @param <P> the generic type of the data payload to be added to the event.
	 * @param <E> the generic type of the event that the payload will be added to.
	 * @param source The object firing the event.
	 * @param payload The data payload to be added to the event that will
	 * eventually be fired.
	 * @param eventType The type of the event that the payload will be added to. 
	 */
	<S,P,E extends CyPayloadEvent<S,P>> void addEventPayload(S source, P payload, Class<E> eventType);

	/**
	 * Forces accumulated payload events to be fired.  This is a useful method
	 * that flushes all accumulated payload events, which are normally fired 
	 * asynchronously to be fired synchronously at a precise time. While this method
	 * can be safely called at any time, it probably shouldn't be called as a force 
	 * of habit.
	 */
	 void flushPayloadEvents();
	 
	 /**
	 * Forces accumulated payload events to be fired.  This is a useful method
	 * that flushes all accumulated payload events for the given envent source, 
	 * which are normally fired 
	 * asynchronously to be fired synchronously at a precise time. While this method
	 * can be safely called at any time, it probably shouldn't be called as a force 
	 * of habit.
	 */
	 void flushPayloadEvents(Object eventSource);

	/**
	 * This method will prevent any events fired from the specified source 
	 * object from being propagated to listeners.  This applies to both
	 * normal events and accumulating event payloads.
	 * @param eventSource The object that should have its events blocked 
	 * from being sent.
	 */
	void silenceEventSource(Object eventSource);

	/**
	 * This method will allow events fired from the specified source 
	 * object to be propagated to listeners.  This applies to both
	 * normal events and accumulating event payloads.  This method only needs
	 * to be called if the silenceEventSource(eventSource) method has
	 * been called.  Otherwise, all events are by default propagated
	 * normally.
	 * @param eventSource The object that should have its events sent
	 * to listeners.
	 */
	void unsilenceEventSource(Object eventSource);
}
