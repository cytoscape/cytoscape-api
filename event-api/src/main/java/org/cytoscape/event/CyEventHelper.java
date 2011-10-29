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


/**
 * The basic event handling interface for Cytoscape.  All Cytoscape events
 * should be fired using these methods.  All listeners should be registered
 * as CyListener services.
 * @CyAPI.Api.Interface   #ASKMIKE
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
