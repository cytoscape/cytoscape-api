package org.cytoscape.work;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation type that can be applied to a <i>method</i> which returns a
 * String that will be used for the title of a Tunable user interface dialog
 * window.
 * 
 * Please note that the method annotated with this needs to return a String and take no arguments.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ProvidesTitle {
}
