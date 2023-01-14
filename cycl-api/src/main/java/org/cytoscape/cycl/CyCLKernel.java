package org.cytoscape.cycl;

/**
 * This provides an interface to OpenCL Kernels
 */
public interface CyCLKernel
{

  /**
   * Return the internal pointer to this kernel
   *
   * @return the internal pointer to the kernel
   */
  public long getKernel();


  /**
   * Execute this kernel
   *
   * @param dimsGlobal The dimension of the global variables
   * @param dimsLocal The dimension of the local variables
   * @param args The args for the kernel
   */
  public void execute(long[] dimsGlobal, long[] dimsLocal, Object... args);

  /**
   * Execute this kernel with offsets
   *
   * @param dimsGlobal The dimension of the global variables
   * @param dimsLocal The dimension of the local variables
   * @param globalOffset The offsets for the global variables
   * @param args The args for the kernel
   */
  public void executeWithOffset(long[] dimsGlobal, long[] dimsLocal, long globalOffset[], Object... args);

  /**
   * Return kernel information as an int
   *
   * @param param_name the parameter to return
   * @return the parameter value
   */
  public int getKernelInfoInt(int param_name);

  /**
   * Return kernel information as a memory pointer
   *
   * @param param_name the parameter to return
   * @return the parameter value
   */
  public long getKernelInfoPointer(int param_name);

  /**
   * Return kernel information as a string
   *
   * @param param_name the parameter to return
   * @return the parameter value
   */
  public String getKernelInfoStringUTF8(int param_name);

}
