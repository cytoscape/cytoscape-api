package org.cytoscape.app;


/**
 * The primary app interface for Cytoscape that all
 * apps must extend. App developers will have access 
 * to all core Cytoscape 3.X services, but are not required to
 * know about or use Maven or OSGi. This interface does not
 * provide access to Swing or GUI related services, for 
 * that use AbstractCySwingApp found in the org.cytoscape.app.swing
 * package.
 * There are limitations on which packages may
 * be included in the app jar based on those already loaded in the classpath.
 * To load alternative versions of the same library used by other apps or 
 * Cytoscape itself, it will be necessary to write your app using OSGi.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractCyApp {

	/**
	 * Reference to access Cytoscape functionality -- various managers and 
	 * factories that are normally available as OSGi services.
	 */
	protected CyAppAdapter adapter;

	// so no one calls this constructor
	private AbstractCyApp() {
		throw new NullPointerException("no adapter provided!");
	}

	/**
	 * The constructor that all apps must call using "super(adapter);" where
	 * the "adapter" is a {@link CyAppAdapter} reference provided as an
	 * argument to the constructor. Cytoscape's app loader will execute
	 * the constructor and provide the proper CyAppAdapter reference.
	 * <br/>
	 * 
	 * <blockquote><pre> 
	 * public class MyApp extends AbstractCyApp {
	 *    public MyApp(CyAppAdapter adapter) {
	 *       super(adapter);
	 *       // app code here
	 *    }
	 * }
	 * </pre></blockquote>
	 * @param adapter a {@link CyAppAdapter} reference provided as an
	 * argument to the constructor.
	 */
	public AbstractCyApp(final CyAppAdapter adapter) {
		if ( adapter == null )
			throw new NullPointerException("null adapter");
		this.adapter = adapter;
	}
}
