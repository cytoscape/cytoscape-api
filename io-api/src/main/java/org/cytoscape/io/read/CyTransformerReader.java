package org.cytoscape.io.read;

import java.io.IOException;
import java.io.InputStream;

import org.cytoscape.filter.model.NamedTransformer;

public interface CyTransformerReader {
	NamedTransformer<?, ?>[] read(InputStream stream) throws IOException;
}
