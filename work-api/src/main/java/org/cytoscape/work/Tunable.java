package org.cytoscape.work;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation type that can be applied to a <i>field</i> or a <i>method</i> 
 * in order to allow <code>TunableInterceptor</code> to catch it,
 * and so to use its members to create a corresponding interface for a user.
 * 
 * <br/>
 * This interface describes the different members that can be used in the 
 * <code>@Tunable(...)</code> to control the instantiation of user interface to
 * present to a user.
 * <br/>
 * 
 * Here is an example of how to use a <code>Tunable</code> annotation:
 * <p><pre>
 * <code>
 * 	<code>@Tunable(description="your last name", group={"Human","pupil"}, params="displayState=collapsed")<code>
 * 	public String lastName = "Smith";
 * </code>
 * </pre></p>
 * 
 * This tunable will be part of a group("<code>pupil</code>"), which is also a part of 
 * a metagroup("<code>Human</code>").<br/>
 * If a the user interface auto-generated from this is a GUI, <code>collapsed</code> could 
 * indicate that the initial state of the panel that may display the JTextField with the 
 * <code>lastName</code> will be collapsed and needs to 
 * be expanded in order to see its components.<br>
 * @CyAPI.Api.Interface
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface Tunable {
	/**
	 * Human-readable label identifying the tunable as displayed to a user.
	 */
	String description() default "";

	/**
	 * Used to define all the groups in which the Tunable takes part (by default, 
	 * its doesn't belong to any group).
	 * 
	 * <p><pre><code>
	 * <b>Example</b>:
	 * 	<code>@Tunable(description="write your last name", group={"Company","Department","office","identity"})</code>
	 * 	public String lastName = "Smith";
	 * </code></pre></p>
	 * 
	 * This String <code>Tunable</code> will take part of these 4 groups.
	 * <b>warning</b>: Note that they are set in an order of subgroups of a main one.
	 * 
	 * <p><pre>
	 * <b>Example</b>:
	 * 
	 * 	<code>@Tunable(description="write your first name", groups={"Company","Department","office","identity"})</code>
	 * 	public String firstName = "John";
	 * 
	 * 	<code>@Tunable(description="write the name of your office", groups={"Company","Department","office"})</code>
	 * 	public String officeName = "CytoscapeDevelopment's Office";
	 * 	</code>
	 * 
	 * Here we have a second item for the identity of a person(the <i>firstName</i>).So, 
	 * the 2 <code>Tunable</code> <i>lastName</i> and <i>firstName</i> are in the subgroup 
	 * <i>identity</i> But, the <code>Tunable</code> String officeName will only take part 
	 * of the upperGroup <i>office</i>, and so won't be set with these other 2 fields.
	 * </pre></p>
	 */
	String[] groups() default {};

	
	/**
	 * Boolean value to choose if the <code>Tunable</code> will control the display of other, 
	 * nested child tunables.
	 */
	boolean xorChildren() default false;

	
	/**
	 * Key that will refer to the "value" of the <code>Tunable</code> which has 
	 * <code>xorChildren=true</code>
	 * 
	 * <p><pre><code>
	 * <b>Example</b> : 
	 * 	<code>@Tunable(description="Single list", group={"TestGroup"}, <b>xorChildren=true</b>)</code>
	 * 	public ListSingleSelection<String> chooser = new ListSingleSelection<String>("<b>Names</b>","<b>FirstNames</b>");
	 * 	
	 * 	<code>@Tunable(description="Multi list", group={"TestGroup","Names"}, <b>xorKey="Names"</b>)</code>
	 * 	public ListMultipleSelection<String> names = new ListMultipleSelection<String>("Johnson","Turner","Smith");
	 * 
	 * 	<code>@Tunable(description="Multi list", group={"TestGroup","First Names"}, <b>xorKey="FirstNames"</b>)</code>
	 * 	public ListMultipleSelection<String> firstnames = new ListMultipleSelection<String>("George","Jane","Sarah");
	 * </code>
	 * </pre></p>
	 *
	 * Here, the 2 <code>ListMultipleSelection</code> won't be displayed in the GUI at the 
	 * same time : each of them depends on the xorKey(<i>FirstNames</i> or <i>Names</i>)
	 * that will match the "value" (i.e item that has been selected) in the 
	 * <code>ListSingleSelection</code>
	 */
	String xorKey() default "";
	
	
	/**
	 * To add a dependency between 2 or more <code>Tunables</code> 
	 * 
	 * <p><pre>
	 * The <code>JPanel</code> of the <code>Tunable</code> that depends on the 
	 * other one will be activated only if the value which is required is set.
	 * 
	 * Here is an example of how to add dependencies between <code>Tunables<code> :
	 * 
	 * <code>
	 *   &#64;Tunable(description="Type")
	 *   public boolean type = false;
	 *
	 *   &#64;Tunable(description="Host name",dependsOn="type=true")
	 *   public String hostname="";
	 * </code>
	 *  </pre></p>
	 * So <code>hostname</code> will be activated if <code>type</code> is set to "true"
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
	 *     This is being used by <code>AbstractBounded</code> and 
	 *     <code>AbstractFlexiblyBounded</code>.
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
	 * <pre><code>
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
	 * </code></pre>
	 * @return a list of Tunable field/method names that will trigger this Tunable to be updated.
	 */
	String[] listenForChange() default {};
}
