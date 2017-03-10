package org.cytoscape.filter.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A warning message that will appear in the UI when a Transformer is invalid.
 */
public class ValidationWarning {

	private final String warning;
	
	public ValidationWarning(String warning) {
		this.warning = warning;
	}
	
	public String getWarning() {
		return warning;
	}
	
	
	/**
	 * Use this method with a static import to shorten the creation of Lists of ValidationWaring objects.
	 */
	public static final List<ValidationWarning> warn(String ... warnings) {
		return Arrays.stream(warnings).map(ValidationWarning::new).collect(Collectors.toList());
	}
}
