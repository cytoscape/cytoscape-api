/**
  This package defines the task framework, where tasks are units of work.

  <h3>Tasks are like action listeners</h3>
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

  <h3>Why tasks?</h3>
  <p>
  Cytoscape is in an OSGi environment. Its components are deliberately made to
  be loosely coupled, and this loose coupling changes how we think
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

  <h3>Why task factories?</h3>
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
  Moreover, a single task object neatly contains state that does not
  spill over into separate task invocations.
  (If you know a functional programming, here is an
  analogy: a task is like an inner function
  that the task factory function returns; the task gets inputs from free variables
  defined in the task factory function.)
  </p>
  
  <h3>Overview of a menu item with a task factory</h3>
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
  Cytoscape tells the task factory to create a task, then invokes it. Tasks
  are used for more than just creating menu items. For example, they are used for creating
  toolbar items as well.
  </p>

  <h3>Task iterators</h3>
  <p>
  Sometimes when a task is executing, you need to run additional
  tasks after it is done. <i>Task iterators</i> let you create a series of tasks
  that you control what task to execute next, even while a task in that iterator
  is executing.
  </p>

  <h3>Task managers</h3>
  <p>
  Most of the time, you export a task factory service, and Cytoscape
  invokes the task for you. Sometimes, you will want to invoke a task yourself.
  In this case, you import the {@link org.cytoscape.work.TaskManager} service
  and call the {@link org.cytoscape.work.TaskManager#execute} method to
  run the task.
  </p>

  <h3>Action listeners are dead&mdash;long live tasks</h3>
  <p>
  The analogy of tasks as an OSGi equivalent of action listeners sweeps many
  of its advantages under the rug. Here are some additional benefits of tasks:
  <ul>
   <li>
   Tasks are run asynchronously. This means you can encapsulate
   complicated, long-running algorithms entirely in a single task.
   This will not freeze Cytoscape.
   </li>
   <li>
   Because tasks can take a long time to complete,
   they can inform the user of its progress through {@link org.cytoscape.work.TaskMonitor}.
   {@code TaskMonitor}s are passed in to the task's {@code run} method.
   In the task's {@code run} method, you call its methods to update the user interface.
   </li>
   <li>
   Tasks can throw exceptions in the {@code run} method. Cytoscape will catch the exception and
   inform the user.
   </li>
   <li>
   Tasks can be cancelled by the user. Tasks are required to implement the {@code cancel} method.
   Long-running tasks are required to respond to user cancellations and return from the
   {@code run} method gracefully.
   </li>
   <li>
   Tasks are independent of Swing. Provided that
   the {@code run} method does not explicitly use Swing,
   the task can be used in other environments besides Swing, like
   the command-line.
   </li>
   <li>
   Tasks can have user-defined inputs called <i>tunables</i>. When Cytoscape detects that
   the task has tunables, it creates a user interface for user input.
   Cytoscape then fills in the tunables with the user's input, then executes the task.
   Tunables are great for algorithms with settings that the user can change before
   running the task.
   With tunables, you do not have to manually create a
   Swing dialog to get user input. If the task is run in the command-line, the user
   can still provide input.
   </li>
  </ul>
  </p>

  <h3>Examples</h3>
  <h4>Hello World Menu Item</h4>
  <pre>{@code
      // Example that displays a user message at the bottom of the Cytoscape desktop
      Logger logger = LoggerFactory.getLogger("CyUserMessages");
      Task myTask = new Task() {
          public void run(TaskMonitor monitor) {
              logger.info("Hey chef");
          }

          public void cancel() {
          }
      };
      TaskFactory myTaskFactory = new TaskFactory() {
          public TaskIterator createTaskIterator() {
              return new TaskIterator(myTask);
          }

          public boolean isReady() {
              return true;
          }
      };
      Properties props = new Properties();
      props.setProperty(PREFERRED_MENU,"Apps");
      props.setProperty(TITLE,"Why, hello there children");
      props.setProperty(MENU_GRAVITY,"1.0");
      props.setProperty(TOOLTIP,"Demonstrates how cool the work framework is");
      registerService(bc, myTaskFactory, TaskFactory.class, props);
  }</pre>

  <h4>Approximating &pi;</h4>
  Here is another example of a task that approximates &pi; using the Wallis product:
  <p align="center">
  <img src="http://latex.codecogs.com/gif.latex?\dpi{100} \frac{\pi}{2}=\prod^\infty_{n=1}\left( \frac{2n}{2n-1} \cdot \frac{2n}{2n+1} \right )=\left( \frac{2}{1} \cdot \frac{2}{3} \right ) \cdot \left( \frac{4}{3} \cdot \frac{4}{5} \right ) \cdot \left( \frac{6}{5} \cdot \frac{6}{7} \right ) \ldots" title="\dpi{150} \frac{\pi}{2}=\prod^\infty_{n=1}\left( \frac{2n}{2n-1} \cdot \frac{2n}{2n+1} \right )=\left( \frac{2}{1} \cdot \frac{2}{3} \right ) \cdot \left( \frac{4}{3} \cdot \frac{4}{5} \right ) \cdot \left( \frac{6}{5} \cdot \frac{6}{7} \right ) \ldots">
  </p>
  <pre>{@code
      // Example that approximates &pi;
      Logger logger = LoggerFactory.getLogger("CyUserMessages");
      final int iterations = 1000;
      Task myPiTask = new Task() {
          public void run(TaskMonitor monitor) {
              double pi = 2.0;
              for (int n = 1; n <= iterations; n++) {
                  pi *= (2 * n) * (2 * n) / ((2 * n - 1) * (2 * n + 1));
              }
              logger.info("Our approximation of pi is: " + Double.toString(pi));
          }

          public void cancel() {
          }
      };
      TaskFactory myTaskFactory = new TaskFactory() {
          public TaskIterator createTaskIterator() {
              return new TaskIterator(myPiTask);
          }

          public boolean isReady() {
              return true;
          }
      };
      Properties props = new Properties();
      props.setProperty(PREFERRED_MENU,"Apps");
      props.setProperty(TITLE,"Approximate Pi");
      props.setProperty(MENU_GRAVITY,"1.0");
      props.setProperty(TOOLTIP,"Approximates pi using the Wallis product");
      registerService(bc, myTaskFactory, TaskFactory.class, props);
  }</pre>

  <h4>Using a task monitor</h4>
  The problem with the above example is that if the &pi; calculation takes a long time,
  the user is not informed of its progress.
  Here, we modify {@code myPiTask} so that it informs the user during this long calculation
  by using the task monitor.

  <pre>{@code
      final int iterations = 1000;
      Task myPiTask = new Task() {
          public void run(TaskMonitor monitor) {
              monitor.setTitle("Calculating Pi");
              double pi = 2.0;
              for (int n = 1; n <= iterations; n++) {
                  monitor.setProgress(((double) n) / iterations);
                  pi *= (2 * n) * (2 * n) / ((2 * n - 1) * (2 * n + 1));
              }
              logger.info("Our approximation of pi is: " + Double.toString(pi));
          }

          public void cancel() {
          }
      };
  }</pre>

  <h4>Cancelling a task</h4>
  The problem with our task is that it
  does not adequately respond to user cancellation.
  When the user cancels the task, nothing happens. We have to fill in the
  {@code cancel} method. The main challenge of dealing with cancellation
  is that the thread executing {@code run} is different
  from the one executing {@code cancel}. We have to come up with a way
  to communicate cancellation from {@code cancel} to {@code run}.
  Here is how we deal with this issue:

  <pre>{@code
      final int iterations = 1000;
      Task myPiTask = new Task() {
          boolean cancelled = false;
          public void run(TaskMonitor monitor) {
              monitor.setTitle("Calculating Pi");
              double pi = 2.0;
              for (int n = 1; n <= iterations; n++) {
                  if (cancelled)
                      break;
                  monitor.setProgress(((double) n) / iterations);
                  pi *= (2 * n) * (2 * n) / ((2 * n - 1) * (2 * n + 1));
              }
              if (!cancelled)
                  logger.info("Our approximation of pi is: " + Double.toString(pi));
          }

          public void cancel() {
              cancelled = true;
          }
      };
  }</pre>

  When the user hits the Cancel button, Cytoscape invokes the {@code cancel} method.
  This switches the {@code cancelled} variable to true. When {@code run} starts another
  iteration of the {@code for} loop, it checks the {@code cancelled} variable.
  Since {@code cancelled} was flipped, it stops the loop.
  In other words, the {@code cancel} method tells the {@code run} method to stop through
  the {@code cancelled} variable.

  <p>
  Communicating cancellation through a boolean variable
  works well for tasks with loops, since each loop iteration
  can just check if the variable has changed and stop.
  </p>

  <p>
  Tasks that do I/O operations, however, require more consideration,
  like a task that downloads a file from some URL.
  There are several ways you can deal with cancellation during an
  I/O operation:
  <ul>
   <li>
   Create a variable that holds the thread executing {@code run}. When
   {@code run} first starts, fill in this variable with the method
   {@link java.lang.Thread#currentThread}. Now the {@code cancel} method
   can know the thread that is executing {@code run}.
   When cancellation occurs, call {@link java.lang.Thread#interrupt}
   on the thread executing {@code run}. Interrupting a thread
   in an I/O operation will sometimes stop it.
   </li>
   <li>
   If your {@code run} method is reading from an {@link java.io.InputStream},
   a {@link java.io.Reader}, a {@link java.net.Socket}, or a {@link java.net.URLConnection},
   call its {@code close} method from the {@code cancel} method.
   This will stop the I/O operation in the {@code run} method.
   </li>
   <li>
   Use Java's non-blocking I/O package: {@link java.nio}. Since non-blocking I/O operations
   are usually done in a loop, it works well with the approach of checking for cancellation
   from a variable. Using non-blocking I/O is more elegant, but it takes
   a lot more effort to write.
   </li>
  </ul>
  </p>
  
  <h4>A task with tunables</h4>
  We have hard-coded a value for the number of iterations to
  approximate &pi;. Thanks to tunables, we can easily give the
  user the option to specify
  the number of iterations. That way the user can decide how long to
  run our task&mdash;or how accurate our approximation should be.
  We make {@code iterations} a tunable whose value the user can specify.
  <pre>
      Task myPiTask = new Task() {
          {@literal @}Tunable(description="How many iterations of the Wallis product to compute?")
          public int iterations = 1000;
          boolean cancelled = false;
          public void run(TaskMonitor monitor) {
              monitor.setTitle("Calculating Pi");
              double pi = 2.0;
              for (int n = 1; n <= iterations; n++) {
                  if (cancelled)
                      break;
                  monitor.setProgress(((double) n) / iterations);
                  pi *= (2 * n) * (2 * n) / ((2 * n - 1) * (2 * n + 1));
              }
              if (!cancelled)
                  logger.info("Our approximation of pi is: " + Double.toString(pi));
          }

          public void cancel() {
              cancelled = true;
          }
      };
  </pre>
*/
package org.cytoscape.work;
