package org.cytoscape.view.vizmap;


/**
 * Factory for {@linkplain VisualStyle}s.  This object will be provided as an OSGi service.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface VisualStyleFactory {
	
	/**
	 * Create a new {@linkplain VisualStyle}.
	 * 
	 * @param title Title of the visual style.  This can be null, but in that case, 
	 * 					default title will be used.
	 * 			Note: This is NOT an identifier of this object, just a title.
	 *
	 * @return New VisualStyle object.
	 */
	VisualStyle createVisualStyle(final String title);
	
	
	/**
	 * Create a copy of given {@linkplain VisualStyle}.
	 *
	 * @param original
	 *            VisualStyle to be copied from.
	 *
	 * @return New VisualStyle copied from the original.
	 * 
	 */
	VisualStyle createVisualStyle(final VisualStyle original);

}
