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

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.swing.DynamicSubmenuListener;

/**
 * A class that allows the enabled state of an Action of JMenuItem to be managed in 
 * a consistent way. 
 * 
 * @CyAPI.Final.Class
 */
final class TaskFactoryEnableSupport extends AbstractEnableSupport {

	private final TaskFactory tfp;

	/**
	 * Constructor.
	 * @param submenuListener The submenu listener whose enabled state will be updated.
	 * @param tfp The task factory enabler that indicates whether or not the submenu
	 * listener should be enabled.
	 */
	public TaskFactoryEnableSupport(DynamicSubmenuListener submenuListener, TaskFactory tfp) {
		super(submenuListener);
		this.tfp = tfp;
	}

	/**
	 * Constructor.
	 * @param action The action whose enabled state will be updated.
	 * @param tfp The task factory enabler that indicates whether or not the action
	 * should be enabled.
	 */
	public TaskFactoryEnableSupport(Action action, TaskFactory tfp) {
		super(action);
		this.tfp = tfp;
	}

	/**
	 * Constructor.
	 * @param menuItem The menuItem whose enabled state will be updated.
	 * @param tfp The task factory enabler that indicates whether or not the menu item
	 * should be enabled.
	 */
	public TaskFactoryEnableSupport(JMenuItem menuItem, TaskFactory tfp) {
		super(menuItem);
		this.tfp = tfp;
	}

	/**
	 * Updates the enable state for the specified action/menuListener/menuItem
	 * for the specified enableFor description and the state of the system.
	 */
	public void updateEnableState() {
		setEnabled( tfp.isReady() );
	}
}
