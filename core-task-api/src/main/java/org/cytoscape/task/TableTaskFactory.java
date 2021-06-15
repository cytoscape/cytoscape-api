package org.cytoscape.task;

import org.cytoscape.model.CyTable;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.Togglable;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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
 * A task factory that creates one or more tasks that operate on the specified CyTable.
 * 
 * <p>
 * Example of creating a button in the table browser toolbar...
 * </p>
 * 
 * <pre>{@code 
 * CountRowsTableTaskFactory tableTaskFactory = new CountRowsTableTaskFactory();
		
 * var iconFont = iconManager.getIconFont(22.0f);
 * var icon = new TextIcon(IconManager.ICON_CALCULATOR, iconFont, 32, 32);
 * var iconId = "test::countrows";
 * iconManager.addIcon(iconId, icon);
 * 
 * Properties props = new Properties();
 * props.setProperty(ServiceProperties.ENABLE_FOR, "table");
 * props.setProperty(ServiceProperties.TOOLTIP, "Count Table Rows...");
 * props.setProperty(ServiceProperties.LARGE_ICON_ID, iconId);
 * props.setProperty(ServiceProperties.TOOL_BAR_GRAVITY, "" + 99);
 * props.setProperty(ServiceProperties.IN_NODE_TABLE_TOOL_BAR, "true");
 * props.setProperty(ServiceProperties.IN_EDGE_TABLE_TOOL_BAR, "true");
 * props.setProperty(ServiceProperties.IN_NETWORK_TABLE_TOOL_BAR, "true");
 * props.setProperty(ServiceProperties.IN_UNASSIGNED_TABLE_TOOL_BAR, "true");
 * 
 * registerService(bc, tableTaskFactory, TableTaskFactory.class, props);
 * }</pre>
 * 
 * <p>
 * In order to create a toggleable button the implementation must also implement the {@link Togglable} interface.
 * </p>
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule core-task-api
 */
public interface TableTaskFactory {

	/** 
	 * Used to provision this factory with a {@link CyTable} that will be used to create tasks.
	 * @param table a non-null CyTable
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute.
	 */
	TaskIterator createTaskIterator(CyTable table);

    /**
     * Returns true if this task factory is ready to produce a TaskIterator.
	 * @param table a non-null CyTable
     * @return true if this task factory is ready to produce a TaskIterator.
     */
	boolean isReady(CyTable table);
	
	/**
	 * Determines if the task factory should appear for the given table.
	 * 
	 * @param table
	 * @since 3.9
	 */
	default boolean isApplicable(CyTable table) {
    	return true;
    }
	
	/**
     * If this task factory implements the {@link Togglable} interface then 
     * this method determines if the button or check box is on or off.
     * 
     * @since 3.9
     */
    default boolean isOn(CyTable table) {
    	return false;
    }
}
