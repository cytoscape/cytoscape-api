package org.cytoscape.cycl;

import java.nio.*;

/***
 * Provides functionality associated with an OpenCL memory object.
 * 
 * @author Dimitry Tegunov
 *
 */
public interface CyCLBuffer 
{
  /***
   * Returns the number of bytes associated with the CyCLBuffer's element type.
   * 
   * @return sizeof(type)
   */
  public int elementSize();
  
  /***
   * Returns the overall buffer size in bytes.
   * 
   * @return sizeof(type) * elements
   */
  public int sizeInBytes();

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   * @param length Number of elements to be copied
   * @param offset Offset in bytes from the device memory's start
   */
  public void setFromHost(byte[] data, int length, int offset);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   * @param length Number of elements to be copied
   * @param offset Offset in bytes from the device memory's start
   */
  public void setFromHost(short[] data, int length, int offset);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   * @param length Number of elements to be copied
   * @param offset Offset in bytes from the device memory's start
   */
  public void setFromHost(int[] data, int length, int offset);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   * @param length Number of elements to be copied
   * @param offset Offset in bytes from the device memory's start
   */
  public void setFromHost(long[] data, int length, int offset);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   * @param length Number of elements to be copied
   * @param offset Offset in bytes from the device memory's start
   */
  public void setFromHost(float[] data, int length, int offset);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   * @param length Number of elements to be copied
   * @param offset Offset in bytes from the device memory's start
   */
  public void setFromHost(double[] data, int length, int offset);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   */
  public void setFromHost(byte[] data);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   */
  public void setFromHost(short[] data);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   */
  public void setFromHost(int[] data);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   */
  public void setFromHost(long[] data);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   */
  public void setFromHost(float[] data);

  /***
   * Copies data from host to device memory.
   * 
   * @param data Data to be copied
   */
  public void setFromHost(double[] data);
  
  /***
   * Copies data from another device buffer to this one.
   * 
   * @param src Device buffer with the source data
   * @param bytes Amount of bytes to be copied
   * @param offsetSrc Offset in bytes from the start of the source buffer
   * @param offsetDst Offset in bytes from the start of the destination buffer
   */
  public void setFromDevice(CyCLBuffer src, long bytes, long offsetSrc, long offsetDst);

  /***
   * Copies data from another device buffer to this one.
   * 
   * @param src Device buffer with the source data
   */
  public void setFromDevice(CyCLBuffer src);

  public void getFromDevice(long offset);
  
  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   * @param length Number of elements
   * @param offset Offset in bytes from the start of the device buffer
   */
  public void getFromDevice(byte[] data, int length, int offset);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   * @param length Number of elements
   * @param offset Offset in bytes from the start of the device buffer
   */
  public void getFromDevice(short[] data, int length, int offset);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   * @param length Number of elements
   * @param offset Offset in bytes from the start of the device buffer
   */
  public void getFromDevice(int[] data, int length, int offset);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   * @param length Number of elements
   * @param offset Offset in bytes from the start of the device buffer
   */
  public void getFromDevice(long[] data, int length, int offset);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   * @param length Number of elements
   * @param offset Offset in bytes from the start of the device buffer
   */
  public void getFromDevice(float[] data, int length, int offset);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   * @param length Number of elements
   * @param offset Offset in bytes from the start of the device buffer
   */
  public void getFromDevice(double[] data, int length, int offset);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   */
  public void getFromDevice(byte[] data);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   */
  public void getFromDevice(short[] data);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   */
  public void getFromDevice(int[] data);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   */
  public void getFromDevice(long[] data);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   */
  public void getFromDevice(float[] data);

  /***
   * Copies data from device to host memory.
   * 
   * @param data Array that the data will be copied to
   */
  public void getFromDevice(double[] data);

  /***
   * Gets the underlying memory object.
   * 
   * @return LWJGL memory object
   */
  public long getMemObject();
  
  /***
   * Gets the underlying buffer.
   * 
   * @return ByteBuffer
   */
  public ByteBuffer getBuffer();
  
  /***
   * Frees all device memory associated with the buffer.
   * CyCLBuffer cannot be used anymore once this method has been executed.
   */
  public void free();
  
  @Override
  public String toString();
}
