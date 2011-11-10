package org.cytoscape.work;


/**
 *  Returns an instance of a {@link TaskIterator}.  Intended to be 
 *  used as an OSGi service, singleton, every type of <code>Task</code> has one.
 *  <br />
 *  Let's say you'd like to do some kind of processing, let's call it "X".  Then you would create a
 *  class called XTask, where the processing would be taking place in XTask's run() method.  Assuming
 *  X needs to have two types of data, let's call them Y and Z, in order to control its behavior.
 *  This would imply that XTask's constructor should take an X and a Y as parameters.  And XTaskFactory
 *  should have setY() and setZ() methods.  XTaskFactory's getTaskIterator() method would then construct
 *  an XTask with the Y and Z that it has available and create a {@link TaskIterator} with this single Task to
 *  iterate over.
 *  @CyAPI.Spi.Interface #ASKMIKE
 */
public interface TaskFactory {
	/** 
	 * Returns an iterator returning a sequence of <code>Task</code>s.
	 * @return an iterator returning a sequence of <code>Task</code>s.
	 *
	 *  Note: Most factory's returned iterator only yields a single <code>Task</code>.
	 */
	TaskIterator getTaskIterator();
}
