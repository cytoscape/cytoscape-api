package org.cytoscape.property;


import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An abstract implementation of CyProperty&lt;Properties&gt; that attempts to read the specified
 * properties file first from the jar file running this code and then appends any properties
 * found in the local configuration directory to that properties object. The config
 * directory used is {@link CyProperty.DEFAULT_PROPS_CONFIG_DIR}.
 * <br/>
 * This class must be extended so that it will read from the proper jar file.  In general
 * simply implementing a constructor should be sufficient:
 * </br>
 * <pre>
 * 	public class PropsReader extends AbstractConfigDirPropsReader {
 * 		PropsReader(String s, SavePolicy sp) {
 * 			super(s,sp);
 * 		}
 * 	}
 * </pre>
 */
public abstract class AbstractConfigDirPropsReader implements CyProperty<Properties> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractConfigDirPropsReader.class);

	/**
	 * The Properties object created for this class. 
	 */
	protected final Properties props;

	/**
	 * The SavePolicy of this CyProperty.
	 */
	protected final SavePolicy savePolicy;

	/**
	 * Creates a new AbstractConfigDirPropsReader object.
	 * @param propFileName The name of the java.util.Properties file to read. 
	 * @param savePolicy The save policy for this CyProperty.  This value MUST be
	 * either {@link CyProperty.SavePolicy.CONFIG_DIR} or 
	 * {@link CyProperty.SavePolicy.SESSION_FILE_AND_CONFIG_DIR} so that we can 
	 * reasonably expect to find a properties file in the configuration directory.  
	 * If you'd like to use a different SavePolicy, then consider using
	 * {@link SimpleCyProperty} instead.
	 */
	public AbstractConfigDirPropsReader(String propFileName, SavePolicy savePolicy) {
		if ( propFileName == null )
			throw new NullPointerException("propFileName cannot be null");

		if ( savePolicy == null )
			throw new NullPointerException("savePolicy cannot be null");

		if ( savePolicy != CyProperty.SavePolicy.CONFIG_DIR &&
		     savePolicy != CyProperty.SavePolicy.SESSION_FILE_AND_CONFIG_DIR )
			 throw new IllegalArgumentException("Save policy must be either CONFIG_DIR or SESSION_FILE_AND_CONFIG_DIR");
		
		this.savePolicy = savePolicy;
		this.props = new Properties();

		try {
			readDefaultFromJar(propFileName);
			readLocalModifications(propFileName);
		} catch (Exception e) {
			logger.warn("Error reading properties file '" + propFileName + "' - using empty intance.", e);
			props.clear();
		}
	}

	private void readLocalModifications(String propFileName) throws Exception {
		InputStream is = null;
		try {
			final File configDir = new File(System.getProperty("user.home"), CyProperty.DEFAULT_PROPS_CONFIG_DIR); 
	        final File localPropsFile = new File(configDir,propFileName);

			if (localPropsFile.exists()) {
				is = new FileInputStream(localPropsFile);
				props.load(is);
				is.close();
			}
		} finally {
			if (is != null) {
				try { is.close(); } catch (IOException ioe) {}
				is = null;
			}
		}
	}

	private void readDefaultFromJar(String propFileName) throws Exception {
		InputStream is = null;
		try {
			String fileName;
			if ( !propFileName.startsWith("/") )
				fileName = "/" + propFileName;
			else
				fileName = propFileName;
				
			is = getClass().getResourceAsStream(fileName);
			if ( is != null )
				props.load(is);
			else
				logger.warn("couldn't find resource '" + propFileName + "' in jar.");
		} finally {
			if (is != null) {
				try { is.close(); } catch (IOException ioe) {}
				is = null;
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public CyProperty.SavePolicy getSavePolicy() {
		return savePolicy; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Properties getProperties() {
		return props;
	}
}
