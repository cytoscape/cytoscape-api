package org.cytoscape.work;


/**
 * Returns an instance of a {@link TaskIterator}.  Intended to be 
 * provided and used as an OSGi service. A TaskFactory is a singleton, although each type of
 * {@link Task} gets its own TaskFactory. For instance, a "load network from file" task will
 * have one TaskFactory and an "apply preferred layout" task will have a different TaskFactory. 
 * <br/>
 * Let's say you'd like to do some kind of processing, let's call it "X".  Then you would create a
 * class called XTask, where the processing would be taking place in XTask's run() method.  Assuming
 * X needs to have two types of data, let's call them Y and Z, in order to control its behavior.
 * This would imply that XTask's constructor should take an Y and a Z as parameters.  The
 * XTaskFactory should either be constructed with classes that provide access to Y and Z, or
 * XTaskFactory should be specialized with a different createTaskIterator(Y y, Z z) method.
 * The createTaskIterator method should always be able to fully construct a {@link TaskIterator}
 * without relying on any other mutable state within the TaskFactory.
 * @CyAPI.Spi.Interface 
 */
public interface TaskFactory {
	/** 
	 * Returns an iterator containing a sequence of <code>Task</code>s.
	 * @return an iterator containing a sequence of <code>Task</code>s.
	 */
	TaskIterator createTaskIterator();

	/**
	 * Returns true if the TaskFactory has all necessary preconditions needed to generate
	 * a task that will execute properly, false otherwise. 
	 * @return true if the TaskFactory has all necessary preconditions needed to generate
	 * a task that will execute properly, false otherwise.
	 */
	boolean isReady();
}
