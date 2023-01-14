package org.cytoscape.cycl;

import java.nio.*;

import java.net.URL;
import java.util.HashMap;

/***
 * Represents functionality associated with a single OpenCL device.
 * All operations, e. g. memory allocation or kernel execution, should be
 * performed through the appropriate CyCLDevice object.
 * All devices present in the system are initialized as a CyCLDevice object
 * when Cytoscape starts. Manual creation should not be needed.
 *
 * @author Dimitry Tegunov
 *
 */
public interface CyCLDevice
{
  /**
   * The OpenCL list of device types.
   */
  public enum DeviceTypes
  {
    CPU,
    GPU,
    Accelerator
  }

  /**
   * Run a simple benchmark on this OpenCL Device and
   * return the runtime
   *
   * @return benchmark runtime
   */
  public double performBenchmark(boolean useOffsets);

  /***
   * Gets the underlying LWJGL device ID.
   *
   * @return LWJGL device ID
   */
  public long getDevice();

  /***
   * Gets the underlying LWJGL platform ID.
   *
   * @return LWJGL platform ID
   */
  public CyCLPlatform getPlatform();

  /***
   * Suggests an optimal block (work item) size for the given global item count.
   *
   * @param n Global item count
   * @return Optimal block size for this architecture
   */
  public long getBestBlockSize(long n);

  /***
   * Return the best warp size for this device
   *
   * @return best warp size for the device
   */
  public long getBestWarpSize();

  /***
   * Return the best block size for this device
   *
   * @return best block size for the device
   */
  public long getBestBlockSize();

  /***
   * Return the maximum work group size for this device
   *
   * @return best block size for the device
   */
  public long getMaxWorkGroupSize();

  /***
   * Determines if a program with the given name has already been compiled and stored.
   *
   * @param name Program name
   * @return True if the program has been compiled, false otherwise
   */
  public Boolean hasProgram(String name);

  /***
   * Attempts to find a pre-compiled program with the given name.
   *
   * @param name Program name
   * @return The program if it is found, null otherwise
   */
  public CyCLProgram getProgram(String name);

  /***
   * Compiles a program and its kernels, and stores it for further use.
   *
   * @param name Program name
   * @param programSources Strings containing the individual files comprising the program
   * @param kernelNames An array of kernel names, as used in the program
   * @param defines Dictionary of definitions to be injected as "#define key value"; can be null
   * @return The program if it has been successfully compiled
   */
  public CyCLProgram addProgram(String name, String[] programSources, String[] kernelNames, HashMap<String, String> defines, boolean silentCompilation);

  /***
   * Compiles a program and its kernels, and stores it for further use.
   *
   * @param name Program name
   * @param programSource A sring containing the program source
   * @param kernelNames An array of kernel names, as used in the program
   * @param defines Dictionary of definitions to be injected as "#define key value"; can be null
   * @return The program if it has been successfully compiled
   */
  public CyCLProgram addProgram(String name, String programSource, String[] kernelNames, HashMap<String, String> defines, boolean silentCompilation);

  /***
   * Compiles a program and its kernels, and stores it for further use.
   *
   * @param name Program name
   * @param resourcePath Path to the resource with the program's text
   * @param kernelNames An array of kernel names, as used in the program
   * @param defines Dictionary of definitions to be injected as "#define key value"; can be null
   * @return The program if it has been successfully compiled
   */
  public CyCLProgram addProgram(String name, URL resourcePath, String[] kernelNames, HashMap<String, String> defines, boolean silentCompilation);

  /***
   * Compiles a program and its kernels, and stores it, possibly replacing (and destroying) an old instance.
   *
   * @param name Program name
   * @param resourcePath Path to the resource with the program's text
   * @param kernelNames An array of kernel names, as used in the program
   * @param defines Dictionary of definitions to be injected as "#define key value"; can be null
   * @return The program if it has been successfully compiled
   */
  public CyCLProgram forceAddProgram(String name, URL resourcePath, String[] kernelNames, HashMap<String, String> defines, boolean silentCompilation);

  /***
   * Gets a dictionary of device-specific values that will be defined in the compiled program
   *
   * @return Dictionary of key-value pairs, as in "#define key value"
   */
  public HashMap<String, String> getDeviceSpecificDefines();

  /***
   * Pauses the calling thread until all items in the device's command queue have been finished.
   */
  public void finishQueue();

  /***
   * Allocates memory on this device without filling it with any data.
   * This assumes it's going to be a read buffer!!
   * @param type Buffer element type
   * @param elements Number of elements
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(Class<?> type, int elements);

  /***
   * Allocates memory on this device without filling it with any data.
   * @param type Buffer element type
   * @param elements Number of elements
   * @param bits the CL_MEM bits that describe the buffer type
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(Class<?> type, int elements, int bits);

  /***
   * Allocate a buffer that will be sent to the device and fills
   * it with host data.
   * @param data Byte array with data to be copied
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createWriteBuffer(byte[] data);

  /***
   * Allocate a buffer that will be read from the device.
   * @param data Byte array the data will be copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createReadBuffer(byte[] data);

  /***
   * Allocate a buffer that will be both read and written to/from the device.
   * @param data Byte array the data will be filled with and copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(byte[] data);

  /***
   * Allocates memory on this device and fills it with host data.
   * @param data Byte array with data to be copied
   * @param bits the CL_MEM bits that describe the buffer type
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(byte[] data, int bits);

  /***
   * Allocate a buffer that will be sent to the device and fills
   * it with host data.
   * @param data Int16 array with data to be copied
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createWriteBuffer(short[] data);

  /***
   * Allocate a buffer that will be read from the device.
   * @param data Int16 array the data will be copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createReadBuffer(short[] data);

  /***
   * Allocate a buffer that will be both read and written to/from the device.
   * @param data Int16 array the data will be filled with and copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(short[] data);

  /***
   * Allocates memory on this device and fills it with host data.
   * @param data Int16 array with data to be copied
   * @param bits the CL_MEM bits that describe the buffer type
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(short[] data, int bits);

  /***
   * Allocate a buffer that will be sent to the device and fills
   * it with host data.
   * @param data Int32 array with data to be copied
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createWriteBuffer(int[] data);

  /***
   * Allocate a buffer that will be read from the device.
   * @param data Int32 array the data will be copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createReadBuffer(int[] data);

  /***
   * Allocate a buffer that will be both read and written to/from the device.
   * @param data Int32 array the data will be filled with and copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(int[] data);
 
  /***
   * Allocates memory on this device and fills it with host data.
   * @param data Int32 array with data to be copied
   * @param bits the CL_MEM bits that describe the buffer type
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(int[] data, int bits);

  /***
   * Allocate a buffer that will be sent to the device and fills
   * it with host data.
   * @param data Int64 array with data to be copied
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createWriteBuffer(long[] data);

  /***
   * Allocate a buffer that will be read from the device.
   * @param data Int64 array the data will be copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createReadBuffer(long[] data);

  /***
   * Allocate a buffer that will be both read and written to/from the device.
   * @param data Int64 array the data will be filled with and copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(long[] data);

  /***
   * Allocates memory on this device and fills it with host data.
   * @param data Int64 array with data to be copied
   * @param bits the CL_MEM bits that describe the buffer type
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(long[] data, int bits);

  /***
   * Allocate a buffer that will be sent to the device and fills
   * it with host data.
   * @param data Float32 array with data to be copied
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createWriteBuffer(float[] data);

  /***
   * Allocate a buffer that will be read from the device.
   * @param data Float32 array the data will be copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createReadBuffer(float[] data);

  /***
   * Allocate a buffer that will be both read and written to/from the device.
   * @param data Float32 array the data will be filled with and copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(float[] data);

  /***
   * Allocates memory on this device and fills it with host data.
   * @param data Float32 array with data to be copied
   * @param bits the CL_MEM bits that describe the buffer type
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(float[] data, int bits);

  /***
   * Allocate a buffer that will be sent to the device and fills
   * it with host data.
   * @param data Float64 array with data to be copied
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createWriteBuffer(double[] data);

  /***
   * Allocate a buffer that will be read from the device.
   * @param data Float64 array the data will be copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createReadBuffer(double[] data);

  /***
   * Allocate a buffer that will be both read and written to/from the device.
   * @param data Float64 array the data will be filled with and copied into
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(double[] data);

  /***
   * Allocates memory on this device and fills it with host data.
   * @param data Float64 array with data to be copied
   * @param bits the CL_MEM bits that describe the buffer type
   * @return CyCLBuffer object with a pointer to the allocated memory
   */
  public CyCLBuffer createBuffer(double[] data, int bits);

  /**
   * Create a local size object that can be used to allocate local memory
   *
   * @param size the size in bytes of the local object
   * @return the CyCLLocalSize object
   */
  public CyCLLocalSize createLocalSize(long size);

  /*
   * Various device information strings
   */
  /**
   * Return the name of this device
   */
  public String getName();

  /**
   * Return the vendor of this device
   */
  public String getVendor();

  /**
   * Return the version of this device
   */
  public String getVersion();

  /**
   * Return the type of this device
   *
   * @return the type of the device
   */
  public DeviceTypes getType();
}
