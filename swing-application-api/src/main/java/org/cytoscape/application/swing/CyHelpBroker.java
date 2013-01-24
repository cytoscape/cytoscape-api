package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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

import javax.help.HelpBroker;
import javax.help.HelpSet;


/**
 * This interface provides access to the Cytoscape Help Broker and 
 * Help Set for managing the JavaHelp system. 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CyHelpBroker {

	/**
	 * Returns the HelpBroker. 
	 * @return the HelpBroker. 
	 */
	public HelpBroker getHelpBroker();

	/**
	 * Returns the HelpSet. 
	 * @return the HelpSet. 
	 */
	public HelpSet getHelpSet();
}
