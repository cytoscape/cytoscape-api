

package org.cytoscape.work;

import java.util.Map;

/**
 * A marker interface that indicates that the TaskManager in question will
 * execute the tasks found in the TaskFactory synchronously, rather than
 * asynchronously.
 */
public interface SynchronousTaskManager<T> extends TaskManager<T,Map<String,Object>> {

}
