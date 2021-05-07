package org.cytoscape.application.swing;

import java.net.URL;
import java.util.Map;

import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuListener;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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
	 * @return true if the action should be in the tool bar.
	 */
	boolean isInToolBar();
	
	/**
	 * Returns whether the action should be in the <b>Node Table Panel</b>'s tool bar.
	 * @return true if the action should be in the Node Table Panel's tool bar.
	 */
	default boolean isInNodeTableToolBar() {
		return false;
	}
	
	/**
	 * Returns whether the action should be in the <b>Edge Table Panel</b>'s tool bar.
	 * @return true if the action should be in the Edge Table Panel's tool bar.
	 */
	default boolean isInEdgeTableToolBar() {
		return false;
	}
	
	/**
	 * Returns whether the action should be in the <b>Network Table Panel</b>'s tool bar.
	 * @return true if the action should be in the Network Table Panel's tool bar.
	 */
	default boolean isInNetworkTableToolBar() {
		return false;
	}
	
	/**
	 * Returns whether the action should be in the <b>Unassigned Tables Panel</b>'s tool bar.
	 * @return true if the action should be in the Unassigned Tables Panel's tool bar.
	 */
	default boolean isInUnassignedTableToolBar() {
		return false;
	}

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
	 * Use {@link #putValue(String, Object)} to set the value of {@link Action#SELECTED_KEY} to true
	 * in order to have the menu item checked.
	 * @return whether or not a checkbox menu item should be used.
	 */
	boolean useCheckBoxMenuItem();
	
	/**
	 * Returns whether or not a toggle button should be used in the tool bar.
	 * Only valid when {@link #setIsInToolBar(boolean)} is also set to true.
	 * Use {@link #putValue(String, Object)} to set the value of {@link Action#SELECTED_KEY} to true
	 * in order to have the toggle button selected.
	 * @return true toggle button should be used.
	 */
	default boolean useToggleButton() {
		return false;
	}

	/**
	 * Forces the action to update its enable state based on the criteria defined for the action.
	 */
	void updateEnableState();	

	/**
 	 * Returns the configuration properties that were passed to the CyAction when it was created.
 	 * @returns configuration properties as a Map.
 	 */
	Map<String,String> getProperties();

	/**
 	 * To support configurable menus and tools, setIsInMenuBar added for 3.6
 	 */
	void setIsInMenuBar(boolean b);
	
	/**
 	 * To support configurable menus and tools, setIsInToolBar added for 3.6
 	 */
	void setIsInToolBar(boolean b);
	
	/**
 	 * To support configurable menus and tools, added for 3.9
 	 */
	default void setIsInNodeTableToolBar(boolean b) {
		// Just ignore it here...
	}
	
	/**
	 * To support configurable menus and tools, added for 3.9
	 */
	default void setIsInEdgeTableToolBar(boolean b) {
		// Just ignore it here...
	}
	
	/**
	 * To support configurable menus and tools, added for 3.9
	 */
	default void setIsInNetworkTableToolBar(boolean b) {
		// Just ignore it here...
	}
	
	/**
	 * To support configurable menus and tools, added for 3.9
	 */
	default void setIsInUnassignedTableToolBar(boolean b) {
		// Just ignore it here...
	}
	
	/**
 	 * To support configurable menus and tools, setPreferredMenu added for 3.6
 	 */
	void setPreferredMenu(String menu);
	
	/**
 	 * To support configurable menus and tools, setToolbarGravity added for 3.6
 	 */
	void setToolbarGravity(float f);
	
	/**
 	 * To support configurable menus and tools, setMenuGravity added for 3.6
 	 */
	void setMenuGravity(float f);

	/**
 	 * Was defined in AbstractCyAction, but not in this interface
 	 */
	void setAcceleratorKeyStroke(KeyStroke ks);

	/**
	 * Optional image to be displayed in the target component's tool tip.
	 * @returns Path to an image file. The default value is <code>null</code>.
	 */
	default URL getToolTipImage() {
		return null;
	}
}
