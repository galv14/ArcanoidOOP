import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author gal.
 *
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expres;
    private String sign;
    /**
     *
     * @return the expression
     */
    public Expression getExpres() {
        return this.expres;
    }
    /**
     *
     * @param s the sign
     */
    public void setSign(String s) {
         this.sign = s;
    }
    /**
    *
    * @return the sign
    */
   public String getSign() {
       return this.sign;
   }
   /**
    *
    * @param e the expression
    */
   public void setExpres(Expression e) {
        this.expres = e;
   }
    /**
    *
    * @return a list of the variables in the expression.
    */
    public List<String> getVariables() {
         Set<String> s = new TreeSet<String>();
         List<String> l = new ArrayList<String>();
         s.addAll(this.expres.getVariables());
         l.addAll(s);
         return l;
     }
    /**
    *
    * @param var a String
    * @param expression an Expression
    * @return a new expression in which all occurrences of the variable var
    * are replaced with the provided expression
    */
   public Expression assign(String var, Expression expression) {
       Expression e1 = expres.assign(var, expression);
       return e1;
   }
   /**
   *
   * @return a nice string representation of the expression.
   */
   public String toString() {
       String x;
       if (this.sign == " - ") {
       x = ("(-" + getExpres().toString() + ")");
       return x;
       }
       if (this.sign == " cos ") {
           x = ("cos(" + getExpres().toString() + ")");
           return x;
       }
       x = ("sin(" + getExpres().toString() + ")");
       return x;
   }

   /**
   *
   * @param assignment a Map for the variables in the expression
   * @return the result of the expression depends on its sign
   * @throws Exception if the expression contains a variable which is not in the assignment
   */
   public double evaluate(Map<String, Double> assignment) throws Exception {
      double answer;
      answer = this.getExpres().evaluate(assignment);
      if (sign == " cos ") {
          if ((answer - 90) % 180 == 0) {
              return 0;
          }
          return Math.cos(Math.toRadians(answer));
      }
      if (sign == " sin ") {
          if (answer % 180 == 0) {
              return 0;
          }
          return Math.sin(Math.toRadians(answer));
      }
      return -answer;
   }
   /**
   *
   * @return the result of the expression depends on its sign
   * @throws Exception if it can't evaluate the expression
   */
  public double evaluate() throws Exception {
      double answer = getExpres().evaluate();
      if (sign == " cos ") {
          if ((answer - 90) % 180 == 0) {
              return 0;
          }
          return Math.cos(Math.toRadians(answer));
      }
      if (sign == " sin ") {
          if (answer % 180 == 0) {
              return 0;
          }
          return Math.sin(Math.toRadians(answer));
      }
      return -answer;
  }
  /**
  *
  * @return a simplified version of the current expression.
  */
  public Expression simplify() {
      if (canEval(this.getExpres())) {
          try {
              double x = this.evaluate();
              return new Num(x);
          } catch (Exception e) {
              System.out.println("error");
          }
      }
      Expression f = this.getExpres().simplify();
      if (sign == " cos ") {
          return new Cos(f);
      }
      if (sign == " sin ") {
          return new Sin(f);
      }
      return new Neg(f);
  }
}
