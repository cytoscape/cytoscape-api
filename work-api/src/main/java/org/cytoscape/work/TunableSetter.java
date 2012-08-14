

package org.cytoscape.work;

import java.util.Map;

/**
 * An API for setting tunable fields and methods with predetermined
 * values in the Tasks found in the specified TaskIterator. This 
 * interface is provided as a convenience to TaskFactory authors
 * who also want to provide an additional API to their TaskFactories that
 * allow Tasks to be configured with method parameters.
 * TunableSetters are provided as OSGi services.
 * <br/>
 * @CyAPI.Api.Interface
 */
public interface TunableSetter {

	/**
	 * This method takes as input a TaskIterator and a map of tunable names to tunable values and applies
	 * those values to any tunables found in the Tasks found in the TaskIterator.  The return value is
	 * a new TaskIterator containing pre-configured tasks.
	 * @param taskIterator The incoming TaskIterator which contains the tasks whose tunables will be set.
	 * @param tunableValues A map of names to tunable values.  The names must match the field or method
	 * name of the tunable in question.
	 * @return A new TaskIterator that contains Task(s) with tunable values already set. 
	 */
	TaskIterator createTaskIterator(TaskIterator taskIterator, Map<String,Object> tunableValues);

}
