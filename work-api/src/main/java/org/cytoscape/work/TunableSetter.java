package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
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

import java.util.Map;

/**
 * An API for setting tunable fields and methods with predetermined
 * values in the Tasks found in the specified TaskIterator. This 
 * interface is provided as a convenience to TaskFactory authors
 * who also want to provide an additional API to their TaskFactories that
 * allow Tasks to be configured with method parameters.
 * TunableSetters are provided as OSGi services.
 * <br/>
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
public interface TunableSetter {

	/**
	 * This method takes as input a TaskIterator and a map of tunable names to tunable values and applies
	 * those values to any tunables found in the Tasks found in the TaskIterator.  The return value is
	 * a new TaskIterator containing pre-configured tasks.
	 * @param taskIterator The incoming TaskIterator which contains the tasks whose tunables will be set.
	 * @param tunableValues A map of names to tunable values.  The names must match the field or method
	 * name of the tunable in question.
	 * @return A new TaskIterator that contains Task(s) with tunable values already set. 
	 */
	TaskIterator createTaskIterator(TaskIterator taskIterator, Map<String,Object> tunableValues);

	/**
	 * This version of createTaskIterator adds a TaskObserver argument.  Since the tasks are preconfigured
	 * the implementation of a TunableSetter acts somewhat like a TaskManager, so it has to handle the
	 * ObservableTasks.
	 *
	 * @param taskIterator The incoming TaskIterator which contains the tasks whose tunables will be set.
	 * @param tunableValues A map of names to tunable values.  The names must match the field or method
	 * name of the tunable in question.
	 * @param observer The TaskObserver that will handle any ObservableTask results
	 * @return A new TaskIterator that contains Task(s) with tunable values already set. 
	 */
	TaskIterator createTaskIterator(TaskIterator taskIterator, Map<String,Object> tunableValues, TaskObserver observer);

	/**
	 * Applies each value from the key-value pairs in tunableValues to the tunable in the
	 * given object whose name matches the corresponding key.
	 * @param object The object whose tunables will be set.
	 * @param tunableValues A map of names to tunable values.  The names must match the field or method
	 * name of the tunable in question.
	 */
	void applyTunables(Object object, Map<String,Object> tunableValues);
}
