package org.cytoscape.property;

/*
 * #%L
 * Cytoscape Property API (property-api)
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
 * A general property service interface for providing access to different types
 * of property objects as OSGi services. The type P is generally one of two types: 
 * {@link java.util.Properties} or 
 * {@link org.cytoscape.property.bookmark.Bookmarks}, although it is possible for 
 * property objects of other types to be registered in this way as well.
 * @param <P> The generic type for the CyProperty. Generally one of two types: 
 * {@link java.util.Properties} or 
 * {@link org.cytoscape.property.bookmark.Bookmarks}, although it is possible for 
 * property objects of other types to be registered in this way as well.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule property-api
 */
public interface CyProperty<P> {

	/** 
	 * SavePolicy specifies how the CyProperty will be saved, or if it will not be saved. 
	 * @CyAPI.Enum.Class
	 */
	enum SavePolicy {
		/** 
		 * These properties are preset by Cytoscape to some default value and
		 * are never written out. 
		 */
		DO_NOT_SAVE,

		/** 
		 * These properties are loaded from and written to the default config
		 * directory and are user defaults. 
		 */
		CONFIG_DIR,

		/** 
		 * These properties will be loaded from and saved to session files. 
		 */
		SESSION_FILE,

		/** 
		 * These properties will be loaded from and saved to session files and the default
		 * config directory.  (Settings in a session file will overwrite those from the
		 * default config directory.) 
		 */
		SESSION_FILE_AND_CONFIG_DIR
	}

	/**
	 * The name of the default directory where we will look for properties files.  This
	 * will be a subdirectory of the "user.home" directory defined in the default Java
	 * system properties ({@link System#getProperties()}).
	 */
	String DEFAULT_PROPS_CONFIG_DIR = "CytoscapeConfiguration";
	
	/**
	 * Returns the name of the CyProperty.
	 * @return The name of the CyProperty.
	 */
	String getName();
	
	/**
	 * Return a property object.
	 * @return A property object of type P.
	 */
	P getProperties();

	/**
	 * Returns the {@link SavePolicy} of the CyProperty.
	 * @return the {@link SavePolicy} of the CyProperty.
	 */
	SavePolicy getSavePolicy();
	
	/**
	 * Returns the type of the property object.
	 * @return the type of the property object.
	 */
	Class<? extends P> getPropertyType();
}
