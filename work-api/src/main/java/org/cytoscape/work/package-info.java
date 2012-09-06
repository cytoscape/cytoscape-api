/*
This package contains the task framework, where 
{@link org.cytoscape.work.Task}s represent a single unit of
asynchronous work meant to run in a separate thread.
Examples of work that might be wrapped by a task include
loading a network or applying a layout.

<p>
{@link org.cytoscape.work.Task} objects are meant to be single
use objects and are produced by {@link org.cytoscape.work.TaskFactory} singletons.  Each type of task
is produced by a unique {@link org.cytoscape.work.TaskFactory}, meaning there are many 
{@link org.cytoscape.work.TaskFactory}s in the system at once.
</p>

<p>
Rather than executing directly,
{@link org.cytoscape.work.Task}s are executed by a {@link org.cytoscape.work.TaskManager}.  
{@link org.cytoscape.work.TaskManager}s execute {@link org.cytoscape.work.TaskIterator}s, which 
contain one or more {@link org.cytoscape.work.Task}s to be executed in order. It
is frequently useful to break one unit of work into multiple {@link org.cytoscape.work.Task}s.
The {@link org.cytoscape.work.TaskManager} handles the parameterization, execution, 
{@link org.cytoscape.work.TaskMonitor}ing, and error handling related to the execution of 
{@link org.cytoscape.work.Task}s.
</p>


{@link org.cytoscape.work.Task}s can be parameterized with user
input (e.g. the name of a file to load) using {@link org.cytoscape.work.Tunable} annotations.  By
annotating public fields and methods in {@link org.cytoscape.work.Task} objects, the 
{@link org.cytoscape.work.TaskManager} will prompt the user to input required values and set the
values in the {@link org.cytoscape.work.Task} prior to execution.
 */

/**

  This package defines the task framework, where tasks are units of work.

  <p>
  Tasks are somewhat analogous to Swing action listeners. To recap Swing action
  listeners, let's say you want to create a menu item and listen to its
  selection. You would follow these steps:
  <ol>
   <li>Create a {@code JMenuItem} and assign it a name, icon, and tooltip.</li>
   <li>Write an action listener. This could be a named class or an anonymous class.</li>
   <li>Add the {@code ActionListener} to the menu item by calling the {@code addActionListener} method.
  </ol>
  When the user selects the menu item, your action listener is invoked.
  </p>

  <p>
  Cytoscape is in an OSGi environment. Its components are deliberately made to
  be loosely coupled, and this loose coupling is applied to tasks. It changes how we think
  about action listeners. With action listeners, you have to explicitly tell
  an object you want to listen to it by calling a method on that object.
  In a loosely decoupled environment, action listeners are not appropriate. Instead,
  you implicitly tell an object you want to listen to it by exporting an OSGi service.
  That object knows all the OSGi services interested in it and calls them when necessary.
  </p>

  <p>
  We establish <i>tasks</i> as a replacement for action listeners to take advantage
  of OSGi's loose coupling. A single task object can be thought of as an
  action listener. Tasks implement the {@code run} method, which is analogous
  to the action listener's {@code actionPerformed} method. <i>Task factories</i>
  create tasks. You export task factories as an OSGi service, not tasks.
  </p>

  <p>
  Why even have task factories? Why not just use tasks directly?
  Many times tasks need input parameters.
  For example, if you're performing the zoom in task, how would the task know which network
  view on which to perform the zoom? The {@code run} method, after all,
  does not allow for input parameters.
  To deal with this issue, you create a task factory that can take
  some parameters, then it returns a new task initialized with those parameters.
  The task can obtain those parameters either through its constructor or as an
  inner class that has access to the task factory's members.
  (If you know functional programming, a task is like an inner function
  that the task factory function returns; the task gets inputs from free variables
  defined in the task factory function.)
  Moreover, a single task object neatly contains state that does not
  spill over into separate task invocations because of task factories.
  </p>
  
  <p>
  To illustrate tasks and task factories, here is how you create a menu item
  in Cytoscape's OSGi environment:
  <ol>
   <li>
   Write a task factory.
   </li>
   <li>
   Create a list of properties about the task factory that specify the name of
   the menu item, its icon and tooltip.
   </li>
   <li>
   Register the task factory, along with its properties, an an OSGi service.
   </li>
  </ol>
  Cytoscape picks up the task factory, reads its properties, and creates a
  menu item just for that task factory. When that menu item is selected by the user,
  Cytoscape tells the task factory to create a task, then invokes it.
  </p>

  <p>
  Sometimes when a task is executing, you need to run additional
  tasks after it is done. <i>Task iterators</i> let you create a series of tasks
  that you control while a task in that iterator is executing.
  </p>

  <p>
  The analogy of a task as an action listener sweeps many advantages
  tasks have over action listeners under the rug. Tasks:
  <ul>
   <li>
   can inform the user of its progress through {@link org.cytoscape.work.TaskMonitor}.
   {@code TaskMonitor}s are passed in to the task's {@code run} method.
   In the task's {@code run} method, call methods like {@link org.cytoscape.work.TaskMonitor#setTitle}
   or {@link org.cytoscape.work.TaskMonitor#setProgress} to update the user interface.
   </li>
   <li>
   can throw exceptions in the {@code run} method. Cytoscape will catch the exception and
   inform the user.
   </li>
   <li>
   are run asynchronously. This means you can encapsulate
   complicated, long-running algorithms entirely in a single task.
   This will not freeze Cytoscape.
   </li>
   <li>
   can be cancelled by the user. Tasks are required to implement the {@code cancel} method.
   Long-running tasks must respond to the user cancelling the task and return from the
   {@code run} method gracefully.
   </li>
   <li>
   are independent of Swing. Provided that
   the {@code run} method does not explicitly use any Swing
   code, the task can be used in other environments like
   the command-line. When Cytoscape is run as a Swing application,
   it will create an appropriate Swing interface without you having to 
   explicitly create it.
   </li>
   <li>
   can have user-defined inputs called <i>tunables</i>. When Cytoscape detects that
   the task has tunables, it creates a user interface that lets the user input values.
   Cytoscape then fills in the tunables with those values, then executes the task.
   Because the work framework is independent of Swing, you will not have to create a
   Swing dialog to get user input. So if the task is run in the command-line, the user
   can still provide input parameters. Of course, when Cytoscape is running as a 
   Swing application, it will, behind-the-scenes, create a Swing dialog for the tunables.
   </li>
  </ul>
  </p>

  <p>
  </p>

  <h3>Examples</h3>
*/
package org.cytoscape.work;
