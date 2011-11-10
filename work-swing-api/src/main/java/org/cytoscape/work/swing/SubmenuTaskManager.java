

package org.cytoscape.work.swing;



import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskManager;

/**
 * A specialization of a TaskManager that creates a DynamicSubmenuListener
 * configuration object.  The DynamicSubmenuListener object will dynamically
 * create a set of submenus based on the presense of a 
 * {@link org.cytoscape.work.util.ListSingleSelection}&lt;String&gt; field
 * or method annotated with {@link org.cytoscape.work.Tunable}.  The strings
 * found in the ListSingleSelection object will define the submenus.
 * @CyAPI.Api.Interface
 */
public interface SubmenuTaskManager extends TaskManager<DynamicSubmenuListener,Object> {
	
}
