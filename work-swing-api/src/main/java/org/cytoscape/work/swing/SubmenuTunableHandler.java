package org.cytoscape.work.swing;


import javax.swing.JMenuItem;
import java.util.List;

import org.cytoscape.work.TunableHandler;
import org.cytoscape.work.TaskFactory;

/**
 * A TunableHandler specifically for generating dynamic submenus.
 */
public interface SubmenuTunableHandler extends TunableHandler {

	/**
	 * Returns the dynamically created submenu item that will
	 * be used by DynamicSubmenusListener.
	 * @return the dynamically created submenu item that will
	 * be used by DynamicSubmenusListener.
	 */
	public JMenuItem getSubmenuItem();

	/**
	 * This method will be called when a submenu is chosen. The intent
	 * is for this method to then set the value in the ListSingleSelection object.
	 * @param menuName The name of the menu selected.
	 */
	public void chosenMenu(String menuName);

	/**
	 * Because clicking on a submenu is already an "action" we need to be able
	 * actually tell the handler which task to execute and how. 
	 * @param dtm The DialogTaskManager to be used to execute the TaskFactory.
	 * @param tf The TaskFactory to be executed.
	 */
	public void setExecutionParams(DialogTaskManager dtm, TaskFactory tf);
}
