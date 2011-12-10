package org.cytoscape.task.creation;

import java.io.File;

import org.cytoscape.session.CySession;

public interface LoadSession {
	
	CySession loadSession(final File file);
}
