package org.cytoscape.ci;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Indicates to JAX-RS processors that the output of a method is intended to be wrapped in a CIResponse, existing as the
 * content of the data field.
 * 
 * IMPORTANT: This annotation should be applied directly to the implementation, as inherited annotations cannot be 
 * retrieved within the JAX-RS filters.
 *  
 * @author davidotasek
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CIWrapping {

}
