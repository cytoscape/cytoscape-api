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

import java.awt.Dialog.ModalityType;
import java.awt.Window;

/**
 * 
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-swing-api
 */
public interface TunableUIHelper {

	/**
 	 * Get the parent of the current Tunable dialog.  This may be
 	 * used as the parent of a child dialog to avoid modality
 	 * problems.
 	 *
 	 * @return the parent window
 	 */
	Window getParent();

	/**
 	 * Set the modality of the parent dialog.  By default, the Swing
 	 * Tunable Dialog is APPLICATION_MODAL.  This may not be ideal
 	 * for circumstances where it's valid for the user to be able to
 	 * manipulate the state of the network or table browser while the
 	 * dialog is up.  This method allows tha app writer to override
 	 * the default modality.
 	 *
 	 * @param modality the new modality for the dialog
 	 */
	public void setModality(ModalityType modality);

	/**
	 * Update all of the tunable values.  This results in all of the
	 * update methods of each of the Tunables being called.  The Object
	 * provided is usually the Task itself.
	 * 
	 * @param objectWithTunables the object containing the tunables
	 */
	public void update(Object objectWithTunables);

	/**
	 * Recreate the Tunable UI.  This might be used, for example, when
	 * a @ContainsTunable field is changed in response to a user input.
	 * As with update, the Object provided is usually the Task object
	 * itself.
	 *
	 * @param objectWithTunables the object containing the tunables
	 */
	public void refresh(Object objectWithTunables);
}
