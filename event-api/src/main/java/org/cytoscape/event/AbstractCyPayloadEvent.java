/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

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

import java.util.Collection;
import java.util.Collections;

/**
 * A base implementation of {@link CyPayloadEvent} that can be used by events.
 * @param <T> the generic type of the source object.
 * @param <P> the generic type of the payloads.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule event-api
 */
public abstract class AbstractCyPayloadEvent<T,P> extends AbstractCyEvent<T> implements CyPayloadEvent<T,P> {

	private final Collection<P> payload;

	/**
	 * Constructor.
	 * @param source The event source object.
	 * @param listenerClass The listener class for this event.
	 * @param payload A collection of payload objects. May be empty, but not null!
	 */
	public AbstractCyPayloadEvent(final T source, Class<?> listenerClass, Collection<P> payload) {
		super(source, listenerClass);

		if ( payload == null )
			throw new NullPointerException("Payload is null");

		this.payload = Collections.unmodifiableCollection(payload);
	}

	/**
	 * Returns an unmodifiable collection of payload objects.
	 * @return an unmodifiable collection of payload objects.
	 */
	@Override
	public Collection<P> getPayloadCollection() {
		return payload;
	}
}
