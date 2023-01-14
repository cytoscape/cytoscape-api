/**
   This package provides an interface to <a href="https://www.khronos.org/opencl">OpenCL</a> from Cytoscape core and Cytoscape apps.

   <h3>Introduction to OpenCL and Cytoscape</h3>
   <p>OpenCL (Open Computing Language) is a framework for parallel programming of GPUs.  The language itself is essentially C with some
   special hooks.  An example OpenCL program to add two vectors together might look like:
   <pre><code>
   __kernel void vector_add(__global const int *A, __global const int *B, __global int *C, int const size) {
 
    // Get the index of the current element to be processed
    int i = get_global_id(0);
 
    if (i &lt; size) {
      // Do the operation
      C[i] = A[i] + B[i];
    }
  }
  </code></pre>
  This program looks just like C with a couple of notable differences.  First, it specifies that the variables are global, which is
  important for GPU programming since that implies we need to make that memory available.  Second is the line that calls the
  {@code get_global_id} method, which actually is how we handle the parallelism.  So, if we look at the code above, we can imagine
  this code running on each GPU core.  On each core, the code would get an index that represents the current element and process
  that element.  That's it!  ....sort of....
  </p>
  <p>GPU programming has it's own challenges.  First, we have to manage the memory -- allocating memory on the GPUs, transfering data
  to that memory, and pulling data back from that memory.  This is not typicall in Java programming.  We also need to manage the
  programs themselves and make the programs (OpenCL calls them <b>kernels</b>) available to be executed.  The program above implemented
  using CyCL might look like this:

  <pre><code>
  // Define some memory buffers
  private CyCLBuffer A;
  private CyCLBuffer B;
  private CyCLBuffer C;
  String program =       
          "__kernel void TestKernel(__global const int* a, __global const int* b, __global int* c, int const size) {\n"
          + "  const int itemId = get_global_id(0);\n" 
          + "  if(i &lt; size) {\n"
          + "    c[i] = a[i] + b[i];\n" 
          + "  }\n" 
          + "}";

  CyCLFactory cycl = serviceRegistrar.getService(CyCLFactory.class);
  try {
    // Check to see if CyCL correctly initialized
    if (!cycl.isInitialized()) {
      // log some error
    }

    // Get the best device
    CyCLDevice device = cycl.getDevice();  // We'll do pretty much everything through the device

    // Create the program
    CyCLProgram testProgram = device.addProgram("Test", new String[] { program }, new String[] { "TestKernel" }, null, false);
    
    // Create arrays a, b, and c
    int n = 1 &lt;&lt; 13; // Just get a size
    int[] a = new int[n];
    int[] b = new int[n];
    int[] c = new int[n];

    // Initialize the arrays
    for (int i = 0; i &lt; n; i++) {
      a[i] = i;
      b[i] = i;
    }

    // Now create our buffers
    A = device.createWriteBuffer(a);
    B = device.createWriteBuffer(b);
    C = device.createReadBuffer(c);

    // OK, get the kernel
    CyCLKernel kernel = program.getKernel("TestKernel"); // This must match the name of the method we want to execute

    kernel.execute(new long[] { n }, null, A, B, C, n); // OK, execute!

    C.getFromDevice(c); // Read the result data from the GPU back into c
    device.finishQueue(); // All done

    // Free the buffers
    A.free();
    B.free();
    C.free();
  }
  </code></pre>

  As seen above, to get started, you need to get the {@code CyCLFactory}, which gets registered at Cytoscape initialization time.  The
  next step is to get a {@code CyCLDevice} from the factory.  Most everything else is done through the device.  The {@code CyCLDevice}
  returned by {@code CyCLFactory.getDevice()} is the "best" (i.e. fastest) device available based on a simple benchmark that gets
  run at initialization time.


 */
package org.cytoscape.cycl;
