package org.cytoscape.io.read;

import java.io.InputStream;

import org.cytoscape.io.CyFileFilter;

public abstract class AbstractInputStreamTaskFactory implements InputStreamTaskFactory {
	private CyFileFilter fileFilter;

	public AbstractInputStreamTaskFactory(CyFileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}
	
	@Override
	public boolean isReady(InputStream is, String inputName) {
		return true;
	}

	@Override
	public CyFileFilter getFileFilter() {
		return fileFilter;
	}
}
