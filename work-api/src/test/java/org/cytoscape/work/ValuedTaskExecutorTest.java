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
import static org.mockito.Mockito.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;


public class ValuedTaskExecutorTest {

	private TaskMonitor tm = mock(TaskMonitor.class);

	@Test
	public void testSuccessfulRun() throws Exception {
		ValuedTask<String> t = new StringValuedTask("homer");
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(t);
		vte.run(tm);
		assertEquals("homer",vte.get());
	}

	@Test(expected=Exception.class)
	public void testExceptionRun() throws Exception {
		ValuedTask<String> t = new ExceptionValuedTask("homer");
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(t);
		vte.run(tm);
	}

	@Test(expected=ExecutionException.class)
	public void testExceptionRunInThread() throws Exception {
		ValuedTask<String> t = new ExceptionValuedTask("homer");
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(t);
		new Thread( new TaskRunner(vte) ).start();
		String s = vte.get();
	}

	@Test(expected=ExecutionException.class)
	public void testExceptionRunInThreadAltGet() throws Exception {
		ValuedTask<String> t = new ExceptionValuedTask("homer");
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(t);
		new Thread( new TaskRunner(vte) ).start();
		String s = vte.get(100,TimeUnit.MILLISECONDS);
	}


	@Test(expected=NullPointerException.class)
	public void testNullValuedTask() throws Exception {
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(null);
	}

	@Test
	public void testTimeoutSuccessRun() throws Exception {
		ValuedTask<String> t = new SlowValuedTask("homer");
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(t);
		new Thread( new TaskRunner(vte) ).start();
		assertEquals("homer",vte.get(600,TimeUnit.MILLISECONDS));
	}

	@Test
	public void testTimeoutFailureRun() throws Exception {
		ValuedTask<String> t = new SlowValuedTask("homer");
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(t);
		new Thread( new TaskRunner(vte) ).start();
		assertNull(vte.get(300,TimeUnit.MILLISECONDS));
	}

	@Test(expected=CancellationException.class)
	public void testCancelRun() throws Exception {
		ValuedTask<String> t = new SlowValuedTask("homer");
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(t);
		new Thread( new TaskRunner(vte) ).start();
		vte.cancel();
		String s = vte.get();
	}

	@Test(expected=CancellationException.class)
	public void testCancelRunAltGet() throws Exception {
		ValuedTask<String> t = new SlowValuedTask("homer");
		ValuedTaskExecutor<String> vte = new ValuedTaskExecutor<String>(t);
		new Thread( new TaskRunner(vte) ).start();
		vte.cancel();
		String s = vte.get(100,TimeUnit.MILLISECONDS);
	}

	private class TaskRunner implements Runnable {
		private Task task;
		TaskRunner(Task task) { this.task = task; }
		public void run() { try { task.run(tm); } catch (Exception e) { } }
	}

	private class StringValuedTask implements ValuedTask<String> {
		private String s;
		public StringValuedTask(String s) { this.s = s; }
		public String run(TaskMonitor tm) { return s; }
		public void cancel() {}
	}

	private class ExceptionValuedTask implements ValuedTask<String> {
		public ExceptionValuedTask(String s) { }
		public String run(TaskMonitor tm) throws Exception { throw new Exception("test"); }
		public void cancel() {}
	}

	private class SlowValuedTask implements ValuedTask<String> {
		private String s;
		public SlowValuedTask(String s) { this.s = s; }
		public String run(TaskMonitor tm) throws Exception {
			Thread.sleep(500);
			return s;
		}
		public void cancel() {}
	}
}
