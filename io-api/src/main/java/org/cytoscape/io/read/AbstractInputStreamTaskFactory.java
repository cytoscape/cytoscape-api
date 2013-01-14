package org.cytoscape.io.read;

import java.io.InputStream;

import org.cytoscape.io.CyFileFilter;

/**
 * TODO: Missing documentation
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule io-api
 */
public abstract class AbstractInputStreamTaskFactory implements InputStreamTaskFactory {
	
	private CyFileFilter fileFilter;

	public AbstractInputStreamTaskFactory(final CyFileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReady(InputStream is, String inputName) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CyFileFilter getFileFilter() {
		return fileFilter;
	}
}
