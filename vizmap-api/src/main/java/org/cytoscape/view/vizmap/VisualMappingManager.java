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

package org.cytoscape.view.vizmap;

import java.util.Set;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.vizmap.events.VisualStyleAboutToBeRemovedEvent;
import org.cytoscape.view.vizmap.events.VisualStyleAddedEvent;

/**
 * Manager for {@linkplain VisualStyle}s. This object manages mapping from view
 * model to VisualStyle. User objects can access all VisualStyles and
 * VisualMappingFunctions through this class.
 * 
 * <p>
 * Add/Remove operations will be done through events. For more information, read
 * JavaDoc for {@linkplain VisualStyleAddedEvent} and
 * {@linkplain VisualStyleAboutToBeRemovedEvent}.
 * </p>
 * 
 */
public interface VisualMappingManager {

	/**
	 * Set a {@linkplain VisualStyle} to the target network view model.
	 * 
	 * @param visualStyle Visual Style to be set.
	 * @param networkViewModel The target network view model.
	 */
	void setVisualStyle(final VisualStyle visualStyle,
			final CyNetworkView networkViewModel);

	/**
	 * Returns the {@linkplain VisualStyle} associated with the target network
	 * view model.
	 * 
	 * @param networkViewModel
	 *            Target network view
	 * 
	 * @return VisualStyle associated with the network view model. This is
	 *         always non-null value. If there is no mapping from given view
	 *         model to a style, then default style will be used.
	 */
	VisualStyle getVisualStyle(final CyNetworkView networkViewModel);

	/**
	 * Returns all available {@linkplain VisualStyle}s managed by this object.
	 * 
	 * @return Set of all registered VisualStyles.
	 * 
	 */
	Set<VisualStyle> getAllVisualStyles();

	/**
	 * Add a new {@link VisualStyle} to this manager.
	 * 
	 * @param visualStyle
	 *            new visual style to be registered.
	 */
	void addVisualStyle(final VisualStyle visualStyle);

	/**
	 * Remove a VisualStyle from this manager.
	 * 
	 * @param visualStyle
	 *            VisualStyle to be removed.
	 */
	void removeVisualStyle(VisualStyle visualStyle);

	/**
	 * Return default {@link VisualStyle}.  This is just an empty visual style.
	 * 
	 * @return default Visual Style.
	 */
	VisualStyle getDefaultVisualStyle();
	
	Set<VisualLexicon> getAllVisualLexicon();

}
