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

import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuListener;

/**
 * An extension of {@link MenuListener} that is used to describe
 * dynamic submenus. This interface allows the top name of the
 * dynamic menu to be set and allows the menu to be enabled or
 * disabled based on the state of the system.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-swing-api
 */
public interface DynamicSubmenuListener extends MenuListener, PopupMenuListener {

	/**
	 * Sets the name of the top level menu.
	 * @param name The name to use for the top level menu.
	 */
	void setMenuTitle(String name);

	/**
	 * Enables or disables the dynamically created menus.
	 * @param enableState Whether to enable or disable the menu.
	 */
	void setEnabled(boolean enableState);
}
