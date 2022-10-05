package org.cytoscape.cycl;

public interface CyCLProgram
{

  /**
   * Return the internal memory pointer to this program
   *
   * @return program pointer
   */
  public long getProgram();

  /**
   * Return the named kernel that's part of this program
   *
   * @param name kernel name
   * @return the kernel
   */
  public CyCLKernel getKernel(String name);

  /**
   * Return the build information string identified by the param_name
   *
   * @param device the device this was built on
   * @param param_name the parameter value to return
   * @return the requested information as a string
   */
  public String getBuildInfoString(CyCLDevice device, int param_name);

  /**
   * Return the build information string identified by the param_name
   *
   * @param cl_device_id the internal pointer to the device this was built on
   * @param param_name the parameter value to return
   * @return the requested information as a string
   */
  public String getProgramBuildInfoStringASCII(long cl_device_id, int param_name);

  /**
   * Return the build information value identified by the param_name
   *
   * @param cl_device_id the internal pointer to the device this was built on
   * @param param_name the parameter value to return
   * @return the requested information as a string
   */
  public int getProgramBuildInfoInt(long cl_device_id, int param_name);

}
