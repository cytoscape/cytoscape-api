/*
 Copyright (c) 2006, 2007, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

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
package org.cytoscape.io.write;

import java.io.File;
import java.io.OutputStream;

import org.cytoscape.io.CyFileFilter;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

/**
 * A {@link org.cytoscape.io.write.CyWriterManager} specific to writing 
 * {@link org.cytoscape.view.model.CyNetworkView} objects. 
 * @CyAPI.Api.Interface
 */
public interface CyNetworkViewWriterManager extends CyWriterManager {

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified network to the
	 * specified file of the specified file type.
	 * @param network The {@link org.cytoscape.model.CyNetwork} to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param file The file to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified network to the
	 * specified file of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(CyNetwork network, CyFileFilter filter, File file) throws Exception;

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified network to the
	 * specified output steam of the specified file type.
	 * @param network The {@link org.cytoscape.model.CyNetwork} to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param os The output steam to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified network to the
	 * specified output steam of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(CyNetwork network, CyFileFilter filter, OutputStream os) throws Exception;
	
	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified view to the
	 * specified file of the specified file type. 
	 * @param view The {@link org.cytoscape.view.model.CyNetworkView} to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param file The file to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified view to the
	 * specified file of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(CyNetworkView view, CyFileFilter filter, File file) throws Exception;

	/**
	 * Returns the {@link CyWriter} Task that will attempt to write the specified view to the
	 * specified output steam of the specified file type. 
	 * @param view The {@link org.cytoscape.view.model.CyNetworkView} to be written.
	 * @param filter The {@link org.cytoscape.io.CyFileFilter} that defines the type of file to be written.
	 * @param os The output steam to be written. 
	 * @return The {@link CyWriter} Task that will attempt to write the specified view to the
	 * specified output steam of the specified file type. 
	 * @throws Exception 
	 */
	CyWriter getWriter(CyNetworkView view, CyFileFilter filter, OutputStream os) throws Exception;
}
