package org.cytoscape.work;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/** A TaskIterator provides the functionality of sequencing tasks.  Tasks provided by TaskIterator
 *  can be executed by calling a TaskManager's execute() method.
 *
 */
public final class TaskIterator implements Iterator<Task> {
	private final List<Task> tasks;
	private int currentIndex;

	/** Constructs an iterator that will yield Tasks in the order that they were passed into
	 *  this constructor.
	 */
	public TaskIterator(final Task... initialTasks) {
		this.tasks = new ArrayList<Task>(initialTasks.length);
		this.currentIndex = 0;

		for (final Task initialTask : initialTasks) {
			tryToAddSelfReferenceToTask(initialTask);
			tasks.add(initialTask);
		}
	}

	/** Inserts "newTasks" immediately after "referenceTask".
	 *  @throws IllegalStateException if "referenceTask" is not known to the iterator.
	 */
	public void insertTasksAfter(final Task referenceTask, final Task... newTasks) throws IllegalStateException {
		final int referenceIndex = tasks.indexOf(referenceTask);
		if (referenceIndex == -1)
			throw new IllegalStateException("invalid reference task in call to insertTaskAfter()!");
		int offset = 0;
		for (final Task newTask : newTasks) {
			tryToAddSelfReferenceToTask(newTask);
			++offset;
			tasks.add(referenceIndex + offset, newTask);
		}
	}

	/** Inserts "newTasks" immediately after "referenceTask".
	 *  @throws IllegalStateException if "referenceTask" is not known to the iterator.
	 */
	public void insertTasksAfter(final Task referenceTask, final TaskIterator newTasks) throws IllegalStateException {
		final int referenceIndex = tasks.indexOf(referenceTask);
		if (referenceIndex == -1)
			throw new IllegalStateException("invalid reference task in call to insertTaskAfter()!");
		int offset = 0;
		while (newTasks.hasNext()) {
			final Task newTask = newTasks.next();
			tryToAddSelfReferenceToTask(newTask);
			++offset;
			tasks.add(referenceIndex + offset, newTask);
		}
	}

	/** @return true if a call to next() would return another Task, otherwise false
	 */
	public boolean hasNext() {
		return currentIndex < tasks.size();
	}

	/** @return the next Task in order if the TaskIterator can still yield more Tasks, otherwise throws
	 *          an exception
	 */
	public Task next() {
		if (currentIndex < tasks.size()) {
			++currentIndex;
			return tasks.get(currentIndex - 1);
		}

		throw new NoSuchElementException("call to next() even though hasNext() is false!");
	}

	/**
	 *  Unsupported -&gt; always throws an exception!
	 */
	public void remove() {
		throw new UnsupportedOperationException("TaskIteratorImpl.remove() has not been implemented!");
	}

	/** Adds a reference to the TaskIterator to Tasks that are capable of maintaining such a reference, i.e.
	 *  Tasks derived from AbstractTask.
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
}


