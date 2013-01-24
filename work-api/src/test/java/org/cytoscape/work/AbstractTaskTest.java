package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class AbstractTaskTest {
	private TaskIterator iter;
	private SimpleTask2 initialTask;

	@Before
	public void init() {
		initialTask = new SimpleTask2(1);
		iter = new TaskIterator(initialTask);
	}

	@Test
	public final void testTaskInsertion1() throws Exception {
		initialTask.insertTasksAfterCurrentTask(new SimpleTask2(2), new SimpleTask2(3));
		initialTask.insertTasksAfterCurrentTask(new SimpleTask2(4));

		final int expectedSequence[] = { 1, 4, 2, 3 };
		for (int taskId : expectedSequence) {
			assertTrue("Invalid task count in iterator!", iter.hasNext());
			final Task task = iter.next();
			assertEquals("Task ID does not match expected ID!", taskId, ((SimpleTask2)task).getId());
		}
		assertFalse("Invalid task count in iterator!", iter.hasNext());
	}

	@Test
	public final void testTaskInsertion2() throws Exception {
		final TaskIterator tasks = new TaskIterator(new SimpleTask2(2), new SimpleTask2(3));
		initialTask.insertTasksAfterCurrentTask(tasks);
		initialTask.insertTasksAfterCurrentTask(new SimpleTask2(4));

		final int expectedSequence[] = { 1, 4, 2, 3 };
		for (int taskId : expectedSequence) {
			assertTrue("Invalid task count in iterator!", iter.hasNext());
			final Task task = iter.next();
			assertEquals("Task ID does not match expected ID!", taskId, ((SimpleTask2)task).getId());
		}
		assertFalse("Invalid task count in iterator!", iter.hasNext());
	}

	@Test
	public final void testCancellation() throws Exception {
		final SimpleTask2 task = new SimpleTask2(1);
		assertFalse("\"cancelled\" must start out set to false!", task.cancelled());	
		task.cancel();
		assertTrue("\"cancelled\" must be true after a call to cancel()!", task.cancelled());
	}
}


class SimpleTask2 extends AbstractTask {
	private int id;

	SimpleTask2(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void run(TaskMonitor taskMonitor) throws Exception {
		// Intentionally do nothing!
	}

	public boolean cancelled() {
		return this.cancelled;
	}
}