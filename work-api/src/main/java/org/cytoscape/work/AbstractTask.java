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

/**
 * A base class for tasks that need to be able to access the TaskIterator that contains them.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule work-api
 */
public abstract class AbstractTask implements Task {
	/**
	 * If this is true, we should abort whatever the <code>Task</code> is currently doing in the run() method.
	 */
	protected volatile boolean cancelled = false;

	private TaskIterator taskIterator;

	/** 
	 * This method is used by a {@link TaskIterator} to set itself on the newly added Task. This
	 * method should not be called by otherwise.
	 * @param taskIterator TaskIterator that will set itself on the newly added task.
	 */
	public final void setTaskIterator(final TaskIterator taskIterator) {
		this.taskIterator = taskIterator;
	}
	
	public TaskIterator getTaskIterator() {
		return taskIterator;
	}

	/** 
	 * Inserts "newTasks" after the current Task, in the TaskIterator that is being managed by this class.
	 * @param newTasks tasks to insert after the current task in the TaskIterator that is being managed by
	 * this class.
	 */
	protected final void insertTasksAfterCurrentTask(final Task... newTasks) {
		taskIterator.insertTasksAfter(this, newTasks);
	}

	/** 
	 * Inserts "newTasks" after the current Task, in the TaskIterator that is being managed by this class.
	 * @param newTasks the TaskIterator to insert after the current task in the TaskIterator that is being 
	 * managed by this class.
	 */
	protected final void insertTasksAfterCurrentTask(final TaskIterator newTasks) {
		taskIterator.insertTasksAfter(this, newTasks);
	}

	@Override
	abstract public void run(TaskMonitor taskMonitor) throws Exception;

	/** 
	 * Calling this attempts to abort the current <code>Task</code>.  How well this works depends 
	 * on the granularity of the implementing <code>Task</code> checking whether the "canceled" 
	 * is true or not and then taking appropriate action.
	 */
	@Override
	public void cancel() {
		cancelled = true;
	}
}
