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
public abstract class BinaryExpression extends BaseExpression {
   private Expression first;
   private Expression second;
   private String sign;
   /**
    *
    * @return the sign
    */
   public String getSign() {
       return this.sign;
   }
   /**
    *
    * @param e a sign
    */
   public void setSign(String e) {
        this.sign = e;
   }
   /**
    *
    * @return the first expression
    */
   public Expression getFirst() {
       return this.first;
   }
   /**
   *
   * @param e the first expression
   */
   public void setFirst(Expression e) {
        this.first = e;
   }
   /**
    *
    * @return the second expression
    */
   public Expression getSecond() {
       return this.second;
   }
   /**
   *
   * @param e the second expression
   */
   public void setSecond(Expression e) {
        this.second = e;
   }
   /**
   *
   * @return a list of the variables in the expression.
   */
   public List<String> getVariables() {
        Set<String> s = new TreeSet<String>();
        List<String> l = new ArrayList<String>();
        s.addAll(this.first.getVariables());
        s.addAll(this.second.getVariables());
        l.addAll(s);
        return l;
    }

   /**
   *
   * @param assignment a Map for the variables in the expression
   * @return the result of the two expressions depending of its sign
   * @throws Exception if the expression contains a variable which is not in the assignment
   */
   public double evaluate(Map<String, Double> assignment) throws Exception {
       if ((this.sign == " L ") && equal(this.first, this.second)) {
           return 1;
       }
       if (this.sign == " - " && equal(this.first, this.second)) {
           return 0;
       }
       double x, y;
       x = this.first.evaluate(assignment);
       y = this.second.evaluate(assignment);
       if (this.sign == " - ") {
           if (equal(this.first, this.second)) {
               return 0;
           }
           return x - y;
       }
       if (this.sign == " + ") {
           return x + y;
       }
       if (this.sign == " * ") {
           return x * y;
       }
       if (this.sign == " / ") {
           if (x != y) {
               return x / y;
           }
           throw new Exception();
       }
       if (this.sign == "^") {
           return Math.pow(x, y);
       }
       if (y > 0 && x != 1) {
           return Math.log(y) / Math.log(x);
       }
       throw new Exception();
    }
   /**
   *
   * @return the result of the two expressions depending of its sign
   * @throws Exception if it can't evaluate the expression
   */
   public double evaluate() throws Exception {
       if ((this.sign == " L ") && equal(this.first, this.second)) {
           return 1;
       }
       if (this.sign == " - " && equal(this.first, this.second)) {
           return 0;
       }
       double x, y;
       x = this.first.evaluate();
       y = this.second.evaluate();
       if (this.sign == " - ") {
           return x - y;
       }
       if (this.sign == " + ") {
           return x + y;
       }
       if (this.sign == " * ") {
           return x * y;
       }
       if (this.sign == " / ") {
           if (x != y) {
               return x / y;
           }
           throw new Exception();
       }
       if (this.sign == "^") {
           return Math.pow(x, y);
       }
       if (y > 0 && x != 1) {
           return Math.log(y) / Math.log(x);
       }
       throw new Exception();
   }
   /**
   *
   * @return a nice string representation of the expression.
   */
   public String toString() {
       String x = ("(" + first.toString() + this.sign + second.toString() + ")");
       return x;
   }
   /**
   *
   * @return a simplified version of the current expression.
   */
   public Expression simplify() {
       if (canEval(this.first) && canEval(this.second)) {
           try {
               double x = this.evaluate();
               return new Num(x);
           } catch (Exception e) {
               System.out.println("error");
           }
       }
       if (this.sign == " - ") {
           return new Minus(this.first, this.second);
       }
       if (this.sign == " + ") {
           return new Plus(this.first, this.second);
       }
       if (this.sign == " * ") {
           return new Mult(this.first, this.second);
       }
       if (this.sign == " / ") {
           return new Div(this.first, this.second);
       }
       if (this.sign == "^") {
           return new Pow(this.first, this.second);
       }
       return new Log(this.first, this.second);
   }
}
