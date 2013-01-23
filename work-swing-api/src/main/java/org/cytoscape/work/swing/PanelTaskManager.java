package org.cytoscape.work.swing;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import javax.swing.JPanel;

import org.cytoscape.work.TaskManager;


/**
 * A specialization of a TaskManager that creates a JPanel configuration
 * object and expects its execution context to be another JPanel.  This
 * TaskManager can be used for embedding JPanels within other GUIs.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-swing-api
 */
public interface PanelTaskManager extends TaskManager<JPanel,JPanel> {
	/**
	 * Validates any pending changes to the tunables in the given context
	 * object.  Any valid pending changes are written back to the context
	 * object.  Returns true if all tunable values in the context object are
	 * valid.
	 * @param tunableContext the object whose tunables should be validated.
	 * @return true if all tunable values in the context object are valid.
	 */
	boolean validateAndApplyTunables(Object tunableContext);
}
