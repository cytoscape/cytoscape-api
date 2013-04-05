/**
This package provides support for graphical annotations associated with a Cytoscape
{@link org.cytoscape.view.model.CyNetworkView}.  Annotations currently include 
shapes {@link org.cytoscape.view.presentation.annotations.ShapeAnnotation}, 
text {@link org.cytoscape.view.presentation.annotations.TextAnnotation},
bounded text {@link org.cytoscape.view.presentation.annotations.BoundedTextAnnotation}, and
images {@link org.cytoscape.view.presentation.annotations.ImageAnnotation}.  In addition
to the standard annotations the pacakge also supports connectors 
{@link org.cytoscape.view.presentation.annotations.ArrowAnnotation} that can connect
an annotation to a point, another annotation, or to a {@link org.cytoscape.model.CyNode}.
<p>Annotations are created by calls to
{@link org.cytoscape.view.presentation.annotations.AnnotationFactory#createAnnotation()}.
Once an annotation is created, it must be added to the
{@link org.cytoscape.view.presentation.annotations.AnnotationManager}, which will actually
draw the annotation on the canvas.  
<p>In general, each type of annotation has it's own set of getter
and setter routines that are appropriate for annotation type.  Annotations are
serialized and deserialized through {@link org.cytoscape.model.CyTable} entries.
Annotation parameters may be set at creation time by setting the appropriate
values in a arg map. 
 */
package org.cytoscape.view.presentation.annotations;
