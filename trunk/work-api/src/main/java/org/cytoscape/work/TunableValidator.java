package org.cytoscape.work;


/**
 * If implemented, this interface is used to apply a test to the modified values of a Tunable.
 * 
 * <p><pre>
 *     <b>Example</b>:
 * 
 *     If using this Test class :
 *     <code>
 *         public class Test {
 *             <code>@Tunable(...)</code>
 *             String name = "John";
 *         }
 * 
 * Then we can provide a method to check if the new value for this tunable matches with the 
 * conditions that we have set :
 * 
 *         public class Test implements TunableValidator{
 *             <code>@Tunable(...)</code>
 *             String name = new String("John");
 * 
 *             ValidationState getValidationState(Appendable message){
 *                 if (name == null || name.isEmpty()) {
 *                     message.append("Name not specified!");
 *                     return INVALID;
 *                 } else if (name.equals("Johnny")) {
 *                     message.append("Are you sure you want to use a nickname?");
 *                     return REQUEST_CONFIRMATION;
 *                 } else {
 *                     return OK;
 *                 }
 *             }
 *         }
 *     </code></pre></p>
 * The String message returned by <code>validate()</code> method is displayed to the user. 
 * 
 * @CyAPI.Spi.Interface
 */
public interface TunableValidator {
    /** 
     * The states the the validator can return.
     * @CyAPI.Enum.Class
     */
    public enum ValidationState {
        /** The input is invalid. */
        INVALID, 
        /** The input is valid. */
        OK, 
        /** Request confirmation from the user. */
        REQUEST_CONFIRMATION
    }

    /**
     * Executes the validation test on the annotated <code>Tunables</code>.
     * 
     * @param  errMsg  if the validation failed an explanatory message can be found here and accessed via
     *                 <code>errMsg.toString()</code>
     * @return OK if the test succeeded and INVALID if it failed and REQUEST_CONFIRMATION if the user has
     *         to be asked for confirmation, e.g. if a file would have to be overwritten etc.
     */
    ValidationState getValidationState(final Appendable errMsg);
}
