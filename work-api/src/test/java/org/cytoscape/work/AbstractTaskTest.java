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