package org.cytoscape.work;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation type that can be applied to a <i>method</i> in order to 
 * allow a <code>TunableInterceptor</code>
 * to catch it, and so to use its members to create a corresponding interface 
 * for a user.
 * 
 * Here is an example of how to use a <code>ProvidesGUI</code> annotation:
 *
 * <pre>
 * 	&#64;ProvidesGUI
 * 	public JPanel getGUI() { return mySetupPanel; }
 * </pre>
 * 
 * Please note that the method annotated with this needs to return a JPanel and take no arguments.
 * @CyAPI.Api.Interface
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ProvidesGUI {
}
