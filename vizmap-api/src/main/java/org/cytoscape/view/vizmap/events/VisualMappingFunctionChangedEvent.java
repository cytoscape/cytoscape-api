package org.cytoscape.view.vizmap.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.vizmap.VisualMappingFunction;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2017 The Cytoscape Consortium
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
 * VisualMappingFunctions should fire this event when the contents of the mapping are modified.
 * @CyAPI.InModule vizmap-api
 */
@SuppressWarnings("rawtypes")
// TODO: This really should be a final class but changing it now would break API
public class VisualMappingFunctionChangedEvent extends
		AbstractCyPayloadEvent<VisualMappingFunction, VisualMappingFunctionChangeRecord> {

	public VisualMappingFunctionChangedEvent(VisualMappingFunction source,
			Collection<VisualMappingFunctionChangeRecord> payload) {
		super(source, VisualMappingFunctionChangedListener.class, payload);
	}
}
