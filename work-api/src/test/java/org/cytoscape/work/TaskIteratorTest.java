/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.work;


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
