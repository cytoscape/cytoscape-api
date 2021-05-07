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

import java.awt.Window;

/**
 * TODO: Missing documentation
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-swing-api
 */
public interface DirectlyPresentableTunableHandler {

	/**
	 * This method allows us to bypass the normal tunable support when there is
	 * only one tunable in a Task.
	 */
	boolean setTunableDirectly(Window possibleParent);
	
	/**
	 * If this method returns true, the boolean will be presented
	 * to set directly from GUI without being a part of a parent panel.
	 * This method can be controlled by the param field available in
	 * tunables. For instance, in Boolean tunable if parameter
	 * "ForceSetDirectly"="true" is defined, the tunable will be
	 * set directly.  
	 * @return
	 */
	boolean isForcedToSetDirectly();
	
}
