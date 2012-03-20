package org.cytoscape.io.read;

import java.io.InputStream;

import org.cytoscape.io.CyFileFilter;
import org.cytoscape.work.TaskIterator;

public abstract class SimpleInputStreamTaskFactory implements InputStreamTaskFactory<Object> {
	private CyFileFilter fileFilter;

	public SimpleInputStreamTaskFactory(CyFileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}
	
	@Override
	public final TaskIterator createTaskIterator(Object tunableContext, InputStream is, String inputName) {
		return createTaskIterator(is, inputName);
	}

	@Override
	public boolean isReady(Object tunableContext, InputStream is, String inputName) {
		return isReady(is, inputName);
	}
	
	protected boolean isReady(InputStream is, String inputName) {
		return true;
	}

	public Object createTunableContext() {
		return null;
	}

	@Override
	public CyFileFilter getFileFilter() {
		return fileFilter;
	}
	
	protected abstract TaskIterator createTaskIterator(InputStream is, String inputName);
}
