package org.cytoscape.view.presentation;

import java.awt.Image;
import java.util.Collection;
import java.util.HashMap;
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
public interface NetworkImageFactory {
	
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
	
	
	/**
	 * Creates a network image thumbnail with the given properties.
	 * <br><br>
	 * Example:
	 * <pre>
	 * Map<String,Object> properties = new HashMap<>();
	 * properties.put(WIDTH,  width);
	 * properties.put(HEIGHT, height);
	 * properties.put(FIT_CONTENT, true);
	 * Image image = thumbnailFactory.createThumbnail(networkView, null, properties);
	 * </pre>
	 * 
	 * @param annotations A list of Annotations that will be included in the image, may be null.
	 */
	Image createImage(CyNetworkView networkView, Collection<Annotation> annotations, Map<String,Object> properties);
	
	
	/**
	 * Creates a network image thumbnail with the given width and height. The network will
	 * be centered and scaled to fit within the image bounds.
	 * <br><br>
	 * Equivalent to:
	 * <pre>
	 * Map<String,Object> properties = new HashMap<>();
	 * properties.put(WIDTH,  width);
	 * properties.put(HEIGHT, height);
	 * properties.put(FIT_CONTENT, true);
	 * Image image = thumbnailFactory.createThumbnail(networkView, null, properties);
	 * </pre>
	 */
	default Image createImage(CyNetworkView networkView, int width, int height) {
		Map<String,Object> properties = new HashMap<>();
		properties.put(WIDTH,  width);
		properties.put(HEIGHT, height);
		properties.put(FIT_CONTENT, true);
		return createImage(networkView, null, properties);
	}

}
