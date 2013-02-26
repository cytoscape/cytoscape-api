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

import java.util.Map;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuListener;

/**
 * An interface that describes how an action should be placed within 
 * the menus and/or toolbars of the Swing application.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CyAction extends Action, MenuListener, PopupMenuListener {

	/**
	 * Returns the name of the action.
	 * @return the name of the action.
	 */
	String getName();

	/**
	 * Returns whether the action should be in the menu bar.
	 * @return whether the action should be in the menu bar.
	 */
	boolean isInMenuBar();

	/**
	 * Returns whether the action should be in the tool bar.
	 * @return whether the action should be in the tool bar.
	 */
	boolean isInToolBar();

	/**
	 * Insert a separator before this menu item.  
	 *
	 * @return true if this Action should have a separator before it
	 */
	boolean insertSeparatorBefore();

	/**
	 * Insert a separator after this menu item. 
	 *
	 * @return true if this Action should have a separator after it
	 */
	boolean insertSeparatorAfter();

	/**
	 * Returns the gravity used to place the menu item for this action.
	 * Gravity is a numeric value associated with each menu item. MenuItems in the same menu pull-down
	 * are sorted in ascending order based on their gravity values.
	 * @return The gravity used to place the menu item for this action.
	 */
	float getMenuGravity();

	/**
	 * Returns the gravity used to place this action in the toolbar.
	 * Gravity is a numeric value associated with each menu item. MenuItems in the same menu pull-down
	 * are sorted in ascending order based on their gravity values.
	 * @return The gravity used to place this action in the toolbar.
	 */
	float getToolbarGravity();

	/**
	 * Returns the accelerator KeyStroke defined for this action. 
	 * Will return null if no accelerator is set.
	 * @return the accelerator KeyStroke defined for this action. 
	 * Will return null if no accelerator is set.
	 */
	KeyStroke getAcceleratorKeyStroke();

	/** 
	 * This method returns a Menu specification string. Submenus are preceded
	 * by dots in this string, so the result "File.Import" specifies the submenu
	 * "Import" of the menu "File". If the result is null, the menu will be
	 * placed in a default location.
	 * @return the string identifying the preferred menu.
	 */
	String getPreferredMenu();

	/** 
	 * Returns whether or not a checkbox menu item should be used.
	 * @return whether or not a checkbox menu item should be used.
	 */
	boolean useCheckBoxMenuItem();

	/**
	 * Forces the action to update its enable state based on the criteria defined for
	 * the action.
	 */
	void updateEnableState();	

	/**
 	 * Returns the configuration properties that were passed to the CyAction
 	 * when it was created.
 	 * @returns configuration properties as a Map.
 	 */
	Map<String,String> getProperties();
}
