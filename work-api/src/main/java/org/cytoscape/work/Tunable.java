package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation type that can be applied to public <i>fields</i> or a <i>methods</i> 
 * in a {@link Task} object that allows the {@link Task} to be configured 
 * with user supplied information.  <code>Tunable</code> annotations allow {@link Task}s to
 * be configured with specific, user supplied data, without needing to produce
 * a user interface. The {@link TaskManager} is responsible for inferring a 
 * user interface from the types of the fields or methods being annotated. 
 * 
 * <br/>
 * <code>Tunable</code> annotations are suitable only for information that <b>must</b> be
 * supplied by the user.  For instance, the name of a file to load can only
 * be known by the user, so is appropriate to be specified by the user.  Other
 * information necessary for the {@link Task} to execute should be provided
 * as constructor arguments to the {@link Task}.
 * 
 * <br/>
 * <code>Tunable</code> annotations provide several parameters that can be used to control
 * the display of <code>Tunable</code>s in the eventual user interface.  These parameters
 * are documented below.
 * <br/>
 * 
 * Here is an example of how to use a <code>Tunable</code> annotation for a field annotation:
 * <pre>
 * 	&#64;Tunable(description="your last name", group={"Human","pupil"}, params="displayState=collapsed")
 * 	public String lastName = "Smith";
 * </pre>
 * 
 * This tunable will be grouped in the user interface first within the "Human" group, then
 * within the "pupil" group. How this grouping is displayed to users is a function of which {@link TaskManager}
 * is used. Likewise, if supported by the {@link TaskManager}, the display state of the <code>Tunable</code>
 * will initially be collapsed.
 * <br/>
 * Here is an example of how to use a <code>Tunable</code> annotation for method annotation.
 * Method annotations require both a getter and a setter method to get and set a 
 * value. Only the getter method needs to be annotated.  The getter method must take no arguments
 * and return a value and be named with the prefix "get".  The setter method does not need a 
 * {@link Tunable} annotation, however the method must take a single argument of the same type as
 * the getter method, it must return void, it must be named with the prefix "set", and the
 * rest of the name must match that of the getter method.
 * <pre>
 * 	&#64;Tunable(description="your last name", group={"Human","pupil"}, params="displayState=collapsed")
 * 	public String getLastName() {
 *       return lastName;
 *  }
 * 
 *  // NO Tunable annotation for the setter method. 
 * 	public void setLastName(String newLastName) {
 *       lastName = newLastName;
 *  }
 * </pre>
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface Tunable {
	final static String GUI_CONTEXT = "gui";
	final static String NOGUI_CONTEXT = "nogui";
	final static String BOTH_CONTEXT = "both";

	/**
	 * Mandatory, human-readable label identifying the Tunable as displayed to a user.
	 */
	String description() default "";

	/**
	 * Optional human-readable description that can provide more complete description of
 	 * that the <code>Tunable</code> does and the implication of various settings. This 
 	 * is intended for scripting use.
	 */
	String longDescription() default "";
	
	/**
 	 * Optional human-readable description that can provide more complete description of
 	 * that the <code>Tunable</code> does and the implication of various settings.  In
 	 * general, this might be implemented in a GUI as a tooltip, but might also be used
 	 * for other purposes.
 	 */
	String tooltip() default "";

	/**
	 * Optional example string for use in scripting environments. This string should be valid
	 * according to any formatting rules for this <code>Tunable</code>, and ideally should 
	 * represent a usable value at execution time, though the latter is not a requirement.
	 */
	String exampleStringValue() default "";
	
	/**
	 * Used to define the presentation grouping of the Tunable. By default a Tunable
	 * belongs to the top level group.
	 * 
	 * <b>Example</b>:
	 * <pre>
	 * 	&#64;Tunable(description="write your last name", group={"Company","Department","Office"})
	 * 	public String lastName = "Smith";
	 * </pre>
	 * 
	 * The <i>lastName</i> <code>Tunable</code> will be nested within the "Company", "Department", 
	 * "Office", and "Identity" groups. The order of groups defines their nesting.
	 * 
	 * <b>Example</b>:
	 * <pre>
	 * 	&#64;Tunable(description="write your first name", groups={"Company","Department","Office","Identity"})
	 * 	public String firstName = "John";
	 * 
	 * 	&#64;Tunable(description="write the name of your office", groups={"Company","Department","Office"})
	 * 	public String officeName = "Cytoscape Development";
	 * 	</pre>
	 *<br> 
	 * Here we add a second item (<i>firstName</i>) to the "Identity" group and then add the 
	 * <i>officeName</i> <code>Tunable</code> to "Office" group.  The "Identity" group will
	 * appear with <i>officeName</i> within the "Office" group.
	 */
	String[] groups() default {};

	/**
	 * Returns the gravity used to place the tunable in the panel.
	 * Gravity is a numeric value associated with each tunable item. <code>Tunable</code>s
	 * are sorted in ascending order based on their gravity values. The first <code>Tunable</code> 
	 * to show will be the one with lowest value.
	 * @return The gravity of the <code>Tunable</code>
	 */
	double gravity() default 999.0;
	
	/**
	 * Returns true if this field or method is used to control the display of <i>other</i>
	 * <code>Tunable</code>s. The <i>other</i> Tunables in question are matched according
	 * the {@link Tunable#groups} value and the {@link Tunable#xorKey} of the <i>other</i>
	 * Tunables. See {@link Tunable#xorKey} for a full example.
	 * @return true if this field or method is used to control the display of <i>other</i>
	 * <code>Tunable</code>s. 
	 */
	boolean xorChildren() default false;

	
	/**
	 * Returns a value that matches one of the values found in a <i>different</i> Tunable
	 * annotated field or method that returns <code>true</code> for the {@link Tunable#xorChildren}
	 * method.
	 * 
	 * <b>Example</b> : 
	 * <pre>
	 * 	&#64;Tunable(description="Distance measure", group={"Measure"}, <b>xorChildren=true</b>)
	 * 	public ListSingleSelection<String> chooser = new ListSingleSelection<String>("<b>Metric</b>","<b>English</b>");
	 * 	
	 * 	&#64;Tunable(description="Metric distances", group={"Measure","Metric"}, <b>xorKey="Metric"</b>)
	 * 	public ListSingleSelection<String> metric = new ListSingleSelection<String>("millimeter","meter","kilometer");
	 * 
	 * 	&#64;Tunable(description="English distances", group={"Measure","English"}, <b>xorKey="English"</b>)
	 * 	public ListSingleSelection<String> english = new ListSingleSelection<String>("inch","yard","mile");
	 * </pre>
	 *
	 * Based on the selection made in the "chooser" <code>Tunable</code>, either the "metric" or
	 * the "english" <code>Tunable</code> will be displayed, not both. 
	 * The <code>xorKey</code> value must match one of the
	 * values specified in the <code>xorChildren</code> <code>Tunable</code>.
	 */
	String xorKey() default "";
	
	
	/**
	 * To add a dependency between two or more <code>Tunables</code>, where
	 * the display of one <code>Tunable</code> depends on the 
	 * the state of another. 
	 * 
	 * <p>Here is an example of how to add dependencies between <code>Tunables</code>:</p>
	 * 
	 * <pre>
	 *   &#64;Tunable(description="Type")
	 *   public boolean type = false;
	 *
	 *   &#64;Tunable(description="Host name",dependsOn="type=true")
	 *   public String hostname="";
	 * </pre>
	 * So <code>hostname</code> will only be displayed if <code>type</code> is set to "true"
	 */
	String dependsOn() default "";

	/**
	 * Returns a key1=value1;key2=value2;...;keyN=valueN type string.  To include commas,
	 * semicolons or backslashes in a value you must escape it with a leading backslash.
	 *
	 * Possible keys (which must consist of letters only) are<br/>
	 *  <ul>
	 *   <li>
	 *     fileCategory: this is used solely for File tunables and must be one of "network",
	 *     "table", "image", "attribute", "session", or "unspecified".
	 *   </li>
	 *   <li>
	 *     input: this is used solely for File tunables and must be either "true" or "false"
	 *   </li>
	 *   <li>
	 *     slider: used when the object's values range should be represented by a slider, 
	 *     the value should always be "true".
	 *     This can be used by <code>BoundedDouble</code>, 
	 *     <code>BoundedFloat</code>, and similar classes.
	 *   </li>
	 *   <li>
	 *     alignments: the value should be a comma-separated list of "horizontal" or "vertical".
	 *     This controls the arrangement of a <code>Tunable</code> within a group, if nothing has
	 *     been specified, "vertical" will be the default.  These values will be in a 1-to-1
	 *     correspondence with the strings in the "group" array.  Excess values will be ignored.
	 *   </li>
	 *   <li>
	 *    groupTitles: the value should be a comma-separated list of "hidden" and/or 
	 *    "displayed" indicating whether the name of a <code>Tunable</code>'s group has to be 
	 *    displayed or not in the <code>titleBorder</code> of the <code>JPanel</code> 
	 *    representing this group.
	 *   </li>
	 *   <li>
	 *    displayState: if present, the corresponding <code>Tunable</code>'s JPanel will be 
	 *    collapsible. The value must be either "collapsed" or "uncollapsed" indicating the 
	 *    initial display state.
	 *   </li>
	 *  </ul>
	 *
	 *  Note: Blanks/spaces in values are significant!
	 */
	String params() default "";

	/**
	 * Returns a list of Tunable field/method names that will trigger this Tunable to be updated.
	 * For instance if Tunable B wants to react to a change in Tunable A, then setting the listenForChange
	 * parameter is one mechanism for achieving this.  The listenForChange parameter will trigger the update
	 * method on the TunableHandler to be called, which will cause B to be updated. Here is an example:
	 * <br/>
	 * <pre>
	 * &#64;Tunable(description="A")
	 * public String getA() {
	 *    return a;	
	 * }
	 * 
	 * public void setA(String a) {
	 *    this.a = a;
	 * }
	 *
	 * // listenForChange="A" means that the value of this 
	 * // Tunable will be updated every time A is changed.
	 * &#64;Tunable(description="B",listenForChange="A")
	 * public String getB() {
	 *    if ( a.equals("somethingSpecial") )
	 *        return "hooray.";
	 *    else
	 *        return b;	
	 * }
	 * 
	 * public void setB(String b) {
	 *    this.b = b;
	 * }
	 * </pre>
	 * @return a list of Tunable field/method names that will trigger this Tunable to be updated.
	 */
	String[] listenForChange() default {};

	/**
 	 * Returns the context that this <code>Tunable</code> is meant for.  Must be one of "gui", "nogui", or
 	 * "both".  If no value is provided, "both" is assumed.  If the context is set to "gui", then this
 	 * <code>Tunable</code> will only be available through GUI implementations, and will not be made available
 	 * to command-line or headless implementations.  If the context is set to "nogui", then this
 	 * <code>Tunable</code> will not be available through the GUI.
 	 */
	String context() default "both";

	/**
	 * If this parameter is required, return true, otherwise it is assumed that a null (or empty) value
	 * is properly handled by the Task.
	 *
	 * @return true if required
	 */
	boolean required() default false;

	/**
	 * Provides a decimal format string suitable for {@link java.text.DecimalFormat}.  This allows default 
	 * values and ranges to be rationally presented to users.  Note that this is only for presentation 
	 * purposes and does not effect the underlying values.  Also, not all Tunables will respect format.
	 * At this point, only numeric and bounded numeric tunables respect format.
	 *
	 * @return the string to use for the format
	 */
	String format() default "";
}
