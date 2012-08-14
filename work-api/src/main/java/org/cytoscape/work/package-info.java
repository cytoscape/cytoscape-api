/**
This package contains the foundational classes that form the task framework in Cytoscape. 
{@link org.cytoscape.work.Task}s represent a unit of
work meant to run in a separate thread. Examples of work that might be wrapped by a task include
loading a network or applying a layout. {@link org.cytoscape.work.Task} objects are meant to be single
use objects and are produced by {@link org.cytoscape.work.TaskFactory} singletons.  Each type of task
is produced by a unique {@link org.cytoscape.work.TaskFactory}, meaning there are many 
{@link org.cytoscape.work.TaskFactory}s in the system at once.  Rather than executing directly,
{@link org.cytoscape.work.Task}s are executed by a {@link org.cytoscape.work.TaskManager}.  
{@link org.cytoscape.work.TaskManager}s execute {@link org.cytoscape.work.TaskIterator}s, which are
iterators that contain one or more {@link org.cytoscape.work.Task}s to be executed in order. It
is frequently useful to break one unit of work into multiple {@link org.cytoscape.work.Task}s.
The {@link org.cytoscape.work.TaskManager} handles the parameterization, execution, 
{@link org.cytoscape.work.TaskMonitor}ing, and error handling related to the execution of 
{@link org.cytoscape.work.Task}s.  {@link org.cytoscape.work.Task}s can be parameterized with user
input (e.g. the name of a file to load) using {@link org.cytoscape.work.Tunable} annotations.  By
annotating public fields and methods in {@link org.cytoscape.work.Task} objects, the 
{@link org.cytoscape.work.TaskManager} will prompt the user to input required values and set the
values in the {@link org.cytoscape.work.Task} prior to execution.
 */
package org.cytoscape.work;
