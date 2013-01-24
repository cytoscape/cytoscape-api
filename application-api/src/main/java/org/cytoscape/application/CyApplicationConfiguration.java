package org.cytoscape.application;

/*
 * #%L
 * Cytoscape Application API (application-api)
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

import java.io.File;

/**
 * Application-wide setting will be accessible through this interface.
 * @CyAPI.Api.Interface
 */
public interface CyApplicationConfiguration {
	
	/**
	 * Returns absolute path to .cytoscape location.
	 * By default, this is user's home directory.
	 * 
	 * @return location of .cytoscape setting directory.
	 * 
	 * TODO: How can we set this location?
	 */
	File getConfigurationDirectoryLocation();

	/**
	 * Returns the absolute path to the configuration directory of a
	 * particular app.  This path might not physically exist and may
	 * need to be created by the caller.
	 * @param appClass Any class defined by the app's bundle
	 * @return the absolute path to the configuration directory of a
	 *         particular app.
	 */
	File getAppConfigurationDirectoryLocation(Class<?> appClass);
}
