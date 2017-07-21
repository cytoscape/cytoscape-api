package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
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


import java.awt.Component;
import java.awt.FileDialog;
import java.io.File;
import java.util.Collection;


/**
 * Provides a platform-dependent way to open files. Mainly
 * because Mac would prefer that you use java.awt.FileDialog
 * instead of the Swing FileChooser.
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule swing-util-api
 */
public interface FileUtil {
	/** The Cytoscape property that stores the last save/load directory. */
	@Deprecated
	final String LAST_DIRECTORY = "directory.last";

	/**
	 * Equal to FileDialog.LOAD.
	 */
	int LOAD = FileDialog.LOAD;

	/**
	 * Equal to FileDialog.SAVE.
	 */
	int SAVE = FileDialog.SAVE;

	/**
	 * Equal to FileDialog.LOAD + FileDialog.SAVE.
	 */
	int CUSTOM = LOAD + SAVE;

	/**
	 * A string that defines a simplified java regular expression for a URL.
	 * This may need to be updated to be more precise.
	 */
	String urlPattern = "^(jar\\:)?(\\w+\\:\\/+\\S+)(\\!\\/\\S*)?$";

	/**
	 * Returns a File object, this method should be used instead
	 * of rolling your own JFileChooser.
	 *
	 * @return the location of the selected file
	 * @param parent the parent of the JFileChooser or FileDialog
	 * @param title the title of the dialog box
	 * @param loadSaveCustom a flag for the type of file dialog
	 * @param filters a non-empty collection of file filters 
	 */
	File getFile(Component parent, String title, int loadSaveCustom,
		     Collection<FileChooserFilter> filters);

	/**
	 * Returns a File object, this method should be used instead
	 * of rolling your own JFileChooser.
	 *
	 * @return the location of the selected file
	 * @param parent the parent of the JFileChooser or FileDialog
	 * @param title the title of the dialog box
	 * @param loadSaveCustom a flag for the type of file dialog
	 * @param startDir an alternate start dir, if null the default
	 *                  Cytoscape MUD will be used
	 * @param customApproveText if this is a custom dialog, then
	 *                            custom text should be on the approve
	 *                            button.
	 * @param filters a non-empty collection of file filters 
	 */
	File getFile(Component parent, String title, int loadSaveCustom, String startDir,
		     String customApproveText, Collection<FileChooserFilter> filters);

	/**
	 * Returns an array of File objects, this method should be used instead
	 * of rolling your own JFileChooser.
	 * @return the location of the selected file
	 * @param parent the parent of the JFileChooser or FileDialog
	 * @param title the title of the dialog box
	 * @param loadSaveCustom a flag for the type of file dialog
	 * @param filters a non-empty collection of file filters 
	 */
	File[] getFiles(Component parent, String title, int loadSaveCustom,
			Collection<FileChooserFilter> filters);
  
	/**
	 * Returns a list of File objects, this method should be used instead
	 * of rolling your own JFileChooser.
	 *
	 * @return and array of selected files, or null if none are selected
	 * @param parent the parent of the JFileChooser or FileDialog
	 * @param title the title of the dialog box
	 * @param loadSaveCustom a flag for the type of file dialog
	 * @param startDir an alternate start dir, if null the default
	 *                  Cytoscape MUD will be used
	 * @param customApproveText if this is a custom dialog, then
	 *                            custom text should be on the approve
	 *                            button.
	 * @param filters a non-empty collection of file filters 
	 */
	File[] getFiles(Component parent, String title, int loadSaveCustom,
			String startDir, String customApproveText,
			Collection<FileChooserFilter> filters);
	
	/**
	 * Returns a list of File objects, this method should be used instead
	 * of rolling your own JFileChooser.
	 *
	 * @return and array of selected files, or null if none are selected
	 * @param parent the parent of the JFileChooser or FileDialog
	 * @param title the title of the dialog box
	 * @param loadSaveCustom a flag for the type of file dialog
	 * @param startDir an alternate start dir, if null the default
	 *                  cytoscape MUD will be used
	 * @param customApproveText if this is a custom dialog, then
	 *                            custom text should be on the approve
	 *                            button.
	 * @param multiselect Enable selection of multiple files (Macs are
	 *                    still limited to a single file because we use
	 *                    FileDialog there -- is this fixed in Java 1.5?)
	 * @param filters a non-empty collection of file filters 
	 */
	File[] getFiles(Component parent, String title, int loadSaveCustom,
			String startDir, String customApproveText, boolean multiselect,
			Collection<FileChooserFilter> filters);
	
	
	/**
	 * Returns a File object representing a folder, this method should be used instead
	 * of rolling your own JFileChooser.
	 *
	 * @return the location of the selected folder
	 * @param parent the parent of the JFileChooser or FileDialog
	 * @param title the title of the dialog box
	 * @param startDir an alternate start dir, if null the default Cytoscape MUD will be used
	 */
	File getFolder(Component parent, String title, String startDir);
}
