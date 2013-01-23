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

import org.cytoscape.work.swing.DynamicSubmenuListener;
import javax.swing.Action;
import javax.swing.JMenuItem;

/**
 * A class that allows the enabled state of an Action to be always enabled. 
 * 
 * @CyAPI.Final.Class
 */
final class AlwaysEnabledEnableSupport extends AbstractEnableSupport {


	/**
	 * Constructor.
	 * @param submenuListener The submenu listener whose enabled state will be updated.
	 */
	public AlwaysEnabledEnableSupport(DynamicSubmenuListener submenuListener) {
		super(submenuListener);
	}

	/**
	 * Constructor.
	 * @param action The action whose enabled state will be updated.
	 */
	public AlwaysEnabledEnableSupport(Action action) {
		super(action);
	}

	/**
	 * Constructor.
	 * @param menuItem The menuItem whose enabled state will be updated.
	 */
	public AlwaysEnabledEnableSupport(JMenuItem menuItem) {
		super(menuItem);
	}

	/**
	 * Always sets the enable state to true.
	 */
	public void updateEnableState() {
		setEnabled( true );
	}
}
