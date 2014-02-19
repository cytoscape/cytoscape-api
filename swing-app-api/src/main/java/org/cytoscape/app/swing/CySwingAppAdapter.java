package org.cytoscape.app.swing;

/*
 * #%L
 * Cytoscape Swing App API (swing-app-api)
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

import java.util.Properties;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.work.swing.DialogTaskManager;
import org.cytoscape.work.swing.PanelTaskManager;
import org.cytoscape.app.CyAppAdapter;


/**
 * A Swing-specific extension of {@link CyAppAdapter} that
 * serves as a Java-only api providing access to core 
 * Cytoscape functionality.
 * This class will provide access the various Swing-specific
 * Manager and Factory interfaces in addition to those defined in
 * {@link CyAppAdapter}.
 * Through these interfaces developers will have access to most management
 * and creational facilities defined in the Cytoscape API.
 * This is a convenience interface intended make app development
 * as simple as possible.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule swing-app-api
 */
public interface CySwingAppAdapter extends CyAppAdapter {

	//
	// work swing api
	//

	/**
	 * Returns an instance of {@link DialogTaskManager}.
	 * @return an instance of {@link DialogTaskManager}.
	 */
	DialogTaskManager getDialogTaskManager();

	/**
	 * Returns an instance of {@link PanelTaskManager}.
	 * @return an instance of {@link PanelTaskManager}.
	 */
	PanelTaskManager getPanelTaskManager();

	//
	// swing application api
	//

	/**
	 * Returns an instance of {@link CySwingApplication}.
	 * @return an instance of {@link CySwingApplication}.
	 */
	CySwingApplication getCySwingApplication();

}
