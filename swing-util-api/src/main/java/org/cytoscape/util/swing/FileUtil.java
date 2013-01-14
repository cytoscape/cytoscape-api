/*
  File: FileUtil.java

  Copyright (c) 2006, 2011, The Cytoscape Consortium (www.cytoscape.org)

  This library is free software; you can redistribute it and/or modify it
  under the terms of the GNU Lesser General Public License as published
  by the Free Software Foundation; either version 2.1 of the License, or
  any later version.

  This library is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
  MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
  documentation provided hereunder is on an "as is" basis, and the
  Institute for Systems Biology and the Whitehead Institute
  have no obligations to provide maintenance, support,
  updates, enhancements or modifications.  In no event shall the
  Institute for Systems Biology and the Whitehead Institute
  be liable to any party for direct, indirect, special,
  incidental or consequential damages, including lost profits, arising
  out of the use of this software and its documentation, even if the
  Institute for Systems Biology and the Whitehead Institute
  have been advised of the possibility of such damage.  See
  the GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this library; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.util.swing;


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
}
