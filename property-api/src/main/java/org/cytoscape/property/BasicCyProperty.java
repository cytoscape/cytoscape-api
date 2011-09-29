package org.cytoscape.property;


import java.util.Properties;


/**
 * A simple implementation of CyProperty&lt;Properties&gt; suitable for 
 * general purpose use. 
 */
public final class BasicCyProperty implements CyProperty<Properties> {
	
	/**
	 * Core Cytoscape Property (Cytoscape System Property)
	 */
	public static final String CORE_PROPRERTY_NAME = "cytoscape3.props";
	
	private final Properties properties;
	private final CyProperty.SavePolicy savePolicy;

	/**
	 * Properties is a non-null Properties object that this CyProperty object
	 * should encapsulate.
	 * @param properties The non-null Properties object this CyProperty object
	 * should encapsulate.  Throws NullPointerException if Properties is null.
	 */
	public BasicCyProperty(final Properties properties, final CyProperty.SavePolicy savePolicy) {
		if (properties == null)
			throw new NullPointerException("\"properties\" parameter is null!");
		if (savePolicy == null)
			throw new NullPointerException("\"savePolicy\" parameter is null!");

		this.properties = properties;
		this.savePolicy = savePolicy;
	}

	/**
	 * {@inheritDoc} 
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * {@inheritDoc} 
	 */
	public CyProperty.SavePolicy getSavePolicy() {
		return savePolicy;
	}
}
