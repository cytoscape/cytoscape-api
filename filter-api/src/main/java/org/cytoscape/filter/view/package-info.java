/**
 * Provides interfaces for contributing user interfaces for {@link org.cytoscape.filter.model.Transformer}s to
 * Cytoscape. To contribute a user interface for a particular {@link org.cytoscape.filter.model.Transformer}
 * implement the interface {@link org.cytoscape.filter.view.TransformerViewFactory} and register it as an
 * OSGi service.  User interfaces that provide a different behaviour when the
 * "Apply Automatically" checkbox is enabled should implement the interface
 * {@link org.cytoscape.filter.view.InteractivityChangedListener} as well.
 */
package org.cytoscape.filter.view;
