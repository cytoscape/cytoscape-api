package org.cytoscape.application;

/*
 * #%L
 * Cytoscape Application API (application-api)
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

/**
 * A service that will shutdown Cytoscape cleanly. Will fire a 
 * CytoscapeShutdownEvent such that all CytoscapeShutdownListener will be
 * notified before actually shutting down.
 * @CyAPI.Api.Interface
 */
public interface CyShutdown {
	/**
	 * Calling this method indicates that the application should shutdown.
	 * @param retVal The return value with which to (eventually)
	 * call {@link System#exit} with.
	 */
	void exit(int retVal);
}
