package org.cytoscape.application;

/*
 * #%L
 * Cytoscape Application API (application-api)
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

/**
 * An interface providing access to version information about this
 * version of the Cytoscape application. Cytoscape strives to follow
 * the <a href="http://semver.org">Semantic Versioning</a> standard
 * to ensure comprehensible backwards compatibility versioning. 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule application-api
 */
public interface CyVersion {

	/**
	 * The string used to identify the Property containing the version number.
	 */
	@Deprecated
	public static final String VERSION_PROPERTY_NAME = "cytoscape.version.number";

	/**
	 * The regular expression used to test the property version string.
	 */
	public static final String VERSION_REGEX = "^(\\d+)\\.(\\d+)\\.(\\d+)([\\-\\.\\w]*)$";
	
	/**
	 * The full version as a string, for example "3.2.5-alpha".  
	 * @return the full version as a string.
	 */
	public String getVersion();

	/**
	 * The first of the three numbers defining the version of the 
	 * software, meaning "3" if the version is "3.2.5". This number
	 * changes only for comprehensive software changes and while APIs
	 * may be similar between versions, they are not guaranteed to be
	 * compatible.
	 * @return The integer describing the major version of this application.
	 */
	public int getMajorVersion(); 


	/**
	 * The second of the three numbers defining the version of the 
	 * software, meaning "2" if the version is "3.2.5". This number
	 * changes as new functionality or a new API is added to the software.  
	 * Minor version APIs are guaranteed to be backwards compatible for 
	 * all previous versions within the major version (i.e. version 3.2
	 * will be completely compatible with all 3.1.x, and 3.0.x code).
	 * @return The integer describing the minor version of this application.
	 */
	public int getMinorVersion();


	/**
	 * The third of the three numbers defining the version of the 
	 * software, meaning "5" if the version is "3.2.5". This number
	 * changes as bug fixes are made but no new features or APIs are 
	 * added. Bug fix versions contain NO NEW FEATRURES!
	 * Bug fix version APIs are guaranteed to be backwards 
	 * compatible for all previous versions within the major version 
	 * (i.e. version 3.2.5 will be completely compatible with all 3.x 
	 * versions of the code).
	 * @return The integer describing the bug fix version of this application.
	 */
	public int getBugFixVersion();

	/**
	 * Any text following the last digit of the bug fix version.  If
	 * the version is 3.0.2-beta1 then the qualifier will be "-beta1".
	 * Final (stable) versions of the application should not include any
	 * qualifying information. All qualifying text indicates a pre-release,
	 * an unstable release, or unfinished release.
	 * @return Any text following the last digit of the bug fix version.
	 */
	public String getQualifier();
}
