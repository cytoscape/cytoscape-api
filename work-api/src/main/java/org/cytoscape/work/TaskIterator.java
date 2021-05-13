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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/** 
 * A TaskIterator provides the functionality of sequencing {@link Task}s.  {@link Task}s provided by TaskIterator
 * can be executed by calling a {@link TaskManager}'s execute() method.
 * @CyAPI.Final.Class
 * @CyAPI.InModule work-api
 */
public final class TaskIterator implements Iterator<Task> {
	private final List<Task> tasks;
	private int currentIndex;
	private int numTasks;

	/** 
	 * Constructs an iterator that will yield Tasks in the order that they were passed into
	 * this constructor. 
	 * @param expectedNumTasks The total number of tasks that the initialTasks are likely to spawn. 
	 * @param initialTasks the Tasks to place into the iterator.
	 */
	public TaskIterator(int expectedNumTasks, final Task... initialTasks) {
		this.tasks = new ArrayList<Task>(initialTasks.length);
		this.currentIndex = 0;
		this.numTasks = expectedNumTasks;

		for (final Task initialTask : initialTasks) {
			tryToAddSelfReferenceToTask(initialTask);
			tasks.add(initialTask);
		}
	}

	/** 
	 * Constructs an iterator that will yield Tasks in the order that they were passed into
	 * this constructor.
	 * @param initialTasks the Tasks to place into the iterator.
	 */
	public TaskIterator(final Task... initialTasks) {
		this(initialTasks.length,initialTasks);
	}

	/** 
	 * Inserts "newTasks" immediately after "referenceTask".
	 * @param referenceTask task to insert newTasks after.
	 * @param newTasks tasks to insert after referenceTask.
	 * @throws IllegalStateException if "referenceTask" is not known to the iterator.
	 */
	public void insertTasksAfter(final Task referenceTask, final Task... newTasks) throws IllegalStateException {
		final int referenceIndex = tasks.indexOf(referenceTask);
		if (referenceIndex == -1)
			throw new IllegalStateException("invalid reference task in call to insertTaskAfter().");

		numTasks += newTasks.length;

		int offset = 0;
		for (final Task newTask : newTasks) {
			tryToAddSelfReferenceToTask(newTask);
			++offset;
			tasks.add(referenceIndex + offset, newTask);
		}
	}

	/** Inserts "newTasks" immediately after "referenceTask".
	 * @param referenceTask the {@link Task} to insert "newTasks" after.
	 * @param newTasks the {@link TaskIterator} the tasks to insert.
	 *  @throws IllegalStateException if "referenceTask" is not known to the iterator.
	 */
	public void insertTasksAfter(final Task referenceTask, final TaskIterator newTasks) throws IllegalStateException {
		final int referenceIndex = tasks.indexOf(referenceTask);
		if (referenceIndex == -1)
			throw new IllegalStateException("invalid reference task in call to insertTaskAfter().");

		numTasks += newTasks.getNumTasks();

		int offset = 0;
		while (newTasks.hasNext()) {
			final Task newTask = newTasks.next();
			tryToAddSelfReferenceToTask(newTask);
			++offset;
			tasks.add(referenceIndex + offset, newTask);
		}
	}

	/** 
	 * Returns true if a call to next() would return another Task, otherwise false.
	 * @return true if a call to next() would return another Task, otherwise false.
	 */
	public boolean hasNext() {
		return currentIndex < tasks.size();
	}

	/** 
	 * Returns the next Task in order if the TaskIterator can still yield more Tasks, otherwise throws
	 * an exception.
	 * @return the next Task in order if the TaskIterator can still yield more Tasks, otherwise throws
	 * an exception.
	 */
	public Task next() {
		if (currentIndex < tasks.size()) {
			++currentIndex;
			return tasks.get(currentIndex - 1);
		}

		throw new NoSuchElementException("call to next() even though hasNext() is false.");
	}

	/**
	 * Unsupported -&gt; always throws an exception!
	 * @throws UnsupportedOperationException
	 */
	public void remove() {
		throw new UnsupportedOperationException("TaskIteratorImpl.remove() has not been implemented.");
	}

	/** 
	 * Adds a reference to the TaskIterator to Tasks that are capable of maintaining such a reference, i.e.
	 * Tasks derived from AbstractTask.
	 */
	private void tryToAddSelfReferenceToTask(final Task newTask) {
		if (newTask instanceof AbstractTask) {
			try {
				((AbstractTask)newTask).setTaskIterator(this);
			} catch (final Exception e) {
				// The above cast must always succeed and therefore we should never get here!
			}
		}
	}

	/**
	 * Returns the current total number of tasks in the iterator. As tasks
	 * get added to the iterator, this number will change, so this should
	 * not be viewed as a fixed or final value!
	 * 
	 * @return the current total number of tasks in the iterator. 
	 */
	public int getNumTasks() {
		return numTasks;
	}
	
	/**
	 * Adds the given task to the end of this iterator.
	 * @param task task to insert
	 */
	public void append(Task task) {
		++numTasks;

		tryToAddSelfReferenceToTask(task);
		tasks.add(task);
	}
	
	/**
	 * Adds the tasks in the given TaskIterator to the end of this iterator.
	 * @param taskIterator the tasks to insert
	 */
	public void append(TaskIterator taskIterator) {
		numTasks += taskIterator.getNumTasks();
		
		while (taskIterator.hasNext()) {
			final Task newTask = taskIterator.next();
			tryToAddSelfReferenceToTask(newTask);
			tasks.add(newTask);
		}
	}
}


