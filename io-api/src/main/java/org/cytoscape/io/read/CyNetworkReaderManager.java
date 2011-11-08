/*
 Copyright (c) 2006, 2007, 2010, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.io.read;


import java.io.InputStream;
import java.net.URI;


/**
 * An object that registers all InputStreamReaderFactory singletons,
 * processes specified input to determine the appropriate factory to
 * use and then returns an instance of the correct {@link org.cytoscape.io.read.CyNetworkReader} 
 * for the input.
 * @CyAPI.Api.Interface
 */
public interface CyNetworkReaderManager {
	/**
	 * Given a URI this method will attempt to find a InputStreamReaderFactory
	 * that can read the URI, will set the InputStream for the factory and
	 * will return the reader task.
	 * @param uri The URI we're attempting to read. 
	 * @param inputName The name of the input. 
	 * @return A reader than can read the specified URI. Will return null if
	 * no reader can be found.
	 */
	CyNetworkReader getReader(URI uri, String inputName); 

	/**
	 * Given an InputStream this method will attempt to find a InputStreamReaderFactory
	 * that can read the stream, will set the InputStream for the factory and
	 * will return the reader task.
	 * @param stream The input stream we're attempting to read. 
	 * @param inputName The name of the input. 
	 * @return A reader that can read the specified stream. Will return null if
	 * no reader can be found.
	 */
	CyNetworkReader getReader(InputStream stream, String inputName); 
}
