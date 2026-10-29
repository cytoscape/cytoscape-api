package org.cytoscape.property;

/*
 * #%L
 * Cytoscape Property API (property-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2026 The Cytoscape Consortium
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
 * Resolves the directory that holds the Cytoscape configuration files.
 * <p>
 * The directory comes from the {@value #CONFIG_DIR_PROPERTY} system property when it is set,
 * which the launcher does at start-up. Otherwise it is derived from the XDG Base Directory
 * specification on Linux, with the legacy {@code ~/CytoscapeConfiguration} directory kept
 * for compatibility.
 * </p>
 * @CyAPI.Final.Class
 * @CyAPI.InModule property-api
 */
public final class CyConfigurationDirectory {

	/** The system property that holds the resolved configuration directory. */
	public static final String CONFIG_DIR_PROPERTY = "cytoscape.config.dir";

	private CyConfigurationDirectory() {
	}

	public static File get() {
		// If the config dir is already set (manually or by the cytoscape.sh launch script),
		// just use the set value
		final String configured = System.getProperty(CONFIG_DIR_PROPERTY);
		if (configured != null && !configured.isBlank()) {
			return new File(configured);
		}

		final String userHome = System.getProperty("user.home");
		final File homeBaseDir = new File(userHome, CyProperty.DEFAULT_PROPS_CONFIG_DIR);

		if (!isLinux()) {
			return homeBaseDir;
		}

		// Follow the XDG Base Directory specification, but keep the old directory for compatibility
		if (homeBaseDir.exists()) {
			return homeBaseDir;
		}

		String xdgConfigHome = System.getenv("XDG_CONFIG_HOME");
		if (xdgConfigHome == null || xdgConfigHome.isBlank()) {
			xdgConfigHome = userHome + File.separator + ".config";
		}

		return new File(xdgConfigHome, CyProperty.DEFAULT_PROPS_CONFIG_DIR);
	}

	private static boolean isLinux() {
		return System.getProperty("os.name").startsWith("Linux");
	}
}
