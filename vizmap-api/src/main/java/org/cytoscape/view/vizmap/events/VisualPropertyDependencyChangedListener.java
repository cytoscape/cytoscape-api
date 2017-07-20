package org.cytoscape.view.vizmap.events;

import org.cytoscape.event.CyListener;
import org.cytoscape.view.vizmap.VisualPropertyDependency;
import org.cytoscape.view.vizmap.VisualStyle;

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
 * This listener is used by the {@link VisualStyle} implementation, so a style can be
 * notified when {@link VisualPropertyDependency#setDependency(boolean)} is called on any of its
 * visual property dependencies.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface VisualPropertyDependencyChangedListener extends CyListener {
	
	void handleEvent(VisualPropertyDependencyChangedEvent e);
	
}
