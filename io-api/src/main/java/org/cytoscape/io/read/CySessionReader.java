package org.cytoscape.io.read;

/*
 * #%L
 * Cytoscape IO API (io-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import org.cytoscape.session.CySession;
import org.cytoscape.work.Task;

/**
 * An extension of the Task interface that returns a
 * {@link org.cytoscape.property.session.Cysession} object. The reader does nothing
 * beyond create the {@link org.cytoscape.property.session.Cysession} object and does NOT 
 * use the {@link org.cytoscape.property.session.Cysession} object to define the state of 
 * Cytoscape - that is managed by the CySessionManager.
 * Instances of this interface are created by {@link org.cytoscape.io.read.InputStreamTaskFactory}
 * objects registered as OSGi services, which are in turn processed
 * by associated reader manager objects that distinguish 
 * InputStreamTaskFactories based on the {@link org.cytoscape.io.DataCategory} associated with
 * the {@link org.cytoscape.io.CyFileFilter}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface CySessionReader extends Task {

	/**
	 * Returns a {@link org.cytoscape.session.CySession} object
	 * @return A {@link org.cytoscape.session.CySession} object. 
	 */
    CySession getSession();
}

