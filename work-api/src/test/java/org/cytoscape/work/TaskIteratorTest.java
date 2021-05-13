package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
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


import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import org.junit.Test;


public class TaskIteratorTest {
	@Test
	public final void testInsertTasksAfter() throws Exception {
		final Task initialTask = new SimpleTask(1);
		final TaskIterator iter = new TaskIterator(initialTask);
		iter.insertTasksAfter(initialTask, new SimpleTask(2));
		iter.insertTasksAfter(initialTask, new SimpleTask(3));

		Task firstTask = iter.next();
		assertEquals("Task sequence error (1)!", 1, ((SimpleTask)firstTask).getId());
		Task secondTask = iter.next();
		assertEquals("Task sequence error (2)!", 3, ((SimpleTask)secondTask).getId());
		Task thirdTask = iter.next();
		assertEquals("Task sequence error (3)!", 2, ((SimpleTask)thirdTask).getId());
	}

	@Test
	public final void testInsertTasksAfterUsingAnIterator() throws Exception {
		final Task initialTask = new SimpleTask(1);
		final TaskIterator iter = new TaskIterator(initialTask);
		final TaskIterator newIter = new TaskIterator(new SimpleTask(2), new SimpleTask(3));
		iter.insertTasksAfter(initialTask, newIter);

		Task firstTask = iter.next();
		assertEquals("Task sequence error (1)!", 1, ((SimpleTask)firstTask).getId());
		Task secondTask = iter.next();
		assertEquals("Task sequence error (2)!", 2, ((SimpleTask)secondTask).getId());
		Task thirdTask = iter.next();
		assertEquals("Task sequence error (3)!", 3, ((SimpleTask)thirdTask).getId());
	}

	@Test(expected=UnsupportedOperationException.class)
	public final void testRemove() throws Exception {
		final TaskIterator iter = new TaskIterator();
		iter.remove();
	}

	@Test(expected=NoSuchElementException.class)
	public final void testNext() throws Exception {
		final Task initialTask = new SimpleTask(1);
		final TaskIterator iter = new TaskIterator(initialTask);
		assertNotNull("We should have gotten a task here!", iter.next());

		// This should blow up!
		iter.next();
	}

	@Test
	public final void testNumTasks() throws Exception {
		final Task initialTask = new SimpleTask(1);
		final Task initialTask2 = new SimpleTask(2);
		final TaskIterator iter = new TaskIterator(initialTask, initialTask2);
		assertEquals(2,iter.getNumTasks());
	}
	
	@Test
	public final void testAppendTask() throws Exception {
		final Task initialTask = new SimpleTask(1);
		final TaskIterator iter = new TaskIterator(initialTask);
		iter.append(new SimpleTask(2));
		iter.append(new SimpleTask(3));

		Task firstTask = iter.next();
		assertEquals("Task sequence error (1)!", 1, ((SimpleTask)firstTask).getId());
		Task secondTask = iter.next();
		assertEquals("Task sequence error (2)!", 2, ((SimpleTask)secondTask).getId());
		Task thirdTask = iter.next();
		assertEquals("Task sequence error (3)!", 3, ((SimpleTask)thirdTask).getId());
		
		iter.append(new SimpleTask(4));
		Task fourthTask = iter.next();
		assertEquals("Task sequence error (4)!", 4, ((SimpleTask)fourthTask).getId());
	}
	
	@Test
	public final void testAppendTaskIterator() throws Exception {
		final Task initialTask = new SimpleTask(1);
		final TaskIterator iter = new TaskIterator(initialTask);
		final TaskIterator iter2 = new TaskIterator(new SimpleTask(2), new SimpleTask(3));
		iter.append(iter2);

		Task firstTask = iter.next();
		assertEquals("Task sequence error (1)!", 1, ((SimpleTask)firstTask).getId());
		Task secondTask = iter.next();
		assertEquals("Task sequence error (2)!", 2, ((SimpleTask)secondTask).getId());
		Task thirdTask = iter.next();
		assertEquals("Task sequence error (3)!", 3, ((SimpleTask)thirdTask).getId());
		
		iter.append(new TaskIterator(new SimpleTask(4)));
		Task fourthTask = iter.next();
		assertEquals("Task sequence error (4)!", 4, ((SimpleTask)fourthTask).getId());
	}
}


class SimpleTask implements Task {
	private int id;

	SimpleTask(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void run(TaskMonitor taskMonitor) throws Exception {
		// Intentionally do nothing!
	}

	public void cancel() {
		// Intentionally do nothing!
	}

	public boolean cancelled() {
		return false;
	}
}
