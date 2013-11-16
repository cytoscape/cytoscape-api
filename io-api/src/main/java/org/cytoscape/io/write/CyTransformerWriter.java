package org.cytoscape.io.write;

import java.io.IOException;
import java.io.OutputStream;

import org.cytoscape.filter.model.NamedTransformer;

public interface CyTransformerWriter {
	void write(OutputStream stream, NamedTransformer<?, ?>... namedTransformer) throws IOException;
}
