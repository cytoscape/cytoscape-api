package org.cytoscape.cycl;

import java.util.List;
import java.util.Properties;

import org.cytoscape.application.CyApplicationConfiguration;
import org.cytoscape.property.CyProperty;


/**
 * CyCLFactory is the service that provides access to the OpenCL interface in Cytoscape.
 * Access CyCLFactory through the service registrar:
 *    CyCLFactory cycl = serviceRegistrar.getService(CyCLFactory.class);
 * Once you have the CyCLFactory, callers <b>must</b> check to make sure
 * OpenCL is available on this computer by calling isInitialized().  If 
 * isInitialized() returns false, then no OpenCL is available and all
 * other calls will fail.
 */
public interface CyCLFactory {
  /**
   * Check to see if OpenCL has been initialized.  If this method
   * returns false, there is no OpenCL context on this platform.
   *
   * @return true if OpenCL is availalbe, false otherwise
   */
  public boolean isInitialized();

  /**
   * Return the best (or preferred) device for this computer
   *
   * @return list of all OpenCL devices
   */
  public CyCLDevice getDevice();

  /**
   * Return a list of all of the devices available on this computer
   *
   * @return list of all OpenCL devices
   */
  public List<CyCLDevice> getDevices();

  /**
   * Make a particular device the preferred device.  This avoids the internal benchmark step and can significantly
   * improve initialization.
   *
   * @param name the name of the device to be the preferred device
   */
  public void makePreferred(String name);

  /**
   * Create a new context for a device
   *
   * @param device the devide to create the context for
   * @return a new context for this device
   */
  public CyCLContext createContext(CyCLDevice device);
}

