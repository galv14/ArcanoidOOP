import java.util.List;
import java.util.Map;

/**
 *
 * @author gal.
 *
 */
public interface Expression {
   // Evaluate the expression using the variable values provided
   // in the assignment, and return the result.  If the expression
   // contains a variable which is not in the assignment, an exception
   // is thrown.
    /**
     *
     * @param assignment the expression using the variable values
     * @return the result
     * @throws Exception if the expression contains a variable which is not in the assignment
     */
   double evaluate(Map<String, Double> assignment) throws Exception;

   // A convenience method. Like the `evaluate(assignment)` method above,
   // but uses an empty assignment.
   /**
    *
    * @return the evaluate the expression
    * @throws Exception if it can't evaluate the expression
    */
   double evaluate() throws Exception;


   /**
    *
    * @return a list of the variables in the expression.
    */
   List<String> getVariables();

   /**
    *
    * @return a nice string representation of the expression.
    */
   String toString();

   /**
    *
    * @param var a String
    * @param expression an Expression
    * @return a new expression in which all occurrences of the variable var
    * are replaced with the provided expression
    */
   Expression assign(String var, Expression expression);
    /**
    *
    * @param var a String
    * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
    */
   Expression differentiate(String var);
   /**
   *
   * @return a simplified version of the current expression.
   */
  Expression simplify();
}