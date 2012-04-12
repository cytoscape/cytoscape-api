/**
 * This package provides factory interfaces to be used by anyone wishing to provide
 * export or writing capabilities to the rest of Cytoscape.  To provide writing 
 * capabilities an App writer will implement an instance of CyWriterFactory that
 * creates a fully configured task.  Part of implementing CyWriterFactory involves
 * providing a CyFileFilter, an interface used to define file extensions and types
 * of data that can be written.
 * <br/>
 * This package is useful primarily when used within the Task/TaskFactory framework 
 * used throughout Cytoscape.  To simply write files from an App, consult the interfaces 
 * found in the Core Task API (org.cytoscape.task.*).
 */
package org.cytoscape.io.write;
