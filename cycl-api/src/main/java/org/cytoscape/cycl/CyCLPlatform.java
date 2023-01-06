package org.cytoscape.cycl;

public interface CyCLPlatform {

  /**
   * Return the name of this platform
   *
   * @return platform name
   */
  public String getName();

  /**
   * Return the capabilities of the platform
   *
   * @return platform capabilities
   */
  // public CLCapabilities getCapabilities();

  /**
   * Return the context properties for this platform as a 
   * pointer buffer
   *
   * @return context properties
   */
  // public PointerBuffer getContextProps();

  /**
   * Return the devices that are part of this plaform by type
   *
   * @param type the device type to filter the list by
   * @return an array of devices (as internal memory pointers)
   */
  public long[] getDevices(int type);

  /**
   * Return information about the platform as a String
   *
   * @param param_name the parameter to retrieve
   * @return the retrieved value
   */
  public String getPlatformInfoStringASCII(int param_name);

  /**
   * Return information about the platform as a UTF8 encoded String
   *
   * @param param_name the parameter to retrieve
   * @return the retrieved value
   */
  public String getPlatformInfoStringUTF8(int param_name);

}
