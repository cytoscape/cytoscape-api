package org.cytoscape.app.swing;

import org.cytoscape.app.AbstractCyApp;

/**
 * The primary Swing-based app interface for Cytoscape that all
 * Swing-based apps must extend. This extension to
 * AbstractCyApp simply provides access to CySwingAppAdapter,
 * which provides access to Swing specific services.  All other
 * services from CyAppAdapter will still be available.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractCySwingApp extends AbstractCyApp {

	/**
	 * Reference to access Cytoscape functionality -- various managers and 
	 * factories that are normally available as OSGi services.
	 */
	protected final CySwingAppAdapter swingAdapter;

	/**
	 * The constructor that all apps must call using "super(adapter);" where
	 * the "adapter" is a {@link CySwingAppAdapter} reference provided as an
	 * argument to the constructor. Cytoscape's app loader will execute
	 * the constructor and provide the proper CySwingAppAdapter reference.
	 * <br/>
	 * 
	 * <blockquote><pre> 
	 * public class MySwingApp extends AbstractCySwingApp {
	 *    public MySwingApp(CySwingAppAdapter adapter) {
	 *       super(adapter);
	 *       // app code here
	 *    }
	 * }
	 * </pre></blockquote>
	 * @param adapter a {@link CySwingAppAdapter} reference provided as an
	 * argument to the constructor.
	 */
	public AbstractCySwingApp(final CySwingAppAdapter swingAdapter) {
		super(swingAdapter);
		this.swingAdapter = swingAdapter;
	}
}
