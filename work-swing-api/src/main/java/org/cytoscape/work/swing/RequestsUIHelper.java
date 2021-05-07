package org.cytoscape.work.swing;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
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
 * Interface to indicate that an app wants to have a UI helper
 * provided.  
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-swing-api
 */
public interface RequestsUIHelper {
	/**
 	 * This method will be called by the Tunable UI handler to
 	 * provide a helper object that might be used for some
 	 * interaction with the Tunable UI.  Note that there is no
 	 * guarantee that this will ever get called.  Non-swing
 	 * user interfaces, in particular (e.g. command line) will not
 	 * provide a TunableUIHelper.
 	 *
 	 * @param helper the helper object
 	 */ 
	public void setUIHelper(TunableUIHelper helper);
}
