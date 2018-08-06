import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gal.
 *
 */
public class Var implements Expression {
    private String variable;
    /**
     *
     * @param x a String
     */
    public Var(String x) {
        this.variable = x;
    }
    /**
    *
    * @param assignment the expression using the variable values
    * @return the result
    * @throws Exception if the expression contains a variable which is not in the assignment
    */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            double value = assignment.get(this.variable);
            return value;
        }
        throw new Exception(" the variable is not in the map ");
    }
    /**
     * @throws Exception always
     * @return Exception because we can't evaluate a variable
     */
    public double evaluate() throws Exception {
        throw new Exception(" can't evaluate a variable ");
    }
    /**
    *
    * @return a list of the variables in the expression.
    */
    public List<String> getVariables() {
        List<String> l = new ArrayList<String>();
        l.add(this.variable);
        return l;
    }
    /**
    *
    * @return the number as Sring
    */
   public String toString() {
       return this.variable;
   }
   /**
   *
   * @param var a String
   * @param expression an Expression
   * @return the expression if var is equal to the variable, null otherwise
   *
   */
    public Expression assign(String var, Expression expression) {
       if (this.variable == var) {
           return expression;
       }
       return this;
    }
    /**
    *
    * @param var a String
    * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
    */
   public Expression differentiate(String var) {
       if (this.variable == var) {
           return new Num(1);
       }
       return new Num(0);
   }
   /**
   *
   * @return a simplified version of the current expression.
   */
   public Expression simplify() {
       return this;
   }
}
