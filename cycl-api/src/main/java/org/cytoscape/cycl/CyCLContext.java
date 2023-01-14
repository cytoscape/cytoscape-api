package org.cytoscape.cycl;

/**
 * An interface to the OpenCL Context object.  This provides pointers to the actual
 * context object and the OpenCL queue.  A new {@code CyCLContext} can be created
 * by calling {@code CyCLFactory.createContext()}
 */
public interface CyCLContext 
{

  /**
   * Return the memory pointer to the context
   *
   * @return memory pointer to context
   */
  public long getContext();
  
  /**
   * Return the memory pointer to the queue
   *
   * @return memory pointer to queue
   */
  public long getQueue();
}
