package org.cytoscape.plugin;


/**
 * The primary plugin interface for Cytoscape that all
 * plugins must extend. Plugin developers will have access 
 * to all Cytoscape 3.X services, but are not required to
 * know about or use Maven, OSGi, or Spring.
 * However, there are limitations on which packages may
 * be included in the plugin jar based on those already loaded in the classpath.
 * To load alternative versions of the same library used by other plugins or 
 * Cytoscape itself, it will be necessary to write your plugin using OSGi
 * and Spring.
 */
public abstract class CyPlugin {

	/**
	 * Reference to access Cytoscape functionality -- various managers and 
	 * factories that are normally available as OSGi services.
	 */
	protected CyPluginAdapter adapter;

	// so no one calls this constructor
	private CyPlugin() {
		throw new NullPointerException("no adapter provided!");
	}

	/**
	 * The constructor that all plugins must call using "super(adapter);" where
	 * the "adapter" is a {@link CyPluginAdapter} reference provided as an
	 * argument to the constructor. Cytoscape's plugin loader will execute
	 * the constructor and provide the proper CyPluginAdapter reference.
	 * <br/>
	 * 
	 * <blockquote><pre> 
	 * public class MyPlugin extends CyPlugin {
	 *    public MyPlugin(CyPluginAdapter adapter) {
	 *       super(adapter);
	 *       // plugin code here
	 *    }
	 * }
	 * </pre></blockquote>
	 * @param adapter a {@link CyPluginAdapter} reference provided as an
	 * argument to the constructor.
	 */
	public CyPlugin(final CyPluginAdapter adapter) {
		if ( adapter == null )
			throw new NullPointerException("null adapter");
		this.adapter = adapter;
	}
}
