package org.cytoscape.util.swing;

import java.io.File;
import java.util.Arrays;
import javax.swing.filechooser.FileFilter;

/**
 * Used to hold extension(s) of acceptable file types and a short description of
 * them.
 * 
 * @CyAPI.Final.Class
 */
public final class FileChooserFilter extends FileFilter {

	private final String description;
	private final String[] extensions;

	/**
	 * Construct this FileChooserFilter using one acceptable file type
	 * extension.
	 * 
	 * @param description
	 *            a short description of the acceptable file type
	 * @param extension
	 *            the file extension of the acceptable file type
	 */
	public FileChooserFilter(final String description, final String extension) {
		this(description, new String[] {extension});
	}

	/**
	 * Construct this FileChooserFilter using multiple acceptable file type
	 * extensions.
	 * 
	 * @param description
	 *            a short description of the acceptable file type(s)
	 * @param extensions
	 *            the file extensions of the acceptable file type(s)
	 */
	public FileChooserFilter(final String description, final String[] extensions) {
		super();
		this.description = description;
		this.extensions = extensions;
	}

	/**
	 * Accept or not the file from JFileChooser.
	 * 
	 * @param file
	 *            the file to be tested
	 */
	@Override
	public boolean accept(final File file) {
		if (file.isDirectory())
			return true;

		String fileName = file.getName().toLowerCase();

		if (extensions != null) {
			for (int i = 0; i < extensions.length; i++) {
				if (fileName.endsWith(extensions[i]))
					return true;
			}

			for (int i = 0; i < extensions.length; i++) {
				if (fileName.contains(extensions[i]))
					return true;
			}
		} else
			throw new IllegalArgumentException("No fileType specified");

		return false;
	}

	/**
	 * Returns the short description of the acceptable file type.
	 * 
	 * @return the short description of the acceptable file type.
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Returns an array of the extensions associated with this
	 * FileChooserFilter.
	 * 
	 * @return an array of the extensions associated with this
	 *         FileChooserFilter.
	 */
	public String[] getExtensions() {
		return extensions;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FileChooserFilter))
			return false;

		final FileChooserFilter otherFilter = (FileChooserFilter) other;
		if (!otherFilter.description.equals(description))
			return false;

		if (otherFilter.extensions.length != extensions.length)
			return false;

		Arrays.sort(otherFilter.extensions);
		Arrays.sort(extensions);

		for (int i = 0; i < extensions.length; ++i) {
			if (!extensions[i].equals(otherFilter.extensions[i]))
				return false;
		}

		return true;
	}
}