import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gal
 *
 */
public class Num implements Expression {
    private double num;
    /**
     *
     * @param x a double
     */
    public Num(double x) {
        this.num = x;
    }
    /**
     *  @throws Exception never
     *  @param assignment a Map<String, Double>
     *  @return the number
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.num;
    }
    /**
     * @throws Exception never
     * @return the number
     */
    public double evaluate() throws Exception {
        return this.num;
    }
    /**
     * @return null
     */
    public List<String> getVariables() {
        List<String> l = new ArrayList<String>();
        return l;
    }
    /**
     *
     * @return the number as Sring
     */
    public String toString() {
        return Double.toString(this.num);
    }
    /**
    *
    * @param var a String
    * @param expression an Expression
    * @return a new expression in which all occurrences of the variable var
    * are replaced with the provided expression
    */
    public Expression assign(String var, Expression expression) {
        return this;
    }
    /**
    *
    * @param var a String
    * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
    */
   public Expression differentiate(String var) {
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
