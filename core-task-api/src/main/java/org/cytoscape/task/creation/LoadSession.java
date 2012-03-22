package org.cytoscape.task.creation;

import java.io.File;

import org.cytoscape.session.CySession;
import org.cytoscape.work.TaskIterator;

public interface LoadSession {
	
	TaskIterator loadSession(final File file);

	TaskIterator loadSession();
}
