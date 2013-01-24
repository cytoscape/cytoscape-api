

package org.cytoscape.work;

import java.util.Map;

/**
 * A marker interface that indicates that the TaskManager in question will
 * execute the tasks found in the TaskFactory synchronously, rather than
 * asynchronously.
 * @param <T> The generic type of this SynchronousTaskManager.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
public interface SynchronousTaskManager<T> extends TaskManager<T,Map<String,Object>> {

}
