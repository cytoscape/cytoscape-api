
package org.cytoscape.taglets.compatibility;

/*
 * #%L
 * Cytoscape Documentation Taglets
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

import java.util.Map;

/**
 * A taglet that describes the versioning and upgrade policy for
 * API interfaces.
 */
public class ApiInterfaceTaglet extends AbstractApiTaglet {
    
  	/**
	 * Constructor.
	 */
	public ApiInterfaceTaglet() {
		super("CyAPI.Api.Interface",
		      "API Interface",
			  "We expect that this interface will be used but not implemented by " +
			  "developers using this interface.  As such, we reserve the right to " + 
			  "add methods to the interface as part of minor version upgrades.  We " + 
			  "will not remove methods for any changes other than major version " + 
			  "upgrades."
			  );
	}

	/**
	 * The method that registers this taglet.
	 * @param tagletMap The map used to which this taglet should be added.
	 */
	@SuppressWarnings("unchecked")
	public static void register(Map tagletMap) {
		registerTaglet(tagletMap, new ApiInterfaceTaglet());
	}
	
}
