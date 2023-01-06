package org.cytoscape.cycl;

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
