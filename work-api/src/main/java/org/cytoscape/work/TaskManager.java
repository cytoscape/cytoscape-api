package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
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


/**
 * Executes the {@link Task}s found in the {@link TaskIterator} provided by a {@link TaskFactory}. 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
public interface TaskManager<T,C> {

	/**
	 * Returns a configuration object generated from the {@link Tunable}s
	 * read from the {@link TaskFactory}.
	 * @param factory The {@link TaskFactory} that will be scanned for {@link Tunable}s.
	 * @param tunableContext An object providing context for the {@link Tunable}s. 
	 * @return a configuration object generated from the {@link Tunable}s
	 * read from the {@link TaskFactory}.
	 */
	 T getConfiguration(TaskFactory factory, Object tunableContext);

	/**
	 * Allows a user of a TaskManager to set the execution context for
	 * the task, for example the parent Window of a dialog or the top-level
	 * menu for menu generation.
	 * @param context The object to serve as the execution context for the TaskManager.
	 */
	void setExecutionContext(C context);

	/**
	 * This method is called to execute the {@link Task}s in a {@link TaskIterator} provided
	 * by a {@link TaskFactory}.  
	 * This method returns once the {@link Task}s derived from the {@link TaskIterator}
	 * returned by the {@link TaskFactory}'s <code>createTaskIterator()</code> method have
	 * started (but not necessarily completed) execution. 
	 * It <i>does not wait</i> for the {@link Task}s to finish. 
	 * @param iterator The {@link TaskIterator} whose tasks will be executed.
	 */
	void execute(TaskIterator iterator);

	/**
	 * This method is called to execute the {@link Task}s in a {@link TaskIterator} provided
	 * by a {@link TaskFactory}.  
	 * This method returns once the {@link Task}s derived from the {@link TaskIterator}
	 * returned by the {@link TaskFactory}'s <code>createTaskIterator()</code> method have
	 * started (but not necessarily completed) execution. 
	 * It <i>does not wait</i> for the {@link Task}s to finish, however, the {@link TaskObserver}s
	 * taskFinished method is called with the results of any of the tasks in the {@link TaskIterator}
	 * that are {@link ObservableTask}s.
	 *
	 * @param iterator The {@link TaskIterator} whose tasks will be executed.
	 * @param observer The {@link TaskObserver} that will be called with the results of the {@link ObservableTask}s.
	 */
	void execute(TaskIterator iterator, TaskObserver observer);

	/**
	 * This method returns the {@link TunableMutator} that is being used by this TaskManager.
	 * 
	 * @return the {@link TunableMutator} being used by the TaskManager, or <b>null</b>
	 * if none is being used.
	 */
	default TunableMutator<?,?> getTunableMutator() { return null; }
}
