package org.cytoscape.view.presentation;

import java.awt.Image;
import java.util.Collection;
import java.util.Map;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.annotations.Annotation;



/**
 * BackgroudColor, ScaleFactor, Center X and Center Y are all Network VPs.
 * 
 * Nested networks are not supported.
 * Annotations are not supported.
 * 
 */
public interface ThumbnailFactory {
	
	/**
	 * The height of the image, default: 100
	 */
	public static final String HEIGHT = "height";
	
	/**
	 * The width of the returned image, default: 100
	 */
	public static final String WIDTH = "width";
	
	/**
	 * <p> If true the network will be automatically centered inside the image bounds.</p>
	 * <p> If false then the network Visual Properties NETWORK_SCALE_FACTOR, 
	 * NETWORK_CENTER_X_LOCATION, NETWORK_CENTER_Y_LOCATION will be used to position and scale
	 * the image. The origin for the image coordinates is at the center of the image.</p>
	 * Default: true.
	 */
	public static final String FIT_CONTENT = "fitContent";

	
	Image getThumbnail(CyNetworkView networkView, Collection<Annotation> annotations, Map<String,Object> properties);
	
	default Image getThumbnail(CyNetworkView networkView, Map<String,Object> properties) {
		return getThumbnail(networkView, null, properties);
	}

}
