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

import javax.swing.Action;
import javax.swing.JMenuItem;

import org.cytoscape.work.swing.DynamicSubmenuListener;

abstract class AbstractEnableSupport {

	private final Action action;
	private final JMenuItem menuItem;
	private final DynamicSubmenuListener submenuListener; 
	private boolean enableState;

	/**
	 * Constructor.
	 * @param submenuListener The submenu listener whose enabled state will be updated.
	 */
	public AbstractEnableSupport(DynamicSubmenuListener submenuListener) {
		this.submenuListener = submenuListener;
		this.action = null;
		this.menuItem = null;
		this.enableState = true;
	}

	/**
	 * Constructor.
	 * @param action The action whose enabled state will be updated.
	 */
	public AbstractEnableSupport(Action action) {
		this.submenuListener = null;
		this.action = action;
		this.menuItem = null;
		this.enableState = true;
	}

	/**
	 * Constructor.
	 * @param menuItem The menuItem whose enabled state will be updated.
	 */
	public AbstractEnableSupport(JMenuItem menuItem) {
		this.submenuListener = null;
		this.action = null;
		this.menuItem = menuItem;
		this.enableState = true;
	}

	/**
	 * Must be implemented by subclass and must call the setEnabled() method
	 * based on the policy of the subclass.
	 */
	public abstract void updateEnableState();

	protected void setEnabled(final boolean b) {
		
		//System.out.println(action + ", " + menuItem + " ========> menu enabled: " + b);
		
		enableState = b;
		
		if ( submenuListener != null )
			submenuListener.setEnabled(enableState);
		if ( action != null )
			action.setEnabled(enableState);
		if ( menuItem != null )
			menuItem.setEnabled(enableState);
	}

	/**
	 * Returns true if the action/menuListener/menuItem is enabled, false otherwise.
	 * @return true if the action/menuListener/menuItem is enabled, false otherwise.
	 */
	public synchronized final boolean isCurrentlyEnabled() {
		return enableState;
	}
}
