package org.cytoscape.application.swing;

import javax.swing.Icon;


/**
 * Service that allows UI presentation to be given for a column namespace.
 * 
 * Register as an OSGi service.
 * 
 * <pre>
 * Properties props = new Properties();
 * props.put(CyColumnPresentation.NAMESPACE, "myNamespace");
 * registerService(bc, new MyColumnPresentation(), CyColumnPresentation.class, props);
 * </pre>
 * 
 * @see org.cytoscape.model.CyColumn
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CyColumnPresentation {
	
	/**
	 * OSGi property, use to provide the namespace to associate this presentation with.
	 */
	public static final String NAMESPACE = "namespace";
	
	/**
	 * Returns an icon associated with the namespace.
	 * @return May return null, but is not recommended.
	 */
	Icon getNamespaceIcon();
	
	/**
	 * Returns a String description of the namespace.
	 * @return May return null, but is not recommended.
	 */
	String getNamespaceDescription();
	
}
